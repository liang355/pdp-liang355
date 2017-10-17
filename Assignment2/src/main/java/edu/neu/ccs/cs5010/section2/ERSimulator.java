package edu.neu.ccs.cs5010.section2;

import java.util.*;

public class ERSimulator {
    // Constants:
    private static final int NUMBER_OF_PATIENTS = 10;
    private static final int SIMULATION_TIME = 60 * 1000;  // in millisecond(s)
    private static final int MAX_URGENCY = 10;
    private static final int MAX_DURATION = 5000;
    private static final int INDEX_OFFSET = 1;
    private static final long PROGRAM_START_TIME = System.currentTimeMillis();
    private static final long PROGRAM_END_TIME = PROGRAM_START_TIME + SIMULATION_TIME;

    private IPriorityQueue<IPatient> patientQueue = new MyPriorityQueue<>();
    private IPriorityQueue<IPatient> arrivalQueue = new MyPriorityQueue<>();
    private IPriorityQueue<IRoom> examQueue = new MyPriorityQueue<>();
    private IPriorityQueue<IRoom> emptyRoomQueue = new MyPriorityQueue<>();

    private enum SimulationType {RANDOM, PRESET};
    private enum EventType {ARRIVAL, FINISH}
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
            patientQueue.insert(patient);
        }
    }

    private void generateEmptyRooms() {
        for(int i = 0; i < numOfRooms; i++) {
            emptyRoomQueue.insert(new MyRoom());
        }
    }

    private void printQueues() {
        List<IPatient> patients = patientQueue.testForwardTraversal();
        System.out.println("Patient queue:");
        System.out.println(patients);

        List<IPatient> arrivedPatients = arrivalQueue.testForwardTraversal();
        System.out.println("Arrival queue:");
        System.out.println(arrivedPatients);

        System.out.println("Empty room queue:");
        List<IRoom> emptyRooms = emptyRoomQueue.testForwardTraversal();
        System.out.println(emptyRooms);

        System.out.println("Exam queue:");
        List<IRoom> examRooms = examQueue.testForwardTraversal();
        System.out.println(examRooms);

        System.out.println("Treated patients:");
        System.out.println(treatedPatients + "\n");
    }

    private void patientArrivalEvent() {
        IPatient patient = patientQueue.remove();
        patient.setRankToUrgency();
        arrivalQueue.insert(patient);
    }

    private void examStartEvent(long curTime) {
        IPatient patient = arrivalQueue.remove();
        patient.setExamStartTime(curTime);
        IRoom room = emptyRoomQueue.remove();
        room.setPatient(patient);
        room.setFinishTime(curTime);
        room.setUtilization();
        room.setRankToFinishTime();
        examQueue.insert(room);
    }

    private void examFinishEvent() {
        IRoom room = examQueue.remove();
        treatedPatients.add(room.getPatient());
        room.setPatient(null);
        room.setFinishTime(-1);
        room.setRankToUtilization();
        emptyRoomQueue.insert(room);
    }

    private void scheduleNextEvent(EventType eventType, long nextEventTime) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(eventType == EventType.ARRIVAL) {
                    patientArrivalEvent();
                } else if(eventType == EventType.FINISH){
                    examFinishEvent();
                }
            }
        }, new Date(nextEventTime));
    }

    // SIMULATION:
    public void runSimulation() {
        if (this.simulationType == SimulationType.RANDOM) {
            // Initialize:
            generateRandomPatients();
            generateEmptyRooms();
            printQueues();

            // Simulation:
            long curTime = PROGRAM_START_TIME;
            while(curTime < PROGRAM_END_TIME) {
                while(!patientQueue.isEmpty() || !examQueue.isEmpty()) {
                    long nextArrivalTime = patientQueue.isEmpty() ? Long.MAX_VALUE : patientQueue.front().getArrivalTime();
                    long nextFinishTime = examQueue.isEmpty() ? Long.MAX_VALUE : examQueue.front().getFinishTime();
                    if(nextArrivalTime < nextFinishTime) {
                        scheduleNextEvent(EventType.ARRIVAL, nextArrivalTime);
                        curTime = nextArrivalTime;
                    } else {
                        scheduleNextEvent(EventType.FINISH, nextFinishTime);
                        curTime = nextFinishTime;
                    }
                    if(!emptyRoomQueue.isEmpty() && !arrivalQueue.isEmpty()) {
                        examStartEvent(curTime);
                    }
                    printQueues();
                }
//                while(emptyRoomQueue.getSize() != 0) {
//                    emptyRoomQueue.remove();
//                    IPatient nextPatient = arrivalQueue.remove();
//                    examQueue.insert(new MyRoom());
//                }
//
//                try {
//                    long nextFinishTime = examQueue.front().getFinishTime();
//                    if(examQueue.getSize() != 0 && nextFinishTime < PROGRAM_END_TIME) {
//                        timer.schedule(new TimerTask() {
//                            @Override
//                            public void run() {
//                                try {
//                                    IRoom finishedRoom = examQueue.remove();
//                                    treatedPatients.add(finishedRoom.getPatient());
//                                    emptyRoomQueue.insert(new MyRoom());
//                                } catch (NoSuchElementException e) {
//                                    System.out.println("no such element");
//                                }
//                            }
//                        }, new Date(nextFinishTime));
//                    }
//                    curTime = nextFinishTime;
//                    printQueues();
//                } catch (Exception e) {
//                    System.out.println("other exception caught");
//                }
            }
        }
    }

    public static void main(String[] args) {
        ERSimulator simulator = new ERSimulator(5, SimulationType.RANDOM);
        simulator.runSimulation();
    }
}
