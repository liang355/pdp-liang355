package edu.neu.ccs.cs5010.section2;

public interface IPatient extends Comparable<IPatient> {
    long getArrivalTime();

    long getExamStartTime();

    long getUrgency();

    long getDuration();

    long getRank();

    void setExamStartTime(long examStartTime);

    void setRankToArrivalTime();

    void setRankToUrgency();
}
