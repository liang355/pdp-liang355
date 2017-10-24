package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class HouseholdDetachedHouse implements Visitable {
    private String householdName = "Detached House";
    private List<Candy> candies = new ArrayList<>();

    public HouseholdDetachedHouse() {
        candies.add(new Candy("Super Size", "Kit Kat"));
        candies.add(new Candy("Super Size", "Whoopers"));
        candies.add(new Candy("Super Size", "Milky Way"));
        candies.add(new Candy("Super Size", "Crunch"));

        candies.add(new Candy("King Size", "Toblerone"));

        candies.add(new Candy("Fun Size", "Twix"));
        candies.add(new Candy("Fun Size", "Snickers"));
        candies.add(new Candy("Fun Size", "Mars"));
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
