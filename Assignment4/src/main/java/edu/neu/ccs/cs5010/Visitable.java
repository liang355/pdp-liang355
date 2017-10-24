package main.java.edu.neu.ccs.cs5010;

import java.util.List;

public interface Visitable {
    public void acceptVisitor(HouseholdVisitor visitor);
    public List<Candy> getCandies();
}
