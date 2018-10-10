// DO NOT EDIT THIS INTERFACE

public interface PriorityQueueADT<E extends Comparable<E>>
{

    /**
     * Checks if the priority queue has any
     * elements and returns true if no elements,
     * false otherwise.
     *
     * @return true if no elements in queue, false otherwise.
     */
    boolean isEmpty();

    /**
     * Adds a data item to the priority queue.
     * Reorders all the other data items in the
     * queue accordingly.
     *
     * If the size if equal to the capacity of the
     * priority queue, double the capacity and then
     * add the new item.
     *
     * @param item the item to add
     * @throws IllegalArgumentException if item is null
     */
     void insert(E item);

    /**
     * Returns the highest priority item in the priority queue.
     *
     * MinPriorityQueue => it will return the smallest valued element.
     * MaxPriorityQueue => it will return the largest valued element.
     *
     * @return the highest priority item in the priority queue.
     * @throws EmptyQueueException if priority queue is empty.
     */
    E getMax() throws EmptyQueueException;

    /**
     * Returns and removes the highest priority item in the priority queue.
     * Reorders all the other data items in the
     * queue accordingly.
     *
     * MinPriorityQueue => it will return and remove the smallest valued element.
     * MaxPriorityQueue => it will return and remove the largest valued element.
     *
     * @return the highest priority item in the priority queue.
     * @throws EmptyQueueException if priority queue is empty.
     */
    E removeMax throws EmptyQueueException();

    /**
     * Returns the number of elements in the priority queue.
     *
     * @return number of elements in the queue.
     */
    int size();
}




//import java.util.Arrays;
//
/// **
//* GENERAL DIRECTIONS -
//*
//* 1. You may add private data fields and private methods as you see fit. 2.
//* Implement ALL the methods defined in the PriorityQueueADT interface. 3. DO
//* NOT change the name of the methods defined in the PriorityQueueADT
//interface.
//* 4. DO NOT edit the PriorityQueueADT interface. 5. DO NOT implement a shadow
//* array.
//*/
//
//public class MinPQ<E extends Comparable<E>> implements PriorityQueueADT<E> {
//private E[] items;
//private static final int INITIAL_SIZE = 10;
//private static int size;
//private static int currSize;
//
////ADD MORE DATA PRIVATE DATA FIELDS AS YOU NEED.
//
//public MinPQ() {
//this.items = (E[]) new Comparable[INITIAL_SIZE];
//this.size = 0;
////position item is inserted at
//this.currSize = INITIAL_SIZE;
////TO-DO: Complete the constructor for any private data fields that you
//}
//
//@Override
//public boolean isEmpty() {
////TODO Auto-generated method stub
//if (this.size == 0) {
//return true;
//} else {
//return false;
//}
//}
//
//@Override
//public void insert(E item) throws IllegalArgumentException {
////TODO Auto-generated method stub
////if array will not be long enough
//if (size == INITIAL_SIZE) {
////increase size of array
//expandArray();
////add item to end of array
//items[this.size()] = item;
//heapUp();
//this.size++;
//}
//}
//
//@Override
//public E getMax() throws EmptyQueueException {
////TODO Auto-generated method stub
//if (this.size == 0)
//throw new EmptyQueueException();
////returns largest double
//return items[0];
//}
//
//@Override
//public int size() {
////TODO Auto-generated method stub
//return this.size;
//}
//
///**
//* Returns and removes the highest priority item in the priority queue.
//* Reorders all the other data items in the queue accordingly.
//*
//* MaxPriorityQueue => it will return and remove the largest valued element.
//*
//* @return the highest priority item in the priority queue.
//* @throws EmptyQueueException
//* if priority queue is empty.
//*/
//public E removeMax() throws EmptyQueueException {
//if(this.size()==0) throw new EmptyQueueException();
//E output = items[0];
//items[0] = items[this.size()-1];
//heapDown();
//this.size--;
//return output;
//}
//public void heapifyUp(){
//int index = this.size() -1;
//while(hasParent(index) && parent(index).compareTo(items[index]) > 0){
//
//}
//}
//}

