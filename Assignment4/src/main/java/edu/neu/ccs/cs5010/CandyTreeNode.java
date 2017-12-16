package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

/**
 * A trie-like tree node encoding parts of candy information
 * at different level of the tree.
 *
 * For example, candy list [Super Type Twix, King Size Mars, Super Size Milky Way]
 * is encoded as the following tree:
 *      Super Type          King Size
 *      /        \              /
 *    Twix    Milky Way      Mars
 *
 */
public class CandyTreeNode {
    String val = "";
    List<CandyTreeNode> children = new ArrayList<>();


    /**
     * Default constructor
     */
    public CandyTreeNode() {}

    /**
     * Overloaded constructor that initialize val field with given value
     * @param val the value of either part of candy information (candy size, candy type, etc.)
     */
    public CandyTreeNode(String val) {
        this.val = val;
    }
}
