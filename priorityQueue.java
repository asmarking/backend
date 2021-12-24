import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
/*
* @author Austin Marking
* the purpose of this file is to 
* implement a priorityqueue data structure with a MinHeap.
* This file has 2 constructors, 7 methods
*/

/* this class is an implementation of a priorityqueue
* using a MinHeap and has one istance variable named heap
* that is the underlying data structure
*/
public class MyPriorityQueue<E extends Comparable<E>> {
   /*@method constructor that initializes a empty minheap
   */ 
    protected MyMinHeap<E> heap ;
    public MyPriorityQueue() {
        heap = new MyMinHeap() ;
    }
    /*@method constructor 
    *@param collection that extends generic type E
    *@exception nullpointer when collection is null
    */ 
    public MyPriorityQueue(Collection<? extends E> collection){
        if(collection==null) {
            throw new NullPointerException() ;
        }   
        for (E s : collection) {
            if(s==null) {
                throw new NullPointerException() ;
            }
        }
        heap = new MyMinHeap(collection) ;
    }
    /*@method adds element to the priorityqueue
    *@param a generic data type
    *@exception nullpointer if element is null
    */ 
    public void push(E element) {
        if(element==null) {
            throw new NullPointerException() ;
        }   
        heap.list.add(element);
        heap.percolateUp(heap.list.size()-1) ;
    }
    /*@method returns the smallest element in the min heap
    *@return the smallest emelent in the min heap
    */ 
    public E peek() {
        if(heap.list.size()==0){
            return null;
        }
        E highestPriority = heap.list.get(0) ;
        return highestPriority ;
    }
    /*@method returns and removes the smallest element 
    *in the min heap
    *@return the smallest element in the min heap
    */ 
    public E pop() {
        if(heap.list.size()==0){
            return null;
        }
        return heap.remove() ;
        }
    /*@method returns the size of the min heap
    *@return returns the size of the min heap
    */ 
    public int getLength() {
        return heap.list.size() ;
    }

    /*@method clears the priority queue
    */ 
    public void clear() {
           heap.clear() ; 
    }
}
