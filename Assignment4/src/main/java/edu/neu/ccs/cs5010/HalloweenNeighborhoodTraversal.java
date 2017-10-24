package edu.neu.ccs.cs5010;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class HalloweenNeighborhoodTraversal {
    private List<Visitable> households;
    private List<Candy> desiredCandies;
    private String traversalString = "Candy type, Candy size, Household type\n";
    private int traversalCount = 0;

    private HalloweenNeighborhoodTraversal(List<Candy> desiredCandies) {
        households = new ArrayList<>();
        households.add(new HouseholdDetachedHouse());
        households.add(new HouseholdDuplex());
        households.add(new HouseholdMansion());
        households.add(new HouseholdTownHome());
        this.desiredCandies = desiredCandies;
    }

    private void printTraversalToFile(String traversalString) {
        String filePathname = "DreamTraversal" + ++traversalCount + ".txt";
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

    public void findAndPrintTraversal() {
        for(Candy c : desiredCandies) {
            if(!findThisCandy(c)) {
                System.out.println("No traversal exists");
                return;
            }
        }
        System.out.println(this.traversalString);
        printTraversalToFile(this.traversalString);
    }

    public static void main(String[] args) {
        int numOfCSVs = 2;
        String[] CSVNames = new String[]{"DreamCandy1", "DreamCandy2"};
        CSVReader csvReader = new CSVReader(numOfCSVs, CSVNames);
        HalloweenNeighborhoodTraversal traversal = new HalloweenNeighborhoodTraversal(csvReader.getDesiredCandiesFromCSV());

        traversal.findAndPrintTraversal();
    }
}
