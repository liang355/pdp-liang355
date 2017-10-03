package edu.neu.ccs.cs5010;

public interface IRoom extends Comparable<IRoom> {
    IPatient getPatient();

    long getFinishTime();

    double getUtilization();
}
