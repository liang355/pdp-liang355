package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class HouseholdDuplex extends Household{
    private String householdName = "Duplex";
    private List<Candy> candies = new ArrayList<>();
    private CandyTreeNode root = new CandyTreeNode();

    public HouseholdDuplex() {
        addCandyToTreeInHouse(new Candy("Super Size", "Toblerone"), root);
        addCandyToTreeInHouse(new Candy("Super Size", "Baby Ruth"), root);
        addCandyToTreeInHouse(new Candy("Super Size", "Almond Joy"), root);

        addCandyToTreeInHouse(new Candy("King Size", "Twix"), root);
        addCandyToTreeInHouse(new Candy("King Size", "Snickers"), root);
        addCandyToTreeInHouse(new Candy("King Size", "Mars"), root);

        addCandyToTreeInHouse(new Candy("Fun Size", "Kit Kat"), root);
        addCandyToTreeInHouse(new Candy("Fun Size", "Whoopers"), root);
        addCandyToTreeInHouse(new Candy("Fun Size", "Milky Way"), root);
        addCandyToTreeInHouse(new Candy("Fun Size", "Crunch"), root);
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
