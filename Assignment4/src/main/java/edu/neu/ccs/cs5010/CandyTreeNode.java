package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class CandyTreeNode {
    String val = "";
    List<CandyTreeNode> children = new ArrayList<>();

    public CandyTreeNode() {}
    public CandyTreeNode(String val) {
        this.val = val;
    }
}
