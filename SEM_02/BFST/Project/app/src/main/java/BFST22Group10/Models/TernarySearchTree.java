package BFST22Group10.Models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

// Inspiration https://dev.to/mlarocca/prefix-search-with-ternary-search-trees-java-implementation-2jdh
public class TernarySearchTree implements Serializable {
    public static final long serialVersionUID = 15;
    private String key;
    private Address address;
    private TernarySearchTree left;
    private TernarySearchTree middle;
    private TernarySearchTree right;

    private void insertWord(String word, int index, Address address) {
        if(this.key == null) {
            this.key = word.substring(index, index + 1).intern();
        } else if (reducedTreeIsExpandable(word, index)) {
            expandTree(word, index);
        }

        if(word.substring(index).equals(this.key)) {
            this.address = address;
        }
        else {
            insertWordInTheCorrectChild(word, index, address);
        }
    }

    private void insertWordInTheCorrectChild(String word, int index, Address address) {
        if (word.substring(index).startsWith(this.key)) {
            this.middle = createNewTreeIfNullAndInsertWord(this.middle, word, index + this.key.length(), address);
        } else if (word.charAt(index) < this.key.charAt(0)){
            this.left = createNewTreeIfNullAndInsertWord(this.left, word, index, address);
        } else if (word.charAt(index) > this.key.charAt(0)){
            this.right = createNewTreeIfNullAndInsertWord(this.right, word, index, address);
        }
    }

    private boolean reducedTreeIsExpandable(String word, int index) {
        return this.key.length() > 1 && this.key.charAt(0) == word.charAt(index);
    }

    private void expandTree(String word, int index) {
        int cutOffIndex = index;
        while (cutOffIndex < word.length() && word.charAt(cutOffIndex) == this.key.charAt(cutOffIndex - index)) {
            cutOffIndex++;
        }

        if (cutOffIndex > index) {
            TernarySearchTree newMiddle = TernarySearchTree.copy(this);
            this.left = null;
            this.middle = newMiddle;
            this.right = null;
            this.address = null;
            middle.key = this.key.substring(cutOffIndex - index).intern();
            this.key = word.substring(index, cutOffIndex).intern();
        }
    }

    private static TernarySearchTree copy(TernarySearchTree original) {
        TernarySearchTree returnTree = new TernarySearchTree();
        returnTree.address = original.address;
        returnTree.left = original.left;
        returnTree.middle = original.middle;
        returnTree.right = original.right;
        return returnTree;
    }

    private TernarySearchTree createNewTreeIfNullAndInsertWord(TernarySearchTree tree, String word, int index, Address address) {
        if (tree == null) tree = new TernarySearchTree();
        tree.insertWord(word, index, address);
        return tree;
    }
    
    // DFS
    public void reduceTree(){
        while(this.isReducible()){
            this.key = (this.key + this.middle.key).intern();
            this.address = this.middle.address;
            this.middle = this.middle.middle;
        }

        reduceChildren();
    }

    private void reduceChildren() {
        if(this.left != null){
            this.left.reduceTree();
        }
        if(this.middle != null){
            this.middle.reduceTree();
        }
        if(this.right != null){
            this.right.reduceTree();
        }
    }
    
    private boolean isReducible(){
        return     this.address == null
                && this.middle != null
                && this.middle.left == null
                && this.middle.right == null;
    }
    
    private TernarySearchTree search(String word){
        if(this.key == null || word.length() == 0){
            return null;
        }
        
        if(key.startsWith(word)){
            return this;
        }

        return searchChildren(word);
    }

    private TernarySearchTree searchChildren(String word) {
        if(word.startsWith(key)){
            return this.middle == null ? null : this.middle.search(word.substring(key.length()));
        }

        if(word.compareTo(key) < 0){
            return this.left == null ? null : this.left.search(word);
        }
        else {
            return this.right == null ? null : this.right.search(word);
        }
    }
    
    // BFS
    public HashSet<Address> prefixSearch(String prefix){
        if (prefix == null) return null;
        prefix = prefix.toLowerCase(Locale.ROOT);
        HashSet<Address> addresses = new HashSet<>();
        TernarySearchTree prefixTree = search(prefix);
        if(prefixTree == null) return addresses;
        LinkedBlockingQueue<TernarySearchTree> queue = new LinkedBlockingQueue<>();

        if(prefixTree.isEndOfString()){
            addresses.add(prefixTree.address);
        }
        
        if(prefixTree.middle != null){
            queue.add(prefixTree.middle);
        }

        return addFirstTenAddresses(queue, addresses);
    }

    private HashSet<Address> addFirstTenAddresses(LinkedBlockingQueue<TernarySearchTree> queue, HashSet<Address> addresses) {
        while(!queue.isEmpty() && addresses.size() < 10) {
            TernarySearchTree tree = queue.poll();
            if(tree.isEndOfString()){
                addresses.add(tree.address);
            }
            if(tree.left != null){
                queue.add(tree.left);
            }
            if(tree.middle != null){
                queue.add(tree.middle);
            }
            if(tree.right != null){
                queue.add(tree.right);
            }
        }
        return addresses;
    }
    
    private boolean isEndOfString() {
        return this.address != null;
    }

    public void insert(Address address) {
        insertWord(address.toString().toLowerCase(Locale.ROOT), 0, address);
    }

    public Address getAddress(String address) {
        address = address.toLowerCase(Locale.ROOT);
        TernarySearchTree tree = this.search(address);
        if (tree != null && tree.isEndOfString()) {
            return tree.address;
        }
        return null;
    }
}
