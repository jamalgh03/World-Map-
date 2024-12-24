package com.example.world_map;


public class vertex {
    Cities city;
    vertex previous;
    int index;
    double Weight ;
    boolean visited;
    LinkedList<edges> e = new LinkedList<edges>();

    public vertex(Cities city, int index) {
        this.city = city;
        this.index = index;
    }

    public Cities getcity() {
        return city;
    }

    public void setcity(Cities city) {
        this.city = city;
    }

    public int getindex() {
        return index;
    }

    public void setindex(int num) {
        this.index = num;
    }

    public double getDistance() {
        return Weight;
    }

    public void setDistance(double distance) {
        this.Weight = distance;
    }

    public LinkedList<edges> getE() {
        return e;
    }

    public void setE(LinkedList<edges> e) {
        this.e = e;
    }

    public boolean findEdge(String ss) {
        for (int i = 0; i < e.size(); i++) {
            if (e.get(i).getTarget().getcity().getCityname().compareToIgnoreCase(ss) == 0)
                return true;
        }
        return false;
    }

    public String toString() {
        String r = city.getCityname() + ":";
        for (int i = 0; i < e.size(); i++) {
            r = r + e.get(i).Target.city.getCityname() + ",";
        }
        return r;
    }

    // تحويل خط الطول
    public static double convertLongitude(double longitude) {
        final double XR_MIN = -180;
        final double XR_MAX = 180;
        final double XW_MIN = 0;
        final double XW_MAX = 1025;
        return ((longitude - XR_MIN) / (XR_MAX - XR_MIN)) * (XW_MAX - XW_MIN) + XW_MIN;
    }

    // تحويل خط العرض
    public static double convertLatitude(double latitude) {
        final double YR_MIN = -90;
        final double YR_MAX = 90;
        final double YW_MIN = 0;
        final double YW_MAX = 525;
        return ((latitude - YR_MIN) / (YR_MAX - YR_MIN)) * (YW_MAX - YW_MIN) + YW_MIN;
    }
}
