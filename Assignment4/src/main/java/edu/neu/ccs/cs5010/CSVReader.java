package edu.neu.ccs.cs5010;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private List<Candy> desiredCandies = new ArrayList<>();
    private int numOfCSVs;
    private String[] CSVNames;

    public CSVReader(int numOfCSVs, String[] CSVNames) {
        this.numOfCSVs = numOfCSVs;
        this.CSVNames = CSVNames;
    }

    public List<Candy> getDesiredCandiesFromCSV() {
        // read CSV here..
//        BufferedReader inputFile = null;
//        try {
//            inputFile = new BufferedReader(new FileReader(CSVPathname));
//            String line;
//
//            if((line = inputFile.readLine()) != null) {
//                String[] candyStrings = line.split(", ");
//
//            }
//        } catch (FileNotFoundException fnfe) {
//            System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
//            fnfe.printStackTrace();
//        } catch (IOException ioe) {
//            System.out.println("Something went wrong! : " + ioe.getMessage());
//            ioe.printStackTrace();
//        } finally {
//            if (inputFile != null) {
//                try {
//                    inputFile.close();
//                } catch (IOException e) {
//                    System.out.println("Failed to close input stream in finally block");
//                    e.printStackTrace();
//                }
//            }
//        }


        desiredCandies.add(new Candy("Super Size", "Twix"));
        desiredCandies.add(new Candy("Regular Size", "Snickers"));
        desiredCandies.add(new Candy("King Size", "Mars"));
        desiredCandies.add(new Candy("Regular Size", "Kit Kat"));
        desiredCandies.add(new Candy("Fun Size", "Toblerone"));
        desiredCandies.add(new Candy("Regular Size", "Almond Joy"));
        desiredCandies.add(new Candy("Fun Size", "Baby Ruth"));
        desiredCandies.add(new Candy("Super Size", "Milky Way"));
        desiredCandies.add(new Candy("King Size", "Mars"));
        desiredCandies.add(new Candy("Regular Size", "Twix"));

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
        return this.desiredCandies;
    }
}
