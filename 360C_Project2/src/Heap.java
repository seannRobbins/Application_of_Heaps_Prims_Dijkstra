/*
 * Name: Seann Robbins
 * EID: xxxxx
 */

// Implement your heap here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;

public class Heap {
    private ArrayList<GasStation> minHeap;
//    int MinDist;

    public Heap() {
        minHeap = new ArrayList<GasStation>();
    }

    public ArrayList<GasStation> getMinHeap(){return minHeap;}

    public int size(){
        return minHeap.size();
    }

    public void clear(){
        minHeap.clear();
    }

    public boolean contains(GasStation current){
        return minHeap.contains(current);
    }

    /**
     * buildHeap(ArrayList<GasStation> stations)
     * Given an ArrayList of GasStation, build a min-heap keyed on each GasStation's minDist
     * Time Complexity - O(nlog(n)) or O(n)
     *
     * @param stations
     */
    public void buildHeap(ArrayList<GasStation> stations) { //O(nlogn);
        // TODO: implement this method
        for (int i = 0; i < stations.size(); i++){
            insertNode(stations.get(i));
        }
    }

    /**
     * insertNode(GasStation in)
     * Insert a GasStation into the heap.
     * Time Complexity - O(log(n))
     *
     * @param in - the GasStation to insert.
     */
    public void insertNode(GasStation in) {
        // TODO: implement this method
        minHeap.add(in);
        heapifyUp(minHeap.size() - 1, in);
    }

    /**
     * findMin()
     * Time Complexity - O(1)
     *
     * @return the minimum element of the heap.
     */
    public GasStation findMin() {
        // TODO: implement this method
        return minHeap.get(0);
    }

    /**
     * extractMin()
     * Time Complexity - O(log(n))
     *
     * @return the minimum element of the heap, AND removes the element from said heap.
     */
    public GasStation extractMin() {
        // TODO: implement this method
        GasStation s = minHeap.get(0);
        delete(0);
        return s;
    }

    /**
     * delete(int index)
     * Deletes an element in the min-heap given an index to delete at.
     * Time Complexity - O(log(n))
     *
     * @param index - the index of the item to be deleted in the min-heap.
     */
    public void delete(int index) {
        // TODO: implement this method
        minHeap.set(index, minHeap.get(minHeap.size() - 1)); //set last element into index
        minHeap.remove(minHeap.size() - 1); //remove the last element
        int parent = (index - 1)/2;
        if (minHeap.size() != 0) {
            if (minHeap.get(index).getDistance() < minHeap.get(parent).getDistance()) { //heapifyup
                heapifyUp(index, minHeap.get(index));
            } else { //heapifydown
                heapifyDown(index);
            }
        }
    }

    /**
     * changeKey(GasStation r, int newDist)
     * Changes minDist of GasStation s to newDist and updates the heap.
     * Time Complexity - O(log(n))
     *
     * @param r       - the GasStation in the heap that needs to be updated.
     * @param newDist - the new cost of GasStation r in the heap (note that the heap is keyed on the values of minDist)
     */
    public void changeKey(GasStation r, double newDist) {
        // TODO: implement this method
        int index = minHeap.indexOf(r);
        r.setDistance(newDist);
        int parent = (index - 1)/2;
        if (minHeap.get(parent).getDistance() > minHeap.get(index).getDistance()){
            heapifyUp(index, r);
        }
        else{
            heapifyDown(index);
        }
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < minHeap.size(); i++) {
            output += minHeap.get(i).getID() + " ";
        }
        return output;
    }

    private static boolean compareTo(GasStation current, GasStation other){
        return current.getDistance() > other.getDistance();
    }

    /** Heapify up(index, GasStation in)
     * given the index of the GasStation and heapify it up
     * O(log(n))
     * **/
    public void heapifyUp(int index, GasStation in){
        // TODO: implement this method
        GasStation  temp;
        int parent;
        if (index == 0){return;}
        else{
           parent = (index - 1)/2; //get the parents index
           if (compareTo(minHeap.get(parent), minHeap.get(index))){ //check keys, swap positions
                temp = in;
                minHeap.set(index, minHeap.get(parent));
                minHeap.set(parent, temp);
                heapifyUp(parent, temp);
           }
        }
    }

    /** Heapify down(index)
     * given the index of the GasStation and heapify it down
     * O(log(n))
     * **/
    public void heapifyDown(int index){
        // TODO: implement this method
        GasStation temp;
        int child1 = (2*index + 1);
        if (child1 > minHeap.size() - 1){
            return;
        }else{
            int smallKid;
            if (child1 == minHeap.size() - 1){
                smallKid = child1;
            }else {
                int child2 = (2 * index + 2);
                if (minHeap.get(child2).getDistance() > minHeap.get(child1).getDistance()){
                    smallKid = child1;
                }else{
                    smallKid = child2;
                }
            }
            if (minHeap.get(index).getDistance() > minHeap.get(smallKid).getDistance()){
                temp = minHeap.get(index);
                minHeap.set(index, minHeap.get(smallKid));
                minHeap.set(smallKid, temp);
                heapifyDown(smallKid);
            }
        }
    }

///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

    public ArrayList<GasStation> toArrayList() {
        return minHeap;
    }
}
