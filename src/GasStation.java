/*
 * Name: Seann Robbins
 * EID: xxxxx
 */

// Methods may be added to this file, but don't remove anything
// Include this file in your final submission


public class GasStation {
    private int x,y; //coordicates
    private int id;
    double gas_min;
    double distance;
    boolean visited;
    private int upgrade_value;
    private GasStation parent;
    private double pdis;

    public GasStation(int _id, int _value, int _x, int _y) {
        id = _id;
        visited = false;
        upgrade_value = _value;
        x = _x;
        y = _y;
        distance = Double.MAX_VALUE;
        pdis = Double.MAX_VALUE;
        parent = null;
        gas_min = Double.MAX_VALUE;
    }

    public boolean getVisited(){return visited;}

    public void setVisited(boolean t){visited = t;}

    public void setParent(GasStation parent){
        this.parent = parent;
    }

    public GasStation getParent(){
        return parent;
    }

    public double getPdis(){return pdis;}

    public void setPdis(double dis){pdis = dis;}

    public double getDistance(){return distance;}

    public void setDistance(double dis){
        distance = dis;
    }

    public int getID() {
        return id;
    }

    public int getXcoordinate() {return x;}

    public int getYcoordinate() {return y;}

    public int[] getCoordinates(){return new int[]{x,y};}

    public int getUpgradeValue(){ return upgrade_value;}

    public String toString() {
        return "Gas Station " + getID();
    }

}
