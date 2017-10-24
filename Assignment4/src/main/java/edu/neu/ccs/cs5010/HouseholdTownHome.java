package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class HouseholdTownHome implements Visitable {
    private String householdName = "Townhome";
    private List<Candy> candies = new ArrayList<>();

    public HouseholdTownHome() {
        candies.add(new Candy("Regular Size", "Twix"));
        candies.add(new Candy("Regular Size", "Snickers"));
        candies.add(new Candy("Regular Size", "Mars"));
        candies.add(new Candy("Regular Size", "Kit Kat"));
        candies.add(new Candy("Regular Size", "Whoopers"));
        candies.add(new Candy("Regular Size", "Toblerone"));
        candies.add(new Candy("Regular Size", "Baby Ruth"));
        candies.add(new Candy("Regular Size", "Almond Joy"));
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
