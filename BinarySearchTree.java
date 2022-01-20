package goldmine.easy;

import dynamicProgramming.A01_LongestPathInMatrix;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private static class Node{
        Integer val;
        Node left;
        Node right;
    }
    private Node root;
    public BinarySearchTree(){
        this.root=new Node();
    }
    public void put(int value){
        put(value,root);
    }
    private void put(int value,Node node){
        if(node.val==null)
            node.val = value;
        else{
            if(value<node.val){
                if(node.left==null) {
                    node.left = new Node();
                }
                put(value,node.left);
            }else{
                if(node.right==null){
                    node.right=new Node();
                }
                put(value,node.right);
            }
        }
    }
    public boolean contains(int value){
        return contains(value,root);
    }

    private boolean contains(int value,Node node){
        if(node==null || node.val==null)
            return false;
        else{
            if(value==node.val){
                return true;
            }else if(value<node.val){
                return contains(value,node.left);
            }else{
                return contains(value,node.right);
            }
        }
    }

    public List<Integer> inOrderTraversal(){
        final ArrayList<Integer> acc= new ArrayList<>();
        inOrderTraversal(root,acc);
        return acc;
    }
    private void inOrderTraversal(Node node,List<Integer> acc){
        if(node==null || node.val==null)
            return;
        inOrderTraversal(node.left,acc);
        acc.add(node.val);
        inOrderTraversal(node.right,acc);
    }
}
