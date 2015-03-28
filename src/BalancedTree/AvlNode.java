
package BalancedTree;

import java.util.Comparator;


    public  class AvlNode<T extends Comparable<T>> implements Comparable<AvlNode<T>> {

        private T element;
        private AvlNode<T> left;
        private AvlNode<T> right;
        private int height;
       
        public AvlNode(T theElement, AvlNode<T> lt, AvlNode<T> rt) {
            super();
            element = theElement;
            left = lt;
            right = rt;
           
        }

        public AvlNode(T theElement) {
            this(theElement, null, null);
        }

    public void setHeight(int height) {
        this.height = height;
    }
   
        public AvlNode getLeftChild() {
            return left;
        }

        public void setLeftChild(AvlNode leftChild) {
            this.left = leftChild;
        }

        public AvlNode getRightChild() {
            return right;
        }

        public void setRightChild(AvlNode rightChild) {
            this.right = rightChild;
        }

        public T getElement() {
            return element;
        }
       
        public void setElement(T element) {
            this.element = element;
        }

        public int getHeight() {
            return height;
        }

   
@Override
	public int compareTo(AvlNode<T> o) {
		return this.element.compareTo(o.element);
	}


    }
