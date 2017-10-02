package edu.neu.ccs.cs5010;

public class MyPatient implements Comparable<MyPatient> {
    public int compareTo(MyPatient o) {
        return Integer.compare(this.urgency, o.urgency);
    }

    private int arrivalTime;
    private int urgency;
    private int duration;
}
