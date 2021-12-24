/**
 *  The purpose of this file is to implement 
 * a minHeap data structure. The underlying data structure 
 * used to do this is an ArrayList.
 * 
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.*;

/**
 * @author Austin Marking
 * This class is a data structure that will allow you to store
 * elements from smallest to largest and will keep this
 * order as you add and remove elements. 
 * It has instance variable list which is of type List
 * and it is the underlying data structure of our class
 */
public class MyMinHeap<E extends Comparable<E>> {

    public static void main(String args[]) {
       List<String> str = new ArrayList<String>();
       str.add("Geeks");
       str.add("for");
       str.add("Geeks");
       str.add("Left Node of index 1") ;
       String first = str.get(0) ;
       String second = str.get(1) ;
       MyMinHeap<String> tester =  new MyMinHeap(str) ;
       tester.swap(0,1) ;
       int trueParentOfIndexOne = 0 ;
       int computedParentOfIndexOne = tester.getParentIdx(1) ;
       int minIndex = tester.getMinChildIdx(2) ;
       System.out.println(minIndex==-1) ;
     }

    protected List<E> list;

    /*@method Percolate the element at the parameter index down until no heap properties 
    *are violated by this element 
    *@param index
    */ 
    protected void percolateDown(int index) {
       int k = index ;
       int l = 2*index+1;
       while(l<this.list.size()) {
            int min=l, r=l+1 ;
            if(r<this.list.size()) { //meaning right node exist
                if(list.get(r).compareTo(list.get(l))<0) {
                    min++ ;
                }
            }
            if(list.get(k).compareTo(list.get(min))>0) {
                //switch
                E temp = list.get(k) ;
                list.set(k,list.get(min)) ;
                list.set(min,temp) ;
                k = min ;
                l = 2*k+1;
            }
            else {
                break ;
            }
        }
    }


   /*@method constructor
   */ 
    public MyMinHeap() {
        list = new ArrayList() ;
    }
   /*@method creates a min heap from a collection
    *@param a collection that extends generic type
    */ 
    public MyMinHeap(Collection<? extends E> collection) {
        if(collection==null) {
            throw new NullPointerException() ;
        }   
        for (E s : collection) {
            if(s==null) {
                throw new NullPointerException() ;
            }
        }
        list = new ArrayList(collection) ;
        int size = list.size()-1 ;
        int x = 0 ;
        while(x<=size/2) {
            this.percolateDown(x) ;
            x++ ;
        }
    }
    /*@method swaps the elements at the provided indexes
    *@param two indexes
    */ 
    protected void swap(int from, int to) {
        E fromClone = list.get(from) ;
        E toClone = list.get(to) ;
        list.set(from,toClone) ;
        list.set(to,fromClone) ;

    }
    /*@method find the index of the parent node 
    *of the node at the given index
    *@param index
    *@return index of the parent node
    */ 
    protected int getParentIdx(int index) {
        int parentIndex = (index-1)/2 ;
        return parentIndex ;
    }
    /*@method find the index of the left child
    * of the node at the given index
    *@param index
    *@return the left index 
    */
    protected int getLeftChildIdx(int index) {
        int leftChildIndex = (2*index) + 1 ;
        return leftChildIndex ; 
    }
    /*@method return right child index of the node at
    *the given index
    *@param index
    *@return index of the right child
    */ 
    protected int getRightChildIdx(int index) {
        int rightChildIndex = (2*index) + 2 ;
        return rightChildIndex ;
    }
    /*@method find the index of the smallest child 
    * of the node at the given index
    *@param index
    *@return the index of the min child
    */ 
    protected int getMinChildIdx(int index) {
        int lastIndexOfList = this.list.size() - 1 ;
        int leftChildIndex = getLeftChildIdx(index) ;
        int rightChildIndex = getRightChildIdx(index) ;
        if(leftChildIndex>lastIndexOfList) {
            return -1 ;
        }
        else if(rightChildIndex <= lastIndexOfList) {
            E leftChildVal = this.list.get(leftChildIndex) ;
            E rightChildVal = this.list.get(rightChildIndex) ;
            int whoBigger = leftChildVal.compareTo(rightChildVal) ;
            if(whoBigger<=0) {
                return leftChildIndex ;
            }
            return rightChildIndex ;
        }
        else {
            return leftChildIndex ;
        }
    }
       
    /*@method Percolate the element at the parameter index up 
    *until no heap properties are violated by this element
    *@param index
    */ 
    protected void percolateUp(int index) {
       // int parentIndex = getParentIdx(index) ;
       // E parentNodeVal = this.list.get(parentIndex) ;
       // E givenIndexVal = this.list.get(index) ;
        while (index > 0) { 
             int parentIndex = getParentIdx(index) ;
             E parentNodeVal = this.list.get(parentIndex) ;
             E indexNodeVal = this.list.get(index) ;
             if(indexNodeVal.compareTo(parentNodeVal)<0) {
                 this.list.set(index,parentNodeVal) ;
                 this.list.set(parentIndex,indexNodeVal);
                 index = parentIndex ;
             }
             else {
                 break ;
             }
        }

    }
   /*@method remove the element at the specified index from list and return it
    *@param index
    */ 
    protected E deleteIndex(int index) {
        E original = list.get(index);
        list.remove(index) ;
        list.set(index,list.get(list.size()-1)) ;
        if(list.get(index).compareTo(original)
        <0) {
            percolateUp(index) ;
            return original ;
        }
        else if(list.get(index).compareTo(original)==0) {
                 return original ;
             }
        else {
            percolateDown(index) ;
            return original;
        }

    }
    /*@method Otherwise, add element to the end of the heap 
    *@param an element of type E 
    *@exception IllegalArgumentException if element is null
    */ 
    public void insert(E element) {
        if(element==null) {
            throw new IllegalArgumentException() ;
        }
        this.list.add(element) ;
        int lastIndex = this.list.size() - 1 ;
        this.percolateUp(lastIndex) ;
    }

    /*@method return the root
    *@return return the root
    */ 
    public E getMin() {
        if(list.size()==0) {
            return null ;
        }
        return this.list.get(0) ;
        
    }
    /*@method Remove and return the root (this will be the smallest) element in the heap
    *@return the root or null if the heap is empty
    */ 
    public E remove() {
        if(this.list.size()==0) {
            return null ;
        }
      E hold = list.get(0) ;
      swap(0,list.size()-1);
      list.remove(list.size()-1) ;
      percolateDown(0) ;
      return hold;
    }
    /*@method returns the number of elements in the min heap
    *@return number of elements in the min heap
    */
    public int size() {
        return this.list.size() ;
    } 
    /*@method clear out the entire heap
    */ 
    public void clear() {
        this.list = new ArrayList() ;
    }		
}
