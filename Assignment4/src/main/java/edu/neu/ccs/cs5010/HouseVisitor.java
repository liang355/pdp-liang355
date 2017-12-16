package edu.neu.ccs.cs5010;

/**
 * The visitor class representing a desired candy type visiting different
 * household types to search for the same candy type given out by households.
 */
public class HouseVisitor implements Visitor {
    private boolean hasDesiredCandy = false;
    private Candy desiredCandy;
    private String curStep = "";
    private CandyTreeNode root; // tree representation of historical knowledge about a household type

    /**
     * The constructor for the visitor class.
     * @param desiredCandy the desired candy to search for in households
     */
    public HouseVisitor(Candy desiredCandy) {
        this.desiredCandy = desiredCandy;
    }

    /**
     * Overloaded method that visits the given type of household.
     * @param detachedHouse the household type that visitor class can visit
     */
    public void visit(HouseholdDetachedHouse detachedHouse) {
        visitHelper(detachedHouse);
    }

    /**
     * Overloaded method that visits the given type of household
     * @param duplex the household type that visitor class can visit
     */
    public void visit(HouseholdDuplex duplex) {
        visitHelper(duplex);
    }

    /**
     * Overloaded method that visits the given type of household.
     * @param mansion the household type that visitor class can visit
     */
    public void visit(HouseholdMansion mansion) {
        visitHelper(mansion);
    }

    /**
     * Overloaded method that visits the given type of household.
     * @param townHome the household type that visitor class can visit
     */
    public void visit(HouseholdTownHome townHome) {
        visitHelper(townHome);
    }

    /**
     * Helper method that defines the behavior of this visitor class at each visit,
     * as it tries to find the desired candy in a household type. If found, create a formatted
     * String representing where the candy is found.
     * @param household general household of the super type inherited by different household types
     */
    private void visitHelper(Visitable household) {
        root = household.getRoot();                        // get historic knowledge at each visit
        this.hasDesiredCandy = hasThisCandy(desiredCandy); // assign search results (boolean) to field
        if(this.hasDesiredCandy) {
            prettyPrintStep(desiredCandy, household);
        }
    }

    /**
     * Traverse tree representing the historic knowledge of the household type that
     * this visitor class is currently visiting.
     * @param desiredCandy the desired candy to search for in households
     * @return if the household currently being visited has the desired candy
     */
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

    /**
     * Create a formatted String representation of (candy size, candy type, household type) triplet.
     * @param candy the found candy type
     * @param household the household type where the given candy is found
     */
    private void prettyPrintStep(Candy candy, Visitable household) {
        this.curStep = candy.getSize() + ", " + candy.getType() + ", " + household.getHouseholdName() + "\n";
    }

    /**
     * Getter method for field hasDesiredCandy
     * @return a field indicating whether this visit has found desired candy
     */
    public boolean hasDesiredCandy() {
        return this.hasDesiredCandy;
    }

    /**
     * Getter method for field curStep
     * @return the formatted String representation of (candy size, candy type, household type) triplet.
     */
    public String getCurStepString() {
        return this.curStep;
    }
}
