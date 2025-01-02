package com.YvonneHong.dataStructures.BPlusTree;

import java.util.ArrayList;
import java.util.List;

public class BPTinternalNode extends BPlusTreeNode {
    private List<BPlusTreeNode> children;  // Child nodes (either internal or leaf)

    public BPTinternalNode(int degree) {
        super(degree);
        children = new ArrayList<>();
    }

    // Override the insert method for internal nodes
    @Override
    protected void insert(int key, String value) {
        // Logic to insert key into the appropriate child
        int index = findChildIndex(key);
        BPlusTreeNode child = children.get(index);
        
        if (child.isFull()) {
            splitChild(index);
            if (key > keys.get(index)) {
                index++;
            }
        }
        
        child.insert(key, value);
    }

    // Override the search method for internal nodes
    @Override
    protected String search(int key) {
        int index = findChildIndex(key);
        return children.get(index).search(key);
    }

    // Override the delete method for internal nodes
    @Override
    protected void delete(int key) {
        int index = findChildIndex(key);
        children.get(index).delete(key);
    }

    // Override the print method for internal nodes
    @Override
    protected void printNode() {
        System.out.print("Internal Node Keys: ");
        for (int key : keys) {
            System.out.print(key + " ");
        }
        System.out.println();
        for (BPlusTreeNode child : children) {
            child.printNode();
        }
    }

    // Helper method to find the index of the appropriate child
    private int findChildIndex(int key) {
        int index = 0;
        while (index < keys.size() && key >= keys.get(index)) {
            index++;
        }
        return index;
    }

    // Split the child at a given index (this method can be extended)
    protected void splitChild(int index) {
        // Get the child to split
        BPlusTreeNode child = children.get(index);
        
        // Ensure the child is an internal node
        if (child instanceof BPTinternalNode) {
            BPTinternalNode internalChild = (BPTinternalNode) child;
            internalChild.split();
        } else {
            BPTLeafNode leafChild = (BPTLeafNode) child;
            leafChild.split();
        }
        
        // Move the middle key of the child node up into the parent node
        int middleKey = child.keys.get(child.keys.size() / 2);
        keys.add(index, middleKey);
        
        // Add the new child node (the one split off) to the children list
        BPlusTreeNode newChild;
        if (child instanceof BPTinternalNode) {
            newChild = ((BPTinternalNode) child).split();
        } else {
            newChild = ((BPTLeafNode) child).split();
        }

        children.add(index + 1, newChild);
    }

    // Split the internal node (called by splitChild)
    protected BPTinternalNode split() {
        // Determine the midpoint
        int midIndex = keys.size() / 2;
        BPTinternalNode newInternalNode = new BPTinternalNode(degree);

        // Move half of the keys and children to the new internal node
        for (int i = midIndex + 1; i < keys.size(); i++) {
            newInternalNode.addKey(keys.get(i));
        }

        for (int i = midIndex + 1; i < children.size(); i++) {
            newInternalNode.addChild(children.get(i));
        }

        // Remove the moved keys and children from the current internal node
        for (int i = midIndex; i < keys.size(); i++) {
            keys.remove(i);
        }

        for (int i = midIndex + 1; i < children.size(); i++) {
            children.remove(i);
        }

        return newInternalNode;
    }

    // Helper method to add key
    protected void addKey(int key) {
        keys.add(key);
    }

    // Helper method to add child node
    protected void addChild(BPlusTreeNode child) {
        children.add(child);
    }
}
