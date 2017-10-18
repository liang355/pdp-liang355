package edu.neu.ccs.cs5010.section2;

import java.util.*;

public class ERSimulator {
    // Constants:
    private static final int NUMBER_OF_PATIENTS = 15;
    private static final int SIMULATION_TIME = 60 * 1000;  // in millisecond(s)
    private static final int MAX_URGENCY = 10;
    private static final int MAX_DURATION = 30 * 1000;  // in millisecond(s)
    private static final int INDEX_OFFSET = 1;
    private static final long PROGRAM_START_TIME = System.currentTimeMillis();
    private static final long PROGRAM_END_TIME = PROGRAM_START_TIME + SIMULATION_TIME;

    // Collections:
    private IPriorityQueue<IPatient> patientQueue = new MyPriorityQueue<>();
    private IPriorityQueue<IPatient> arrivalQueue = new MyPriorityQueue<>();
    private IPriorityQueue<IRoom> examQueue = new MyPriorityQueue<>();
    private IPriorityQueue<IRoom> emptyRoomQueue = new MyPriorityQueue<>();
    private List<IPatient> treatedPatients = new ArrayList<>();

    // Params & Misc:
    private enum SimulationType {RANDOM, PRESET};
    private enum EventType {ARRIVAL, FINISH}
    private SimulationType simulationType;
    private int numOfRooms;

    public ERSimulator(int numOfRooms, SimulationType simulationType) {
        this.numOfRooms = numOfRooms;
        this.simulationType = simulationType;
    }

    // Initialization HELPERS:
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

    // Printer HELPER
    private void printQueues() {
        StringJoiner joiner;

        System.out.println("Patient queue:");
        List<IPatient> patients = patientQueue.testForwardTraversal();
        joiner = new StringJoiner(",\n");
        for(IPatient patient : patients) {
            joiner.add(patient.toString());
        }
        System.out.println(joiner.toString());

        System.out.println("Arrival queue:");
        List<IPatient> arrivedPatients = arrivalQueue.testForwardTraversal();
        joiner = new StringJoiner(",\n");
        for(IPatient patient : arrivedPatients) {
            joiner.add(patient.toString());
        }
        System.out.println(joiner.toString());

        System.out.println("Empty room queue:");
        List<IRoom> emptyRooms = emptyRoomQueue.testForwardTraversal();
        joiner = new StringJoiner(",\n");
        for(IRoom room : emptyRooms) {
            joiner.add(room.toString());
        }
        System.out.println(joiner.toString());

        System.out.println("Exam queue:");
        List<IRoom> examRooms = examQueue.testForwardTraversal();
        joiner = new StringJoiner(",\n");
        for(IRoom room : examRooms) {
            joiner.add(room.toString());
        }
        System.out.println(joiner.toString());

        System.out.println("Treated patients:");
        joiner = new StringJoiner(",\n");
        for(IPatient patient : treatedPatients) {
            joiner.add(patient.toString());
        }
        System.out.println(joiner.toString() + "\n");
    }

    // Three events:
    // patientArrivalEvent: patient arrives at emergency room (patientQueue -> arrivalQueue);
    // examStartEvent:      patient starts exam in exam room (arrivalQueue -> emptyRoom; emptyRoom -> examQueue);
    // examFinishEvent:     patient finishes exam (patient -> treatedPatients; examQueue -> emptyRoomQueue);
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
        room.setFinishTime(0);
        room.setRankToUtilization();
        emptyRoomQueue.insert(room);
    }

    // Mutual recursion: scheduleNextEvent <--> chooseNextEvent
    // Termination: 1) time limit exceeded OR 2) all patients have arrived && no exam room is occupied
    private void scheduleNextEvent(EventType eventType, long nextEventTime) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Schedule either patientArrivalEvent or examFinishExam, who ever comes first
                if(eventType == EventType.ARRIVAL) {
                    patientArrivalEvent();
                    System.out.println("------------------PatientArrivalEvent------------------");
                } else if(eventType == EventType.FINISH){
                    examFinishEvent();
                    System.out.println("--------------------ExamFinishEvent--------------------");
                }
                printQueues();

                // After the scheduled event, the state of Emergency room has changed;
                // Check for availability of exam room & patients waiting line (arrivalQueue)
                if(!emptyRoomQueue.isEmpty() && !arrivalQueue.isEmpty()) {
                    examStartEvent(nextEventTime);
                    System.out.println("--------------------ExamStartEvent--------------------");
                }
                chooseNextEvent(nextEventTime);
                printQueues();

                // Cancel the timer after executing scheduled task
                timer.cancel();
                timer.purge();
            }
        }, new Date(nextEventTime));
    }

    private void chooseNextEvent(long curTime) {
        if(curTime < PROGRAM_END_TIME && (!patientQueue.isEmpty() || !examQueue.isEmpty())) {
            long nextArrivalTime = patientQueue.isEmpty() ? Long.MAX_VALUE : patientQueue.front().getArrivalTime();
            long nextFinishTime = examQueue.isEmpty() ? Long.MAX_VALUE : examQueue.front().getFinishTime();
            if(nextArrivalTime < nextFinishTime) {
                scheduleNextEvent(EventType.ARRIVAL, nextArrivalTime);
            } else {
                scheduleNextEvent(EventType.FINISH, nextFinishTime);
            }
        }
    }

    // SIMULATION:
    public void runSimulation() {
        if (this.simulationType == SimulationType.RANDOM) {
            // Initialize:
            generateRandomPatients();
            generateEmptyRooms();
            printQueues();

            // Simulation:
            chooseNextEvent(PROGRAM_START_TIME);
        }
    }

    public static void main(String[] args) {
        ERSimulator simulator = new ERSimulator(3, SimulationType.RANDOM);
        simulator.runSimulation();
    }
}
