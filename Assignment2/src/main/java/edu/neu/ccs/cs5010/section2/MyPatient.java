package edu.neu.ccs.cs5010.section2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyPatient implements IPatient {
    private long arrivalTime;
    private long examStartTime = -1;
    private long duration;
    private long urgency;
    private long rank = arrivalTime;


    public MyPatient(long arrivalTime, long duration, int urgency) {
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.urgency = urgency;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public long getExamStartTime() {
        return examStartTime;
    }

    public long getUrgency() {
        return urgency;
    }

    public long getDuration() {
        return duration;
    }

    public long getRank() {
        return rank;
    }

    public void setExamStartTime(long examStartTime) {
        this.examStartTime = examStartTime;
    }

    public void setRankToArrivalTime() {
        this.rank = this.arrivalTime;
    }

    public void setRankToUrgency() {
        this.rank = this.urgency;
    }

    @Override
    public int compareTo(IPatient o) {
        if(this.rank < o.getRank()) {
            return -1;
        } else if(this.rank < o.getRank()) {
            return 1;
        } else {
            return Long.compare(this.arrivalTime, o.getArrivalTime());
        }
    }

    @Override
    public String toString() {
        return "[" + Long.toString(urgency) + ", " + arrivalTime % 1000000 + "]";
    }
}
