package edu.neu.ccs.cs5010;

public class HouseVisitor implements Visitor {
    private boolean hasDesiredCandy = false;
    private Candy desiredCandy;
    private String curStep = "";
    private CandyTreeNode root;

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
        root = household.getRoot();
        this.hasDesiredCandy = hasThisCandy(desiredCandy);
        if(this.hasDesiredCandy) {
            prettyPrintStep(desiredCandy, household);
        }
    }

    private boolean hasThisCandy(Candy desiredCandy) {
        String[] attributes = new String[] {desiredCandy.getSize(), desiredCandy.getType()};
        CandyTreeNode cur = root;
        for(String attr : attributes) {
            boolean hasAttr = false;
            for(CandyTreeNode child : cur.children) {
                hasAttr = child.val.equals(attr);
                cur = child;
                if(hasAttr) break;
            }
            if(!hasAttr) { return false; }
        }
        return true;
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
