package main.java.edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class HouseholdTownHome implements Visitable {
    private List<Candy> candies = new ArrayList<>();

    public HouseholdTownHome() {
        candies.add(new Candy("Regular size", "Twix"));
        candies.add(new Candy("Regular size", "Snickers"));
        candies.add(new Candy("Regular size", "Mars"));
        candies.add(new Candy("Regular size", "Kit Kat"));
        candies.add(new Candy("Regular size", "Whoopers"));
        candies.add(new Candy("Regular size", "Toblerone"));
        candies.add(new Candy("Regular size", "Baby Ruth"));
        candies.add(new Candy("Regular size", "Almond Joy"));
    }

    public void acceptVisitor(HouseholdVisitor visitor) {
        visitor.visit(this);
    }

    public List<Candy> getCandies() {
        return candies;
    }
}
