package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class HouseholdDetachedHouse extends Household {
    private String householdName = "Detached House";
    private List<Candy> candies = new ArrayList<>();
    private CandyTreeNode root = new CandyTreeNode();

    public HouseholdDetachedHouse() {
        // encoding with tree
        addCandyToTreeInHouse(new Candy("Super Size", "Kit Kat"), root);
        addCandyToTreeInHouse(new Candy("Super Size", "Whoopers"), root);
        addCandyToTreeInHouse(new Candy("Super Size", "Milky Way"), root);
        addCandyToTreeInHouse(new Candy("Super Size", "Crunch"), root);

        addCandyToTreeInHouse(new Candy("King Size", "Toblerone"), root);

        addCandyToTreeInHouse(new Candy("Fun Size", "Twix"), root);
        addCandyToTreeInHouse(new Candy("Fun Size", "Snickers"), root);
        addCandyToTreeInHouse(new Candy("Fun Size", "Mars"), root);
    }

    public void acceptVisitor(HouseVisitor visitor) {
        visitor.visit(this);
    }

    public CandyTreeNode getRoot() {
        return root;
    }

    public List<Candy> getCandies() {
        return candies;
    }

    public String getHouseholdName() {
        return householdName;
    }
}
