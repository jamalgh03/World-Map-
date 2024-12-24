package com.example.world_map;

public class edges {
    vertex Target;
    vertex source;
    double Weight;

    public edges(vertex source, vertex Target, double Weight) {
        super();
        this.source = source;
        this.Target = Target;
        this.Weight = Weight;
    }

    public vertex getTarget() {
        return Target;
    }

    public void setTarget(vertex target) {
        Target = target;
    }

    public vertex getSource() {
        return source;
    }

    public void setSource(vertex source) {
        this.source = source;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        this.Weight = weight;
    }


}
