package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing historic knowledge of detached house type as tree
 */
public class HouseholdDetachedHouse extends Household {
    private String householdName = "Detached House";
    private List<Candy> candies = new ArrayList<>();
    private CandyTreeNode root = new CandyTreeNode();

    /**
     * Constructor that encodes historic knowledge
     * at instance initialization
     */
    public HouseholdDetachedHouse() {
        // encoding with tree
        addCandyToTreeInHouse(new Candy("Super Size", "Kit Kat"), root);
        addCandyToTreeInHouse(new Candy("Super Size", "Whoopers"), root);
        addCandyToTreeInHouse(new Candy("Super Size", "Milky Way"), root);
        addCandyToTreeInHouse(new Candy("Super Size", "Crunch"), root);

        addCandyToTreeInHouse(new Candy("King Size", "Toblerone"), root);

        addCandyToTreeInHouse(new Candy("Fun Size", "Twix"), root);
        addCandyToTreeInHouse(new Candy("Fun Size", "Snickers"), root);
        addCandyToTreeInHouse(new Candy("Fun Size", "Mars"), root);
    }

    /**
     * Method that allows Visitor object to visit/access this current class object
     * @param visitor the visitor object
     */
    public void acceptVisitor(HouseVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Getter method for field root
     * @return the tree node encoded with historic knowledge of this household type
     */
    public CandyTreeNode getRoot() {
        return root;
    }

    /**
     * Getter method for field householdName
     * @return the name of the household type
     */
    public String getHouseholdName() {
        return householdName;
    }
}
