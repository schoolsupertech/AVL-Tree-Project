/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MasterPackge;

/**
 *
 * @author EternityShiningJewel
 * @param <T>
 */
public class Node<T extends Comparable <T>> {
    private T key;
    private int BLheight;
    private Node<T> left, right;
    
    public Node() {}
    public Node(T key) {
        this.key = key;
    }
    public Node(T key, Node<T> left, Node<T> right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

// Setters
    public void setKey(T key) {
        this.key = key;
    }
    public void setBLheight(int BLheight) {
        this.BLheight = BLheight;
    }
    public void setLeft(Node<T> left) {
        this.left = left;
    }
    public void setRight(Node<T> right) {
        this.right = right;
    }
    
// Getters
    public T getKey() {
        return key;
    }
    public int getBLheight() {
        return BLheight;
    }
    public Node<T> getLeft() {
        return left;
    }
    public Node<T> getRight() {
        return right;
    }
    
// Comparable
    @SuppressWarnings("unchecked")
    public boolean equal(Object key) {
        return this.key.compareTo((T)key) == 0;
    }
    @SuppressWarnings("unchecked")
    public boolean greaterThan(Object key) {
        return this.key.compareTo((T)key) >= 1;
    }
    @SuppressWarnings("unchecked")
    public boolean lessThan(Object key) {
        return this.key.compareTo((T)key) <= -1;
    }
    
    public void printKey() {
        System.out.println(this.key);
    }
}