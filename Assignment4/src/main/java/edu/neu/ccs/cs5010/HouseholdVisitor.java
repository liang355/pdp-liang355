package main.java.edu.neu.ccs.cs5010;

import java.util.List;

public class HouseholdVisitor implements Visitor {
    private boolean hasDesiredCandy = false;
    private Candy desiredCandy;

    public HouseholdVisitor(Candy desiredCandy) {
        this.desiredCandy = desiredCandy;
    }

    public void visit(HouseholdDetachedHouse detachedHouse) {
        visitHelper(detachedHouse);
    }

    public void visit(HouseholdDuplex duplex) {
        visitHelper(duplex);
    }

    public void visit(HouseholdMansion mansion) {
        visitHelper(mansion);
    }

    public void visit(HouseholdTownHome townHome) {
        visitHelper(townHome);
    }

    private void visitHelper(Visitable household) {
        for(Candy c : household.getCandies()) {
            if(c.equalsTo(desiredCandy)) {
                this.hasDesiredCandy = true;
                prettyPrint(c, household);
                break;
            }
        }
    }

    private void prettyPrint(Candy candy, Visitable household) {
        // Pretty print here
    }

    public boolean getHasDesiredCandy() {
        return this.hasDesiredCandy;
    }
}
