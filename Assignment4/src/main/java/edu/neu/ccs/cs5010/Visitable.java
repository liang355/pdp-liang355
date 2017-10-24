package edu.neu.ccs.cs5010;

import java.util.List;

public interface Visitable {
    public void acceptVisitor(HouseVisitor visitor);
    public List<Candy> getCandies();
    public String getHouseholdName();
}
