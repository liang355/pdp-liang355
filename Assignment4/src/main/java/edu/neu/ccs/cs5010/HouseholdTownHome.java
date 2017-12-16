package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class HouseholdTownHome extends Household {
    private String householdName = "Townhome";
    private List<Candy> candies = new ArrayList<>();
    private CandyTreeNode root = new CandyTreeNode();

    public HouseholdTownHome() {
        addCandyToTreeInHouse(new Candy("Regular Size", "Twix"), root);
        addCandyToTreeInHouse(new Candy("Regular Size", "Snickers"), root);
        addCandyToTreeInHouse(new Candy("Regular Size", "Mars"), root);
        addCandyToTreeInHouse(new Candy("Regular Size", "Kit Kat"), root);
        addCandyToTreeInHouse(new Candy("Regular Size", "Whoopers"), root);
        addCandyToTreeInHouse(new Candy("Regular Size", "Toblerone"), root);
        addCandyToTreeInHouse(new Candy("Regular Size", "Baby Ruth"), root);
        addCandyToTreeInHouse(new Candy("Regular Size", "Almond Joy"), root);
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
