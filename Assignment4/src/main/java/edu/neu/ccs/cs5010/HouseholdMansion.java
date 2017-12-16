package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class HouseholdMansion extends Household {
    private String householdName = "Mansion";
    private List<Candy> candies = new ArrayList<>();
    private CandyTreeNode root = new CandyTreeNode();

    public HouseholdMansion() {
        addCandyToTreeInHouse(new Candy("Super Size", "Twix"), root);
        addCandyToTreeInHouse(new Candy("Super Size", "Snickers"), root);
        addCandyToTreeInHouse(new Candy("Super Size", "Mars"), root);

        addCandyToTreeInHouse(new Candy("King Size", "Kit Kat"), root);
        addCandyToTreeInHouse(new Candy("King Size", "Whoopers"), root);
        addCandyToTreeInHouse(new Candy("King Size", "Crunch"), root);

        addCandyToTreeInHouse(new Candy("Fun Size", "Toblerone"), root);
        addCandyToTreeInHouse(new Candy("Fun Size", "Baby Ruth"), root);
        addCandyToTreeInHouse(new Candy("Fun Size", "Almond Joy"), root);
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
