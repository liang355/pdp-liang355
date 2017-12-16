package edu.neu.ccs.cs5010;

public abstract class Household implements Visitable {

    public void addCandyToTreeInHouse(Candy candy, CandyTreeNode root) {
        String[] attributes = new String[] {candy.getSize(), candy.getType()};

        // add candy size and candy type to form a path in the tree
        CandyTreeNode cur = root;
        for(String attr : attributes) {
            CandyTreeNode newNode = null;
            for(CandyTreeNode child : cur.children) {
                if(child.val.equals(attr)) {
                    newNode = child;
                    break;
                }
            }
            if(newNode == null) {
                newNode = new CandyTreeNode(attr);
                cur.children.add(newNode);
            }
            cur = newNode;
        }
    }
}
