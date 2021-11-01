/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MasterPackge;

import java.util.ArrayList;

/**
 *
 * @author EternityShiningJewel
 * @param <T>
 */
public class SBT<T extends Comparable<T>> {
    private final ArrayList<T> al = new ArrayList<>();
    private Node<T> root;
    
    public SBT() {
        root = null;
    }
    
// Check root = empty
    public boolean isEmpty() {
        return this.root == null;
    }
    
    public int size() {
        return al.size();
    }
    
    public T get(int index) {
        return al.get(index);
    }
    
    private int height(Node<T> n) {
        return n == null ? -1 : n.getBLheight();
    }
    
    private void updateHeight(Node<T> n) {
        n.setBLheight(1 + Math.max(height(n.getRight()), height(n.getLeft())));
    }
    
    private int getBalance(Node<T> n) {
        return (n == null) ? -1 : height(n.getLeft()) - height(n.getRight());
    }
    
    private Node<T> rotateR(Node<T> r) {
        if(r == null) {
            return r;
        }
        
        Node<T> lNode = r.getLeft();
        Node<T> rNode = lNode.getRight();
        
        lNode.setRight(r);
        r.setLeft(rNode);
        updateHeight(r);
        updateHeight(lNode);
        
        return lNode;
    }
    
    private Node<T> rotateL(Node<T> r) {
        if(r == null) {
            return r;
        }
        
        Node<T> rNode = r.getRight();
        Node<T> lNode = rNode.getLeft();
        
        rNode.setLeft(r);
        r.setRight(lNode);
        updateHeight(r);
        updateHeight(rNode);
        
        return rNode;
    }
    
    private Node<T> rebalance(Node<T> r) {
        if(r == null) {
            return r;
        }
        
        Node<T> tmp = r;
        updateHeight(tmp);
        int balance = getBalance(r);
        
        if(balance > 1) {
            if(height(tmp.getLeft().getLeft()) > height(tmp.getLeft().getRight())) {
                tmp = rotateR(tmp);
            }
            else {
                tmp.setLeft(rotateL(tmp.getLeft()));
                tmp = rotateR(tmp);
            }
        }
        else if(balance < -1) {
            if(height(tmp.getRight().getRight()) > height(tmp.getRight().getLeft())) {
                tmp = rotateL(tmp);
            }
            else {
                tmp.setRight(rotateR(tmp.getRight()));
                tmp = rotateL(tmp);
            }
        }
        
        return tmp;
    }
    
// Insert
    public void add(T val) {
        root = insert(root, val);
    }
    private Node<T> insert(Node<T> n, T key) {
        if(n == null) {
            al.add(key);
            return new Node<>(key);
        }
        
        if(n.equal(key)) {
            return n;
        }
        else if(n.greaterThan(key)) {
            n.setLeft(insert(n.getLeft(), key));
        }
        else {
            n.setRight(insert(n.getRight(), key));
        }
        
        return rebalance(n);
    }
    
// Delete
    public void remove(T val) {
        al.remove(val);
        root = delete(root, val);
    }
    private Node<T> mostLeftChild(Node<T> node) {
        if(node.getLeft() == null) {
            return node;
        }
        return mostLeftChild(node.getLeft());
    }
    private Node<T> delete(Node<T> n, T key) {
        Node<T> tmp = n;
        
        if(tmp == null) {
            return tmp;
        }
        
        if(tmp.greaterThan(key) || tmp.equal(key)) {
            tmp.setLeft(delete(tmp.getLeft(), key));
        }
        else if(tmp.lessThan(key) || tmp.equal(key)) {
            tmp.setRight(delete(tmp.getRight(), key));
        }
        
        if(tmp.equal(key)) {
            if(tmp.getLeft() == null || tmp.getRight() == null) {
                tmp = (tmp.getLeft() == null) ? tmp.getRight() : tmp.getLeft();
            }
            else {
                Node<T> mostLeftChild = mostLeftChild(tmp.getRight());
                tmp.setKey(mostLeftChild.getKey());
                tmp.setRight(delete(tmp.getRight(), tmp.getKey()));
            }
        }

        return rebalance(tmp);
    }
    
// Search
    public void search(T key) {
        Node<T> tmp = find(root, key);
        
        if(tmp == null) {
            System.out.println("Not found");
        }
        else {
            System.out.println(tmp.getKey());
        }
    }
    private Node<T> find(Node<T> head, T key) {
        if(head != null) {
            if(head.equal(key)) {
                return head;
            }
            else if(head.greaterThan(key)) {
                return find(head.getLeft(), key);
            }
            else {
                return find(head.getRight(), key);
            }
        }
        
        return null;
    }
    
// Print path between 2 nodes
    public void print2Node(T val1, T val2) {
        getPathBetween2Node(root, val1, val2);
    }
    private void getPathBetween2Node(Node<T> r, T val1, T val2) {
        int intersection = -1;
        int i = 0, j = 0;
        
        ArrayList<T> path1 = new ArrayList<>();
        ArrayList<T> path2 = new ArrayList<>();
        
        getPath(r, path1, val1);
        getPath(r, path2, val2);
        
        while(i != path1.size() || j != path2.size()) {
            if(i >= path1.size() || i >= path2.size()) {
                intersection = j - 1;
                break;
            }
            if(i == j && path1.get(i) == path2.get(j)) {
                i++;
                j++;
            }
            else {
                intersection = j - 1;
                break;
            }
        }
        
        for(i = path1.size()-1; i > intersection; i--) {
            System.out.print(path1.get(i)+ " ");
        }
        for(i = intersection; i < path2.size(); i++) {
            System.out.print(path2.get(i)+ " ");
        }
    }
    private boolean getPath(Node<T> r, ArrayList<T> list, T val) {
        if(r == null) {
            return false;
        }
        
        list.add(r.getKey());
        
        if(r.getKey() == val) {
            return true;
        }
        
        if(getPath(r.getLeft(), list, val) || getPath(r.getRight(), list, val)) {
            return true;
        }
        
        list.remove(list.size()-1);
        
        return false;
    }
    
// Display
    public void bftRecursion(){
        if(root == null) {
            return;
        }
        
        ArrayList<Node<T>> newList = new ArrayList<>();
        
        newList.add(root);
        bftRecursion(newList, 1);
        
        System.out.println();
    }
    private void bftRecursion(ArrayList<Node<T>> list, int g){
        System.out.print("\nLevel " + g + ": ");
        
        ArrayList<Node<T>> newList = new ArrayList<>();
        
        list.stream().map(node -> {
            if(node.getLeft() != null) {
                newList.add(node.getLeft());
            }
            return node;
        }).map(node -> {
            if(node.getRight() != null) {
                newList.add(node.getRight());
            }
            return node;
        }).forEachOrdered(node -> {
            System.out.print(node.getKey() + "\t");
        });
        
        if(!newList.isEmpty()) {
            bftRecursion(newList, g+1);
        }
    }
    
    public void show() {
        inorderRec(root);
    }
    private void inorderRec(Node<T> r) {
        if(r != null) {
            inorderRec(r.getLeft());
            r.printKey();
            inorderRec(r.getRight());
        }
    }
}