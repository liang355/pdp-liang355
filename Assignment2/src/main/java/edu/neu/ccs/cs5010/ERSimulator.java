package edu.neu.ccs.cs5010;

import java.util.*;

public class ERSimulator {
    // Constants:
    private static final int NUMBER_OF_PATIENTS = 5;
    private static final int SIMULATION_TIME = 60 * 1000;  // in millisecond(s)
    private static final int MAX_URGENCY = 10;
    private static final int MAX_DURATION = 5000;
    private static final int INDEX_OFFSET = 1;
    private static final long PROGRAM_START_TIME = System.currentTimeMillis();
    private static final long PROGRAM_END_TIME = PROGRAM_START_TIME + SIMULATION_TIME;

    private IPriorityQueue<IPatient> arrivalQueue = new MyPriorityQueue<>();
    private IPriorityQueue<IRoom> examQueue = new MyPriorityQueue<>();
    private IPriorityQueue<IRoom> emptyRoomQueue = new MyPriorityQueue<>();

    private enum SimulationType {RANDOM, PRESET};
    private SimulationType simulationType;
    private int numOfRooms;
    private List<IPatient> treatedPatients = new ArrayList<>();

    public ERSimulator(int numOfRooms, SimulationType simulationType) {
        this.numOfRooms = numOfRooms;
        this.simulationType = simulationType;
    }

    // HELPERS:
    private void generateRandomPatients() {
        for(int i = 0; i < NUMBER_OF_PATIENTS; i++) {
            Random rand = new Random();
            long nextRandomTime = PROGRAM_START_TIME + rand.nextInt(SIMULATION_TIME) + INDEX_OFFSET;
            long nextRandomDuration = rand.nextInt(MAX_DURATION) + INDEX_OFFSET;
            int nextRandomUrgency = rand.nextInt(MAX_URGENCY) + INDEX_OFFSET;
            IPatient patient = new MyPatient(nextRandomTime, nextRandomDuration, nextRandomUrgency);
            arrivalQueue.insert(patient);
        }
    }

    private void generateEmptyRooms() {
        for(int i = 0; i < numOfRooms; i++) {
            emptyRoomQueue.insert(new MyEmptyRoom());
        }
    }

    private void printQueues() {
        List<IPatient> patients = arrivalQueue.testForwardTraversal();
        System.out.println("Arrival queue:");
        for(int i = 0; i < patients.size(); i++) {
            System.out.print(patients.get(i).getUrgency() + ", ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Empty room queue:");
        List<IRoom> emptyRooms = emptyRoomQueue.testForwardTraversal();
        for(int i = 0; i < emptyRooms.size(); i++) {
            System.out.print(emptyRooms.get(i).getUtilization() + ", ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Exam queue:");
        List<IRoom> examRooms = examQueue.testForwardTraversal();
        for(int i = 0; i < examRooms.size(); i++) {
            System.out.print(examRooms.get(i).getFinishTime() % 100000 + ", ");
        }
        System.out.println();
    }

    // SIMULATION:
    public void runSimulation() {
        if (this.simulationType == SimulationType.RANDOM) {
            // Initialize:
            generateRandomPatients();
            generateEmptyRooms();

            // Simulation:
            long curTime = PROGRAM_START_TIME;
            while(curTime < PROGRAM_END_TIME) {
                while(emptyRoomQueue.getSize() != 0) {
                    emptyRoomQueue.remove();
                    IPatient nextPatient = arrivalQueue.remove();
                    examQueue.insert(new MyExamRoom(nextPatient, PROGRAM_START_TIME));
                }
                Timer timer = new Timer();
                try {
                    long nextFinishTime = examQueue.front().getFinishTime();
                    if(examQueue.getSize() != 0 && nextFinishTime < PROGRAM_END_TIME) {
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                try {
                                    IRoom finishedRoom = examQueue.remove();
                                    treatedPatients.add(finishedRoom.getPatient());
                                    emptyRoomQueue.insert(new MyEmptyRoom());
                                } catch (NoSuchElementException e) {
                                    System.out.println("no such element");
                                }
                            }
                        }, new Date(nextFinishTime));
                    }
                    curTime = nextFinishTime;
                    printQueues();
                } catch (Exception e) {
                    System.out.println("other exception caught");
                }
            }
        }
    }

    public static void main(String[] args) {
        ERSimulator simulator = new ERSimulator(5, SimulationType.RANDOM);
        simulator.runSimulation();
    }
}
