package main.java.edu.neu.ccs.cs5010;

import java.util.List;

public interface Visitor {
    public void visit(HouseholdDetachedHouse detachedHouse);

    public void visit(HouseholdDuplex duplex);

    public void visit(HouseholdMansion mansion);

    public void visit(HouseholdTownHome townHome);

    public boolean getHasDesiredCandy();
}
