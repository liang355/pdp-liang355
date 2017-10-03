package edu.neu.ccs.cs5010;

public interface IPatient extends Comparable<IPatient> {
    long getArrivalTime();

    int getUrgency();

    long getDuration();
}
