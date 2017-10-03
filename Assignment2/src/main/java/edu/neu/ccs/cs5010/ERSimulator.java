package edu.neu.ccs.cs5010;

import java.util.*;

public class ERSimulator {
    // Constants:
    private static final int NUMBER_OF_PATIENTS = 30;
    private static final int SIMULATION_TIME = 60 * 1000;  // in millisecond(s)
    private static final int MAX_URGENCY = 10;
    private static final int MAX_DURATION = 5000;
    private static final int INDEX_OFFSET = 1;

    private IPriorityQueue<IPatient> arrivalQueue = new MyPriorityQueue<>();
    private IPriorityQueue<IPatient> examQueue = new MyPriorityQueue<>();
    private IPriorityQueue<IPatient> roomQueue = new MyPriorityQueue<>();

    private enum SimulationType {RANDOM, PRESET}

    ;
    private SimulationType simulationType;
    private int numOfRoom;

    public ERSimulator(int numOfRoom, SimulationType simulationType) {
        this.numOfRoom = numOfRoom;
        this.simulationType = simulationType;
    }

    private void generateRandomPatients() {
        for (int i = 0; i < NUMBER_OF_PATIENTS; i++) {
            Random rand = new Random();
            long nextRandomTime = System.currentTimeMillis() + rand.nextInt(SIMULATION_TIME);
            long nextRandomDuration = rand.nextInt(MAX_DURATION) + INDEX_OFFSET;
            int nextRandomUrgency = rand.nextInt(MAX_URGENCY) + INDEX_OFFSET;
            IPatient patient = new MyPatient(nextRandomTime, nextRandomDuration, nextRandomUrgency);
            arrivalQueue.insert(patient);
        }
    }

    public void run() {
        if (this.simulationType == SimulationType.RANDOM) {
            generateRandomPatients();
            while (examQueue.getSize() < numOfRoom && !arrivalQueue.isEmpty()) {
                examQueue.insert(arrivalQueue.remove());
            }
        }
    }

    public static void main(String[] args) {
        ERSimulator simulator = new ERSimulator(5, SimulationType.RANDOM);
        simulator.run();
    }
}
