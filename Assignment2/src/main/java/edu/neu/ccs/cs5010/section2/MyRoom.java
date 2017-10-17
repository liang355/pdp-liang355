package edu.neu.ccs.cs5010.section2;

public class MyRoom implements IRoom {
    private IPatient patient = null;
    private long finishTime = -1;
    private long utilization = 0;
    private long rank = utilization;

    public IPatient getPatient() {
        return patient;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public long getUtilization() {
        return utilization;
    }

    public long getRank() {
        return rank;
    }

    public void setPatient(IPatient patient) {
        this.patient = patient;
    }

    public void setFinishTime(long startTime) {
        long duration = 0;
        if(this.patient != null) {
            duration = patient.getDuration();
        }
        this.finishTime = startTime + duration;
    }

    public void setUtilization() {
        long duration = 0;
        if(this.patient != null) {
            duration = patient.getDuration();
        }
        this.utilization += duration;
    }

    public void setRankToFinishTime() {
        this.rank = this.finishTime;
    }

    public void setRankToUtilization() {
        this.rank = this.utilization;
    }

    @Override
    public int compareTo(IRoom o) {
        if(this.rank < o.getRank()) {
            return -1;
        } else if(this.rank > o.getRank()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "[" + Long.toString(utilization) + ", " + finishTime % 1000000 + "]";
    }
}
