package Assignment1;

import java.lang.Iterable;
import java.util.*;

public class ScenicRoad extends Highway {
    // Contents of the Highway.
    private Set<Vehicle> contents;
    private Queue<Vehicle> minQueueEast;
    private Queue<Vehicle> minQueueWest;
    private int numEast = 0;
    private int numWest = 0;
    private int bandwidth;

    public ScenicRoad(int bandwidth) {
        contents = new LinkedHashSet<Vehicle>();
        minQueueEast = new PriorityQueue<Vehicle>(1, new Comparator<Vehicle>() {
            public int compare(Vehicle o1, Vehicle o2) {
                if(o1.getVelocity() < o2.getVelocity()) return -1;
                if(o1.getVelocity() > o2.getVelocity()) return 1;
                return 0;
            }
        });
        minQueueWest = new PriorityQueue<Vehicle>(1, new Comparator<Vehicle>() {
            public int compare(Vehicle o1, Vehicle o2) {
                if(o1.getVelocity() < o2.getVelocity()) return -1;
                if(o1.getVelocity() > o2.getVelocity()) return 1;
                return 0;
            }
        });
        this.bandwidth = bandwidth;
    }

    @Override
    public boolean add(Vehicle v) {
        if(v == null) {
            throw new IllegalArgumentException("query vehicle can not be null");
        }
        if(contents.contains(v)) {
            return false;
        }
        if(v.getDirection() == 1) {
            minQueueEast.add(v);
            numEast++;
        } else if (v.getDirection() == 2) {
            minQueueWest.add(v);
            numWest++;
        }
        contents.add(v);
        if(minQueueEast.size() == bandwidth || minQueueWest.size() == bandwidth) {
            reduceVelocityForAllVehicles();
        }
        return true;
    }

    private void reduceVelocityForAllVehicles() {
        Iterator<Vehicle> it = iterator();
        while(it.hasNext()) {
            Vehicle curVehicle = it.next();
            if(curVehicle.getVelocity() > 5) {
                curVehicle.updateVelocity(5);
            }
        }
    }
}
