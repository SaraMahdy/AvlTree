package BalancedTree;
import java.util.*;
import java.lang.*;
public class AvlTree <T extends Comparable<T>>{

    private static final int ALLOWED_IMBALANCE_FACTOR = 1;
    public AvlNode<T> root;
    private int size;
   public AvlTree(){
       root=null;
   }
    public int getSize() {
        if (root==null)return 0;
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public void printTree(AvlNode<T> root) {
        if (root == null) {
            return;
        }
              System.out.println("PRINT:" + root.getElement());
        printTree(root.getLeftChild());
  
        printTree(root.getRightChild());
    }
    /***********************************Required Methods********************************/
    public void insert(T item)  {
        size++;
        insert(item, root);
    }
    public void printHight(){
        System.out.println(height(root));
    }
    public void delete(T item) throws Exception {
        size--;
        delete(item, root);
    }
     public AvlNode<T> search(T item) throws Exception {
        return search(item, root);
    }
     /**********************************************************************************/

    private AvlNode<T> delete(T item, AvlNode<T> t) throws Exception {
        if (t == null) {
            throw new Exception();
        }//not found 
        
        int compare =t.getElement().compareTo(item);
        if (compare > 0) {
            t.setLeftChild(delete(item, t.getLeftChild()));
        } else if (compare < 0) {
            t.setRightChild(delete(item, t.getRightChild()));
        } else if (t.getLeftChild() != null && t.getRightChild() != null) {
            t.setElement((T) (leftMost(t.getRightChild())).getElement());
            t.setRightChild(delete(t.getElement(), t.getRightChild()));
        } else {
            if(t==root){
                root= (t.getLeftChild() != null) ? t.getLeftChild() : t.getRightChild();
            }
            t = (t.getLeftChild() != null) ? t.getLeftChild() : t.getRightChild();
            
        }

        return balance(t);

    }
    private AvlNode<T>leftMost(AvlNode<T>t){
        
        while(t.getLeftChild()!=null){
            t=t.getLeftChild();
        }
        return t;
    }
   

    private AvlNode<T> search(T item, AvlNode<T> t) throws Exception {//search for an element ,if found return the node, o.w null
        AvlNode<T> found = null;
        if (root == null) {
            throw new Exception();
        }
        if (t == null) {
            return null;
        } else {
            int compare=t.getElement().compareTo(item);
            if (compare==0) {
                return found = t;
            } else if (compare>0) {
                found = search(item, t.getLeftChild());
            } else {
                found = search(item, t.getRightChild());
            }
        }
        return found;
    }

   

    private int height(AvlNode<T> node) {
        return node == null ? -1 : node.getHeight();
    }

    // internal method to insert into a subtree
    private AvlNode<T> insert(T item, AvlNode<T> subtree_root)  {//return new root of the sub tree
        if (this.root == null) {
            this.root = new AvlNode<>(item, null, null);
        }
        if (subtree_root == null) {
            return subtree_root = new AvlNode<>(item, null, null);
        }

        int compareResult = subtree_root.getElement().compareTo(item);

        if (compareResult >0) {
            subtree_root.setLeftChild(insert(item, subtree_root.getLeftChild()));

        } else if (compareResult < 0) {
            subtree_root.setRightChild(insert(item, subtree_root.getRightChild()));
        }
        else{
          
        }

        return balance(subtree_root);

    }
    // Get Balance factor of node N

    private AvlNode<T> balance(AvlNode<T> t) {
        if (t == null) {
            return null;
        }
        if (height(t.getLeftChild()) - height(t.getRightChild()) > ALLOWED_IMBALANCE_FACTOR) {
            if (height(t.getLeftChild().getLeftChild()) >= height(t.getLeftChild().getRightChild())) {//left left
                t = rotateWithLeftChild(t);

            } else {
                t = doubleWithLeftChild(t);

            }
        } else if (height(t.getRightChild()) - height(t.getLeftChild()) > ALLOWED_IMBALANCE_FACTOR) {
            if (height(t.getRightChild().getRightChild()) >= height(t.getRightChild().getLeftChild())) {//left left
                t = rotateWithRightChild(t);

            } else {
                t = doubleWithRightChild(t);
            }
        }
        t.setHeight( Math.max(height(t.getLeftChild()), height(t.getRightChild())) + 1);

        return t;
    }

    /**
     * ***************Rotation Cases**************************
     */
    // Single Rotation Case 1 :
    // rotating k1 and k2(old subtree root)
    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode k1 = k2.getLeftChild();
        k2.setLeftChild(k1.getRightChild());
        k1.setRightChild(k2);
        k2.setHeight(Math.max(height(k2.getRightChild()), height(k2.getLeftChild())) + 1);
        k1.setHeight(Math.max(height(k1.getRightChild()), height(k1.getLeftChild())) + 1);
        if (k2 == root) {
            root = k1;
        }
        return k1;

    }

    // Single Rotation Case 4 :
    // rotating k1 and k2(old subtree root, first node with balance problem)

    private AvlNode<T> rotateWithRightChild(AvlNode<T> k2) {

        AvlNode k1 = k2.getRightChild();
        k2.setRightChild(k1.getLeftChild());
        k1.setLeftChild(k2);
        k2.setHeight(Math.max(height(k2.getRightChild()), height(k2.getLeftChild())) + 1);
        k1.setHeight(Math.max(height(k1.getRightChild()), height(k1.getLeftChild())) + 1);
        if (k2 == root) {
            root = k1;
        }

        return k1;

    }

    //Double Rotation Case 2:
    // k3: old subtree root : first node with balance problem
    // 2 consecutive single rotations: k3 left with its right then k3 with its new left

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.setLeftChild(rotateWithRightChild(k3.getLeftChild()));
        return rotateWithLeftChild(k3);
    }

    //Double Rotation Case 3:
    // k3: old subtree root : first node with balance problem
    // 2 consecutive single rotations: k3 right with its left then k3 with its new right
    private AvlNode<T> doubleWithRightChild(AvlNode<T> k3) {
        k3.setRightChild(rotateWithLeftChild(k3.getRightChild()));
        return rotateWithRightChild(k3);
    }

}
