package edu.neu.ccs.cs5010;

/**
 * The interface that is implemented by abstract class Household
 */
public interface Visitable {
    void acceptVisitor(HouseVisitor visitor);
    String getHouseholdName();
    CandyTreeNode getRoot();
}
