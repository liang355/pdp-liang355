package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class HouseholdDuplex implements Visitable{
    private String householdName = "Duplex";
    private List<Candy> candies = new ArrayList<>();

    public HouseholdDuplex() {
        candies.add(new Candy("Super Size", "Toblerone"));
        candies.add(new Candy("Super Size", "Baby Ruth"));
        candies.add(new Candy("Super Size", "Almond Joy"));

        candies.add(new Candy("King Size", "Twix"));
        candies.add(new Candy("King Size", "Snickers"));
        candies.add(new Candy("King Size", "Mars"));

        candies.add(new Candy("Fun Size", "Kit Kat"));
        candies.add(new Candy("Fun Size", "Whoopers"));
        candies.add(new Candy("Fun Size", "Milky Way"));
        candies.add(new Candy("Fun Size", "Crunch"));
    }

    public void acceptVisitor(HouseVisitor visitor) {
        visitor.visit(this);
    }

    public List<Candy> getCandies() {
        return candies;
    }

    public String getHouseholdName() {
        return householdName;
    }
}
