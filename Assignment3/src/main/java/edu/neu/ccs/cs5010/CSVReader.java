package edu.neu.ccs.cs5010;

import java.util.*;
import java.io.*;
import java.text.*;
import java.util.regex.*;

public class CSVReader {
    private List<String> lines = new ArrayList<>();
    private String filename = "";

    public List<String> getLines() {
        return lines;
    }

    public CSVReader(String CSVPathname) {
        this.filename = new File(CSVPathname).getName();

        BufferedReader inputFile = null;
        try {
            inputFile = new BufferedReader(new FileReader(CSVPathname));
            String line;

            while ((line = inputFile.readLine()) != null) {
                this.lines.add(line);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Something went wrong! : " + ioe.getMessage());
            ioe.printStackTrace();
        } finally {
            if (inputFile != null) {
                try {
                    inputFile.close();
                } catch (IOException e) {
                    System.out.println("Failed to close input stream in finally block");
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Map<String, String>> readCSVToMappings(String event) {
        List<Map<String, String>> mappings = new ArrayList<>();
        // 1. get all headers
        String headerLine = lines.get(0);
        String[] headers = headerLine.split(",");

        // 2. create a map<header, value> from each subsequent line
        for(int i = 1; i < lines.size(); i++) {
            String valueLine = lines.get(i);
            String[] values = valueLine.split(",");
            Map<String, String> map = new HashMap<>();
            for(int j = 0; j < values.length; j++) {
                // 3. format key and value (removing double quotes)
                StringBuilder sb = new StringBuilder(headers[j]);
                String formattedKey = sb.substring(1, headers[j].length() - 1);
                StringBuilder sb_v = new StringBuilder(values[j]);
                String formattedValue = sb_v.substring(1, values[j].length() - 1);

                map.put(formattedKey, formattedValue);
            }
            // 4. add extra entries to the map from other sources (e.g. date, filename, user input):
            // map Date
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            map.put("Date", timeStamp);

            // map departure-city
            Pattern p;
            Matcher m;
            String departure_city = "";
            p = Pattern.compile("From.*?To");
            m = p.matcher(filename);
            while(m.find()) {
                departure_city = m.group();
                StringBuilder sb = new StringBuilder(departure_city);
                departure_city = sb.substring(4, departure_city.length() - 2);
            }
            map.put("departure-city", departure_city);

            // map destination-city
            String destination_city = "";
            p = Pattern.compile("To.*?\\.");
            m = p.matcher(filename);
            while(m.find()) {
                destination_city = m.group();
                StringBuilder sb = new StringBuilder(destination_city);
                destination_city = sb.substring(2, destination_city.length() - 1);
            }
            map.put("destination-city", destination_city);

            // map event
            map.put("event", event);

            // 5. add the completed map to mappings
            mappings.add(map);
        }
        return mappings;
    }
}
