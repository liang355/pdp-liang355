package edu.neu.ccs.cs5010;

public abstract class MyRoom implements IRoom {
    protected IPatient patient;
    protected long finishTime;
    protected long utilization;

    public IPatient getPatient() {
        return patient;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public double getUtilization() {
        return utilization;
    }
}
