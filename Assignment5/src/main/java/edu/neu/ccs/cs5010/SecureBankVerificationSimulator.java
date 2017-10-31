package edu.neu.ccs.cs5010;

import java.math.BigInteger;
import java.util.*;

public class SecureBankVerificationSimulator {
    // Constants:
    private static final int MAX_MESSAGE_INTEGER = 30000;

    private Bank bank;
    private Map<Integer, Client> clients = new HashMap<>();
    private Map<Integer, BigInteger> sigMap = new HashMap<>();

    private void generateRandomClients(int numOfClients) {
        bank = new Bank();
        for(int i = 0; i < numOfClients; i++) {
            Client client = new Client(bank);
            int clientID = client.getClientID();
            clients.put(clientID, client);
        }
    }

    private void generateEncryptedPairs(int numOfVerifs, double fraction) {
        for(int i = 0; i < numOfVerifs; i++) {
            Random rand = new Random();
            Client curClient = clients.get(rand.nextInt(clients.size()));
            int message = rand.nextInt(MAX_MESSAGE_INTEGER) + 1; // random [1, 30000]
            BigInteger sig = curClient.generateSig(message);
            sigMap.put(curClient.getClientID(), sig);
        }
    }

    private void processEncryptedPairs() {

    }

    public void runSimulation(int numOfClients, int numOfVerifs, double fraction, String outputFile) {
        generateRandomClients(numOfClients);
        generateEncryptedPairs(numOfVerifs, fraction);
        processEncryptedPairs();
    }

    public static void main(String[] args) {
        int numOfClients = 50000;
        int numOfVerifs = 10000;
        double fraction = 0.5;
        String outputFile = "output";
        SecureBankVerificationSimulator simulator =  new SecureBankVerificationSimulator();
        simulator.runSimulation(numOfClients, numOfVerifs, fraction, outputFile);
    }
}
