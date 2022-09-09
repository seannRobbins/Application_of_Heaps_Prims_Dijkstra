# Application_of_Heaps_Prims_Dijkstra
This project is an application of Prim's and Djkstra's algorithms for finding the minimum spanning tree and shortest path respectfully.
Below is the description of the problem:

You are trying to plan a road trip through a deserted wasteland in your car. You are a great
mechanic and can upgrade your tank size with necessary parts. In this wasteland, there are several
gas stations with unlimited free gasoline and parts to upgrade your tank. Your job is to figure out
whether the road trip you are planning is feasible.
There are n gas stations numbered from 0 to n − 1. You initially have a car that has a tank
size that can travel x miles. Each gas station also has a value k ≥ 0 indicating the value of the
parts available at that place that can upgrade your car to travel an additional k miles. You can
only use these parts once, but it permanently upgrades your the size of your gas tank.
Each gas station is located at a lattice point (x, y) where x and y are both integer values, measured
in miles from each other. Starting at some gas station s, find if you are able to reach another gas
station t. (You can use the parts at your start station to upgrade your car)
In this project, you will implement a minimum heap with the distance variable in GasStation.
You will implement an algorithm to find all reachable stations given a beginning station and tank
size. You will also implement a method to find the minimum tank size you will need in order to go
from one station to another. 

Part 1: Implement a Heap
Complete Heap by implementing the following methods, you can add methods if needed:
• void buildHeap(ArrayList<GasStaiony> stations)
Given an ArrayList of GasStations, build a minimum heap in O(n) time based on each
GasStation’s distance.
• void insertNode(GasStation in)
Insert a GasStation in O(log(n)) time.
• GasStation findMin()
Find minimum in O(1) time.
• GasStation extractMin()
Extract minimum in O(log(n)) time.
• void delete(int index)
Delete the GasStation at a given index in O(log(n)) time.
• void changeKey(GasStation c, double newCost)
Updates a GasStation and rebalances the Heap in O(log(n)) time. (HINT: Try to be more
efficient than scanning the entire heap every time you want to update a GasStation.)

Part 2: Finding All Reachable Gas Stations [25 points]
Implement an algorithm to find the all reachable gas stations in the map (including the start gas
station itself) given an initial tak size. You will be heavily penalized for a non-polynomial time (in
terms of the number of stations) algorithm. The specific inputs provided and outputs expected are
provided below.
Input(s):
• A starting gas station GasStation start
• Initial size of the gas tank int init size
Output:
• An ArrayList<GasStation> Reachable where Reachable represents all the gas stations you
can get to from GasStation start.
Method Signature:
• ArrayList<GasStation> findAllReachableStations(GasStation start,int init size);

Part 3: Finding Minimum Tank Size [25 points]
Implement an algorithm to find the smallest tank size needed to get from a start gas station to an
end gas station.
Input(s):
• A starting gas station GasStation start
• A destination gas station GasStation dest
Output:
• An int tankSize where the returned value tankSize represents the minimum gas tank size
needed at the beginning of the trip in order to reach the destination gas station start. You
should also use the upgrade available at the start station. Note that the distance calculated
between gas stations should be double, so round up int tankSize at the end.
