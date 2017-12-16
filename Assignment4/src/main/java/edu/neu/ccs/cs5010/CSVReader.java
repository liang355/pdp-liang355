package edu.neu.ccs.cs5010;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class CSVReader {

    private List<Candy> readLineToCandies(String line) {
        // get raw candy strings
        String[] candyStrings = line.split(", ");
        List<Candy> desiredCandies = new ArrayList<>();
        Pattern p;
        Matcher m;
        String size;
        String type;
        // iterate over raw candy strings
        for(String s : candyStrings) {
            // match and get size:
            p = Pattern.compile("(super size|king size|fun size).*?");
            m = p.matcher(s.toLowerCase());
            if(m.matches()) {
                size = m.group(1);
                String[] parts = size.split(" ");
                size = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1) + " " +
                        parts[1].substring(0, 1).toUpperCase() + parts[1].substring(1);
            } else {
                size = "Regular Size";
            }
            // match and get type:
            p = Pattern.compile(".*?(twix|snickers|mars|kit kat|whoopers|milky way|toblerone|crunch|baby ruth|almond joy)");
            m = p.matcher(s.toLowerCase());
            if(m.matches()) {
                type = m.group(1);
                String[] parts = type.split(" ");
                for(int i = 0; i < parts.length; i++) {
                    StringBuilder sb = new StringBuilder("");
                    sb.append(parts[i].substring(0, 1).toUpperCase()).append(parts[i].substring(1));
                    parts[i] = sb.toString();
                }
                type = String.join(" ", parts);
            } else {
                type = "Unrecognized Candy";
            }
            // create Candy(size, type)
            desiredCandies.add(new Candy(size, type));
        }
//        System.out.println(Arrays.toString(desiredCandies.toArray()));
        return desiredCandies;
    }

    public List<Candy> getDesiredCandiesFromCSV(String CSVPathname) {
        // read CSV here..
        List<Candy> desiredCandies = new ArrayList<>();
        BufferedReader inputFile = null;
        try {
            inputFile = new BufferedReader(new FileReader(CSVPathname));
            String line;

            if((line = inputFile.readLine()) != null) {
                return readLineToCandies(line);
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
        return desiredCandies;
    }
}



//        desiredCandies.add(new Candy("Super Size", "Twix"));
//        desiredCandies.add(new Candy("Regular Size", "Snickers"));
//        desiredCandies.add(new Candy("King Size", "Mars"));
//        desiredCandies.add(new Candy("Regular Size", "Kit Kat"));
//        desiredCandies.add(new Candy("Fun Size", "Toblerone"));
//        desiredCandies.add(new Candy("Regular Size", "Almond Joy"));
//        desiredCandies.add(new Candy("Fun Size", "Baby Ruth"));
//        desiredCandies.add(new Candy("Super Size", "Milky Way"));
//        desiredCandies.add(new Candy("King Size", "Mars"));
//        desiredCandies.add(new Candy("Regular Size", "Twix"));

//        desiredCandies.add(new Candy("Super Size", "Twix"));
//        desiredCandies.add(new Candy("Regular Size", "Snickers"));
//        desiredCandies.add(new Candy("King Size", "Hershey’s"));
//        desiredCandies.add(new Candy("Regular Size", "Kit Kat"));
//        desiredCandies.add(new Candy("Fun Size", "Toblerone"));
//        desiredCandies.add(new Candy("Regular Size", "Almond Joy"));
//        desiredCandies.add(new Candy("Fun Size", "Baby Ruth"));
//        desiredCandies.add(new Candy("Super Size", "Milky Way"));
//        desiredCandies.add(new Candy("King Size", "Mars"));
//        desiredCandies.add(new Candy("Regular Size", "Crunch"));