package edu.neu.ccs.cs5010;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HalloweenNeighborhoodTraversal {
    private List<Visitable> households;
    private String traversalString = "Candy type, Candy size, Household type\n";
    private int traversalCount = 0;
    private String curCSVName = "";

    private HalloweenNeighborhoodTraversal() {
        households = new ArrayList<>();
        households.add(new HouseholdDetachedHouse());
        households.add(new HouseholdDuplex());
        households.add(new HouseholdMansion());
        households.add(new HouseholdTownHome());
    }

    private void printTraversalToFile(String traversalString) {
        // make output file name
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(curCSVName);
        String cnt = "";
        if(m.find()) {
            cnt = m.group();
        }
        String filePathname = "DreamTraversal" + cnt + ".txt";
        // use PrintWriter
        try {
            PrintWriter out = new PrintWriter(filePathname);
            out.print(traversalString);
            out.flush();
        } catch (FileNotFoundException fnfe) {
            System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
            fnfe.printStackTrace();
        }
    }

    private boolean findThisCandy(Candy desiredCandy) {
        HouseVisitor visitor = new HouseVisitor(desiredCandy);
        for(Visitable household : households) {
            if(visitor.hasDesiredCandy()) {
                break;
            }
            household.acceptVisitor(visitor);
            this.traversalString += visitor.getCurStepString();
        }
        if(!visitor.hasDesiredCandy()) {
            System.out.println("Candy "+ desiredCandy.toString() + " is not found in the neighborhood! ...");
        }
        return visitor.hasDesiredCandy();
    }

    private void findAndPrintTraversalForThisList(List<Candy> desiredCandies) {
        for(Candy c : desiredCandies) {
            if(!findThisCandy(c)) {
                System.out.println("No traversal exists");
                return;
            }
        }
        System.out.println(this.traversalString);
        printTraversalToFile(this.traversalString);
    }

    public void findAndPrintAllTraversals(int numOfCSVs, String[] CSVNames) {
        CSVReader csvReader = new CSVReader();
        for(int i = 0; i < numOfCSVs; i++) {
            this.curCSVName = CSVNames[i];
            List<Candy> candies = csvReader.getDesiredCandiesFromCSV(CSVNames[i]);
            findAndPrintTraversalForThisList(candies);
        }
    }

    public static void main(String[] args) {
        int numOfCSVs = 2;
        String[] CSVNames = new String[] {"DreamCandy1.csv", "DreamCandy2.csv"};

        if(args.length != 0) {
            numOfCSVs = Integer.parseInt(args[0]);
            CSVNames = new String[args.length - 1];
            for(int i = 1; i < args.length; i++) {
                CSVNames[i - 1] = args[i];
            }
        }
        
        HalloweenNeighborhoodTraversal traversal = new HalloweenNeighborhoodTraversal();
        traversal.findAndPrintAllTraversals(numOfCSVs, CSVNames);
    }
}
