package edu.neu.ccs.cs5010;

public class MyEmptyRoom extends MyRoom{
    public MyEmptyRoom() {
        super();
        this.utilization = 0;
    }

    @Override
    public int compareTo(IRoom o) {
        if(this.utilization < o.getUtilization()) {
            return -1;
        } else if(this.utilization > o.getUtilization()) {
            return 1;
        }
        return 0;
    }
}
