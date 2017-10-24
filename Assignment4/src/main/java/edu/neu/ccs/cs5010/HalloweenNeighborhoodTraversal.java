package main.java.edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class HalloweenNeighborhoodTraversal {
    private List<Visitable> households;
    private List<Candy> desiredCandies;

    private void getDesiredCandiesFromCSV() {
        CSVReader csvReader = new CSVReader();
        desiredCandies = csvReader.getDesiredCandiesFromCSV();
    }

    public HalloweenNeighborhoodTraversal() {
        households = new ArrayList<>();
        households.add(new HouseholdDetachedHouse());
        households.add(new HouseholdDuplex());
        households.add(new HouseholdMansion());
        households.add(new HouseholdTownHome());
        getDesiredCandiesFromCSV();
    }

    public void findThisCandy(Candy desiredCandy) {
        HouseholdVisitor visitor = new HouseholdVisitor(desiredCandy);
        for(Visitable household : households) {
            household.acceptVisitor(visitor);
        }
    }

    public void findAndPrintAllTraversals() {
        for(Candy c : desiredCandies) {
            findThisCandy(c);
        }
    }

    public static void main(String[] args) {
        HalloweenNeighborhoodTraversal traversal = new HalloweenNeighborhoodTraversal();
        traversal.findAndPrintAllTraversals();
    }
}
