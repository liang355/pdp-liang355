package edu.neu.ccs.cs5010;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class HouseVisitor implements Visitor {
    private boolean hasDesiredCandy = false;
    private Candy desiredCandy;
    private String curStep = "";

    public HouseVisitor(Candy desiredCandy) {
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
                prettyPrintStep(c, household);
                break;
            }
        }
    }

    private void prettyPrintStep(Candy candy, Visitable household) {
        this.curStep = candy.getSize() + ", " + candy.getType() + ", " + household.getHouseholdName() + "\n";
    }

    public boolean hasDesiredCandy() {
        return this.hasDesiredCandy;
    }

    public String getCurStepString() {
        return this.curStep;
    }
}
