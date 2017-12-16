package edu.neu.ccs.cs5010;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class for Halloween Neighborhood Traversal
 */
public class HalloweenNeighborhoodTraversal {
    private List<Visitable> households;
    private String traversalString = "Candy type, Candy size, Household type\n";
    private String curCSVName = "";

    /**
     * Constructor for the class, initializing Household classes with historic data
     */
    private HalloweenNeighborhoodTraversal() {
        households = new ArrayList<>();
        households.add(new HouseholdDetachedHouse());
        households.add(new HouseholdDuplex());
        households.add(new HouseholdMansion());
        households.add(new HouseholdTownHome());
    }

    /**
     * Print the String representation of a traversal to file
     * @param traversalString the String representation of traversal for a candy list
     */
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

    /**
     * Find the given candy, if the candy can be found, log the String representation of
     * (candy size, candy type, household type) triplet in the format of CSV file row.
     * @param desiredCandy the candy to find
     * @return whether the candy can be found
     */
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

    /**
     * Find and print the traversal for the given candy list
     * @param desiredCandies the list of desired candies read from input file
     */
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

    /**
     * Find and print traversals for all candy lists read from file
     * @param numOfCSVs number of input CSV files
     * @param CSVNames an array of CSV file names in the format of "DreamCandyX.csv"
     */
    public void findAndPrintAllTraversals(int numOfCSVs, String[] CSVNames) {
        CSVReader csvReader = new CSVReader();
        for(int i = 0; i < numOfCSVs; i++) {
            this.curCSVName = CSVNames[i];
            List<Candy> candies = csvReader.getDesiredCandiesFromCSV(CSVNames[i]);
            findAndPrintTraversalForThisList(candies);
        }
    }

    /**
     * run the Halloween neighborhood traversal program.
     * @param args an array of arguments from command line
     */
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
