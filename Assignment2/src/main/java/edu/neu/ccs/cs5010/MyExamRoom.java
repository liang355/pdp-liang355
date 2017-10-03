package edu.neu.ccs.cs5010;

public class MyExamRoom extends MyRoom {
    public MyExamRoom(IPatient patient, long startTime) {
        super();
        this.patient = patient;
        this.finishTime = startTime + patient.getDuration();
    }

    @Override
    public int compareTo(IRoom o) {
        if(this.finishTime < o.getFinishTime()) {
            return -1;
        } else if(this.finishTime > o.getFinishTime()) {
            return 1;
        }
        return 0;
    }
}
