package main.java.edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class HouseholdDuplex implements Visitable{
    private List<Candy> candies = new ArrayList<>();

    public HouseholdDuplex() {
        candies.add(new Candy("Super size", "Toblerone"));
        candies.add(new Candy("Super size", "Baby Ruth"));
        candies.add(new Candy("Super size", "Almond Joy"));

        candies.add(new Candy("King size", "Twix"));
        candies.add(new Candy("King size", "Snickers"));
        candies.add(new Candy("King size", "Mars"));

        candies.add(new Candy("Fun size", "Kit Kat"));
        candies.add(new Candy("Fun size", "Whoopers"));
        candies.add(new Candy("Fun size", "Milky Way"));
        candies.add(new Candy("Fun size", "Crunch"));
    }

    public void acceptVisitor(HouseholdVisitor visitor) {
        visitor.visit(this);
    }

    public List<Candy> getCandies() {
        return candies;
    }
}
