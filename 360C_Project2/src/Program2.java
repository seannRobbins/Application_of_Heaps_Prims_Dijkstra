/*
 * Name: <your name>
 * EID: <your EID>
 */

// Implement your algorithms here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;
import java.util.LinkedList;

public class Program2 {
    private ArrayList<GasStation> stations;  // this is a list of all Cities, populated by Driver class
    private Heap minHeap;

    // additional constructor fields may be added, but don't delete or modify anything already here
    public Program2() {
        minHeap = new Heap();
        stations = new ArrayList<GasStation>();
    }

    /**
     * Sets all stations distances to max value
     */
    private void setToMaxDistance(){
        for (int i = 0; i < stations.size(); i++){
            stations.get(i).setDistance(Double.MAX_VALUE);
        }
    }

    /**
     * Return the distance between two gas stations
     *
     * @param start - beginning station
     * @param dest - destination station
     * @return double distance between them
     */
    private double returnDis(GasStation start, GasStation dest){
        int x1 = start.getXcoordinate();
        int x2 = dest.getXcoordinate();
        int y1 = start.getYcoordinate();
        int y2 = dest.getYcoordinate();
        double xdis = (x2 - x1);
        double ydis = (y2 - y1);
        double minDis;
        double xmult = xdis*xdis;
        double ymult = ydis*ydis;
        minDis = xmult + ymult;
        minDis = Math.sqrt(minDis);
        if (minDis < dest.getDistance()) {
            return minDis;
        }else{
            return dest.getDistance();
        }
    }

//    /**
//     * Adjusts the distances of all gas stations in respect to the station current
//     * if distance from current to next is smaller than next's distance value
//     * 1. change nexts parent to current
//     * 2. change nexts distance to the distance between it and current
//     * @param current
//     *
//     * O(n)
//     *
//     */
//    private void adjustDistance(GasStation current){
//        for (int i = 0; i < stations.size(); i++){
//            if (!stations.get(i).equals(current)){
//                double distance = returnDis(current, stations.get(i));
//                if (distance < stations.get(i).getDistance()){
//                    if (minHeap.contains(stations.get(i))) {
//                        minHeap.changeKey(stations.get(i), distance);
//                        stations.get(i).setParent(current);
//                    }
//                }
//            }
//        }
//    }


    private ArrayList<GasStation> getStations(){
        ArrayList<GasStation> list = new ArrayList<>();
        for (int i = 0; i < stations.size(); i++){
            GasStation temp = new GasStation(stations.get(i).getID(), stations.get(i).getUpgradeValue(), stations.get(i).getXcoordinate(), stations.get(i).getYcoordinate());
            list.add(temp);
        }
        return list;
    }

    private boolean isReachable(int gas, GasStation current, ArrayList<GasStation> reachable){
        return (gas >= ((int)current.getPdis()) + 1)&(reachable.contains(current.getParent()));
    }

    /**
     * findAllReachableStations(GasStation start, int init_size)
     *
     * @param start     - the starting GasStation.
     * @param init_size - the initial tank size obtained
     * @return the list of all gas stations we can reach from start
     *
     * Use Prims to find minimum Spanning tree
     * 1. Find the adjacency list
     *
     *
     */
    public ArrayList<GasStation> findAllReachableStations(GasStation start,int init_size) {
        // TODO: implement this method

        //Prims Initialization--------------------------------------------------------------
        double gas_tank = init_size;
        setToMaxDistance();
        start.setDistance(0); //Prims set start distance to zero
        ArrayList<GasStation> reachable = new ArrayList<>();
        GasStation current;
        minHeap.clear();
        minHeap.buildHeap(stations);
        while(minHeap.size() != 0){
            current = minHeap.extractMin();
            for (GasStation elem : minHeap.getMinHeap()){
                minHeap.changeKey(elem, returnDis(current, elem));
            }
            if (current.getDistance() <= gas_tank){
                reachable.add(current);
                gas_tank += current.getUpgradeValue();
            }else{
                break;
            }
        }
        return reachable;
    }

    /**
     * findMinimumTankSize()
     * @param start  - the starting GasStation
     * @param dest   - the destination Gas Station
     * @return The minimum gas tank size needed at the beginning of the trip
     *
     * USING PRIM's ALGORITHM
     * O(n^2logn)
     */
    public int findMinimumTankSize(GasStation start, GasStation dest) {
        // TODO: implement this method
        //Prims Initialization--------------------------------------------------------------
        setToMaxDistance();//Prims set all pdis,distance to max and parent to null
        double gas_val = 0; //total current gas tank value
        double gas_up = 0;
        double gas_min = 0; //current minimum starting gas tank requirement
        start.setDistance(0);
        GasStation current;
        minHeap.clear();
        minHeap.buildHeap(stations); //build minheap based off of pdis

        while (minHeap.size() != 0){
            current = minHeap.extractMin();
            for (GasStation elem : minHeap.getMinHeap()){
                minHeap.changeKey(elem, returnDis(current, elem));
            }
            if (current.getDistance() > gas_val){
                gas_min += (current.getDistance() - gas_val);
            }
            gas_up += current.getUpgradeValue();
            gas_val = gas_up + gas_min;
            if (current.equals(dest)){
                break;
            }
        }

        int temp = (int)gas_min;
        if ((double)temp == gas_min){
            return temp;
        }else {
            return (int) gas_min + 1;
        }
    }

    //return the gas station id and its upgrade value
    //this function can be altered for your debugging purpose
    public String toString() {
        String o = "";
        for (GasStation v : stations) {
            boolean first = true;
            o += "Gas Station ";
            o += v.getID();
            o += " has upgrade value ";
            o += v.getUpgradeValue();
            o += System.getProperty("line.separator");

        }

        return o;
    }

///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

    public Heap getHeap() {
        return minHeap;
    }

    public ArrayList<GasStation> getAllStations() {
        return stations;
    }


    // used by Driver.java and sets cities to reference an ArrayList of all RestStops
    public void setAllNodesArray(ArrayList<GasStation> x) {
        stations = x;
    }
}


/*
Problem 3 first try
ArrayList<GasStation> Reachable = new ArrayList<>(); //return array of stations
        start.setDistance(0);
        createDistances1(start); //create all distances from start O(n)
minHeap.buildHeap(stations); //build a heap O(nlogn)
//      minHeap.insertNode(start); //put start in the heap O(logn)
        GasStation g; //temp var
        for (int i = 0; i < minHeap.size() - 1; i++){ //go through entire list of stations O(n)
            g = minHeap.extractMin(); //get the current closest station to start O(logn)
            if (g.getDistance() <= init_size){
                Reachable.add(g); //add the gasStation to reachable
            }else{ //all other stations are too far from start
                break;
            }
        }
        minHeap.clear();
        return Reachable;
 */