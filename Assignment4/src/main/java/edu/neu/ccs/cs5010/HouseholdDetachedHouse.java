package main.java.edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class HouseholdDetachedHouse implements Visitable {
    private List<Candy> candies = new ArrayList<>();

    public HouseholdDetachedHouse() {
        candies.add(new Candy("Super size", "Kit Kat"));
        candies.add(new Candy("Super size", "Whoopers"));
        candies.add(new Candy("Super size", "Milky Way"));
        candies.add(new Candy("Super size", "Crunch"));

        candies.add(new Candy("King size", "Toblerone"));

        candies.add(new Candy("Fun size", "Twix"));
        candies.add(new Candy("Fun size", "Snickers"));
        candies.add(new Candy("Fun size", "Mars"));
    }

    public void acceptVisitor(HouseholdVisitor visitor) {
        visitor.visit(this);
    }

    public List<Candy> getCandies() {
        return candies;
    }
}
