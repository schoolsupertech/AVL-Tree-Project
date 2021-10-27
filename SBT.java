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
    private Node<T> root;
    private int size;
    
    public SBT() {
        root = null;
        size = 0;
    }
    
// Search
    public void search(T key) {
        if(isEmpty()) {
            System.out.println("Not found");
        }
        else {
            find(root, key).printKey();
        }
    }
    private Node<T> find(Node<T> head, T key) {
        if(head.getRight() == null || head.getLeft() == null) {
            return head;
        }
        if(head.equal(key)) {
            return head;
        }
        else if(head.lessThan(key)) {
            return find(head.getRight(), key);
        }
        else {
            return find(head.getLeft(), key);
        }
    }
    
// Check root = empty
    public boolean isEmpty() {
        return this.root == null;
    }
    
    public int size() {
        return this.size;
    }
    
    public int get(int index) {
        return index;
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
            this.size++;
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
        else {
            if((tmp.getLeft() == null) || (tmp.getRight() == null)) {
                Node<T> temp = null;
                
                temp = temp == tmp.getLeft()? tmp.getRight() : tmp.getLeft();
                if(temp == null) {
                    temp = tmp;
                    tmp = null;
                }
                else {
                    tmp = temp;
                }
            }
            else {
                Node<T> temp = mostLeftChild(tmp.getRight());
                tmp.setKey(temp.getKey());
                tmp.setRight(delete(tmp.getRight(), temp.getKey()));
                this.size--;
            }
        }
//        if(tmp.equal(key)) {
//            if(tmp.getLeft() == null || tmp.getRight() == null) {
//                tmp = (tmp.getLeft() == null) ? tmp.getRight() : tmp.getLeft();
//            }
//            else {
//                Node<T> mostLeftChild = mostLeftChild(tmp.getRight());
//                tmp.setKey(mostLeftChild.getKey());
//                tmp.setRight(delete(tmp.getRight(), tmp.getKey()));
//                this.size--;
//            }
//        }

        // If the tree had only one node then return
        if (tmp == null)
            return tmp;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        tmp.setBLheight(max(height(tmp.getRight()), height(tmp.getLeft())) + 1);

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(tmp);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(tmp.getLeft()) >= 0) {
            return rotateR(tmp);
        }

        // Left Right Case
        if (balance > 1 && getBalance(tmp.getLeft()) < 0)
        {
            tmp.setLeft(rotateL(tmp.getLeft()));
            return rotateR(tmp);
        }

        // Right Right Case
        if (balance < -1 && getBalance(tmp.getRight()) <= 0)
            return rotateL(tmp);

        // Right Left Case
        if (balance < -1 && getBalance(tmp.getRight()) > 0)
        {
            tmp.setRight(rotateR(tmp.getRight()));
            return rotateL(tmp);
        }

        return tmp;
    }
    
    private int max(int a, int b) {
        return a > b? a : b;
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