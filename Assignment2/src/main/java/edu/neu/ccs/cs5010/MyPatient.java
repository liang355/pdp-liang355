package edu.neu.ccs.cs5010;

public class MyPatient implements IPatient {
    private long arrivalTime;
    private int urgency;
    private long duration;

    public MyPatient(long arrivalTime, long duration, int urgency) {
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.urgency = urgency;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public int getUrgency() {
        return urgency;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public int compareTo(IPatient o) {
        return Integer.compare(this.urgency, o.getUrgency());
    }
}
