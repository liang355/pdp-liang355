package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class HouseholdMansion implements Visitable {
    private String householdName = "Mansion";
    private List<Candy> candies = new ArrayList<>();

    public HouseholdMansion() {
        candies.add(new Candy("Super Size", "Twix"));
        candies.add(new Candy("Super Size", "Snickers"));
        candies.add(new Candy("Super Size", "Mars"));

        candies.add(new Candy("King Size", "Kit Kat"));
        candies.add(new Candy("King Size", "Whoopers"));
        candies.add(new Candy("King Size", "Crunch"));

        candies.add(new Candy("Fun Size", "Toblerone"));
        candies.add(new Candy("Fun Size", "Baby Ruth"));
        candies.add(new Candy("Fun Size", "Almond Joy"));
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
