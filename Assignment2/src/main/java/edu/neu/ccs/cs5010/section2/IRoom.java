package edu.neu.ccs.cs5010.section2;

public interface IRoom extends Comparable<IRoom> {
    IPatient getPatient();

    long getFinishTime();

    long getUtilization();

    long getRank();

    void setPatient(IPatient patient);

    void setFinishTime(long startTime);

    void setUtilization();

    void setRankToFinishTime();

    void setRankToUtilization();
}
