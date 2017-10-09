package edu.neu.ccs.cs5010;

import java.util.*;
import java.io.*;

public class CSVReader {
    private List<String> lines = new ArrayList<>();

    public List<String> getLines() {
        return lines;
    }

    public CSVReader(String CSVPathname) {
        BufferedReader inputFile = null;

        try {
            inputFile = new BufferedReader(new FileReader(CSVPathname));
            String line;

            while ((line = inputFile.readLine()) != null) {
                this.lines.add(line);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("*** OUPS! A file was not found : " + fnfe.getMessage());
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

    public List<Map<String, String>> readCSVToMappings() {
        List<Map<String, String>> mappings = new ArrayList<>();
        String headerLine = lines.get(0);
        String[] headers = headerLine.split(",");
        for(int i = 1; i < lines.size(); i++) {
            String valueLine = lines.get(i);
            String[] values = valueLine.split(",");
            Map<String, String> map = new HashMap<>();
            for(int j = 0; j < values.length; j++) {
                map.put(headers[j], values[j]);
            }
            mappings.add(map);
        }
        return mappings;
    }

    public static void main(String[] args) {
        String CSVPathname = "/Users/yingbinliang/IdeaProjects/pdp-liang355/Assignment3/Flight3FromVancouverToSeattle.csv";
        CSVReader reader = new CSVReader(CSVPathname);
        System.out.println(Arrays.toString(reader.getLines().toArray()));
    }
}
