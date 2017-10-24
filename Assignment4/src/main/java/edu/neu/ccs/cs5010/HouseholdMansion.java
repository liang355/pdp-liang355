package main.java.edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class HouseholdMansion implements Visitable {
    private List<Candy> candies = new ArrayList<>();

    public HouseholdMansion() {
        candies.add(new Candy("Super size", "Twix"));
        candies.add(new Candy("Super size", "Snickers"));
        candies.add(new Candy("Super size", "Mars"));

        candies.add(new Candy("King size", "Kit Kat"));
        candies.add(new Candy("King size", "Whoopers"));
        candies.add(new Candy("King size", "Crunch"));

        candies.add(new Candy("Fun size", "Toblerone"));
        candies.add(new Candy("Fun size", "Baby Ruth"));
        candies.add(new Candy("Fun size", "Almond Joy"));
    }

    public void acceptVisitor(HouseholdVisitor visitor) {
        visitor.visit(this);
    }

    public List<Candy> getCandies() {
        return candies;
    }
}
