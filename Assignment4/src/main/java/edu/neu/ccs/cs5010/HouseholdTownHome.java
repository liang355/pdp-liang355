package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing historic knowledge of town home type as tree
 */
public class HouseholdTownHome extends Household {
    private String householdName = "Townhome";
    private List<Candy> candies = new ArrayList<>();
    private CandyTreeNode root = new CandyTreeNode();

    /**
     * Constructor that encodes historic knowledge
     * at instance initialization
     */
    public HouseholdTownHome() {
        // encoding with tree
        addCandyToTreeInHouse(new Candy("Regular Size", "Twix"), root);
        addCandyToTreeInHouse(new Candy("Regular Size", "Snickers"), root);
        addCandyToTreeInHouse(new Candy("Regular Size", "Mars"), root);
        addCandyToTreeInHouse(new Candy("Regular Size", "Kit Kat"), root);
        addCandyToTreeInHouse(new Candy("Regular Size", "Whoopers"), root);
        addCandyToTreeInHouse(new Candy("Regular Size", "Toblerone"), root);
        addCandyToTreeInHouse(new Candy("Regular Size", "Baby Ruth"), root);
        addCandyToTreeInHouse(new Candy("Regular Size", "Almond Joy"), root);
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
