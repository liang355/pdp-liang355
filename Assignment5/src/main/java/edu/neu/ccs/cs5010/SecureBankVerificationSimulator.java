package edu.neu.ccs.cs5010;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The main class for running the simulation of secure communication between
 * Client and Bank entities using RSA encryption. It has methods for 3 major steps
 * of the simulation
 */
public class SecureBankVerificationSimulator {
    // Constants:
    private static final int MAX_MESSAGE_INTEGER = 30000;

    private Bank bank;
    private Map<Integer, Client> clientMap = new HashMap<>(); // Map<ClientId, Client>
    private Map<Integer, List<EncryptedMessage>> encryptedMessagesMap = new HashMap<>(); // Map<ClientId, EncryptedMessage>
    private List<TransactionRecord> transactionRecordList = new ArrayList<>();
    private Set<Integer> rejectedDeposits = new HashSet<>();
    private Set<Integer> rejectedWithdrawals = new HashSet<>();

    /**
     * step 1: Generating and Storing the Requested Number of Unique Bank Clients
     * @param numOfClients the number of unique clients to generate
     */
    private void generateRandomClients(int numOfClients) {
        bank = new Bank();
        for(int i = 0; i < numOfClients; i++) {
            Client client = new Client(bank, i);
            clientMap.put(i, client);
        }
    }

    /**
     * step 2: Generating (message, digital signature) Pairs
     * @param numOfVerifs number of verifications, which implies number of (message, signature) pairs to generate
     * @param fraction the fraction of invalid message to generate
     */
    private void generateEncryptedPairs(int numOfVerifs, double fraction) {
        Random rand = new Random();
        List<Integer> keys = new ArrayList<>(clientMap.keySet());
        Collections.shuffle(keys);
        for(int i = 0; i < numOfVerifs; i++) {
            int message = rand.nextInt(MAX_MESSAGE_INTEGER) + 1; // random message [1, 30000]
            int clientId = keys.get(rand.nextInt(keys.size()));  // random clientIdString [0, numberOfClients]

            // simulator asks random client to generate valid/invalid signatures
            Client client = clientMap.get(clientId);
            BigInteger sig = (double)i < (double) numOfVerifs * fraction ?
                    new BigInteger(String.valueOf(message)) : client.generateSig(message);

            // create encrypted messages, and map them to clientIdString
            if(!encryptedMessagesMap.containsKey(clientId)) {
                encryptedMessagesMap.put(clientId, new ArrayList<>());
            }
            encryptedMessagesMap.get(clientId).add(new EncryptedMessage(message, sig));
        }
    }

    /**
     * step 3: Processing (message, digital signature) Pairs
     * @param outputFile the file name of the output CSV file
     */
    private void processEncryptedPairs(String outputFile) {
        StringBuilder toPrint = new StringBuilder("Transaction number, Date, " +
                "Time, Client ID, Message, Digital signature, Verified, Transactions status\n");
        Map<BigDecimal[], Integer> publicKeyFreqs = new HashMap<>();
        int duplicatePKCount = 0;
        for(int clientId : encryptedMessagesMap.keySet()) {
            List<EncryptedMessage> list = encryptedMessagesMap.get(clientId);
            for(EncryptedMessage encryptedMessage : list) {
                String transactionNumber = String.valueOf(transactionRecordList.size() + 1);
                String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
                String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                String clientIdString = String.valueOf(clientId);
                String message = String.valueOf(encryptedMessage.message);
                String digitalSignature = String.valueOf(encryptedMessage.signature);
                String verified;

                BigDecimal[] publicKey = bank.getPublicKeyByClientId(clientId);
                publicKeyFreqs.put(publicKey, publicKeyFreqs.getOrDefault(publicKey, 0) + 1);
                duplicatePKCount += publicKeyFreqs.get(publicKey) > 1 ? publicKeyFreqs.get(publicKey) : 0;

                // transactionsStatus logic
                int limit;
                int amount = Integer.valueOf(message.substring(0, message.length() - 1));
                int typeIndex = message.charAt(message.length() - 1) - '0';
                ClientAccount account = bank.getAccountByClientId(clientId);
                String transactionsStatus = typeIndex < 5 ? "deposit " : "withdrawal ";

                // verifyTransaction
                if(!bank.verifyTransaction(clientId, encryptedMessage)) {
                    verified = "no";
                    transactionsStatus += "rejected";
                } else {
                    verified = "yes";
                    limit = typeIndex < 5 ? account.getDepositLimit() : account.getWithdrawalLimit();
                    transactionsStatus += amount <= limit ? "accepted" : "rejected";
                }

                // store rejected transactions
                if(transactionsStatus.contains("rejected")) {
                    if(transactionsStatus.contains("deposit")) {
                        rejectedDeposits.add(clientId);
                    } else if (transactionsStatus.contains("withdrawal")) {
                        rejectedWithdrawals.add(clientId);
                    }
                }

                // create transaction record and ass to list
                TransactionRecord record = new TransactionRecord(transactionNumber, date, time, clientIdString,
                        message, digitalSignature, verified, transactionsStatus);
                transactionRecordList.add(record);
                toPrint.append(record).append("\n");
            }
        }

        // Print to console and file
        System.out.println("\nMETADATA:");
        // Meta 1:
        System.out.println("1. Number of distinct transactions" +
                " with different IDs, but the same public key: " + duplicatePKCount);
        // Meta 2:
        List<Integer> keys = new ArrayList<>(encryptedMessagesMap.keySet());
        keys.sort((a, b) -> (encryptedMessagesMap.get(b).size() - encryptedMessagesMap.get(a).size()));
        int len = keys.size() < 10 ? keys.size() : 10;
        System.out.println("2. Top ten unique users with the " +
                "largest number of transactions during the simulation: " +
                Arrays.toString(keys.subList(0, len).stream().mapToInt(i->i).toArray()));
        System.out.println("-----------------------------");
        System.out.println("userId, transaction freq");
        for(int i = 0; i < len; i++) {
            int key = keys.get(i);
            System.out.println( key+ ": " + encryptedMessagesMap.get(key).size());
        }
        System.out.println("-----------------------------");
        // Meta 3:
        System.out.println("3. List of all IDs with rejected deposit transactions: " +
                Arrays.toString(new ArrayList<>(rejectedDeposits).stream().mapToInt(i->i).toArray()));
        // Meta 4:
        System.out.println("4. List of all IDs with rejected withdrawal transactions: " +
                Arrays.toString(new ArrayList<>(rejectedWithdrawals).stream().mapToInt(i->i).toArray()));
        printTransactionsToFile(toPrint.toString(), outputFile);
//        for(TransactionRecord record : transactionRecordList) {
//            System.out.println(record.toString());
//        }
    }

    /**
     * print all transaction records to file according to the requirements
     * @param toPrint the file string to print to file
     * @param outputFile the name of output file
     */
    private void printTransactionsToFile(String toPrint, String outputFile) {
        try {
            PrintWriter out = new PrintWriter(outputFile);
            out.print(toPrint);
            out.flush();
        } catch (FileNotFoundException fnfe) {
            System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
            fnfe.printStackTrace();
        }
    }

    /**
     * simulate three steps in RSA encryption sequentially
     * @param numOfClients the number of clients to generate for simulation
     * @param numOfVerifs the number of verifications to generate for simulation
     * @param fraction the fraction of invalid message (un-encrypted)
     * @param outputFile the name of output file
     */
    public void runSimulation(int numOfClients, int numOfVerifs, double fraction, String outputFile) {
        generateRandomClients(numOfClients);
        generateEncryptedPairs(numOfVerifs, fraction);
        processEncryptedPairs(outputFile);
    }


    /**
     * The main function that takes argument from command line and triggers the simulation
     * @param args the arguments from the command line
     */
    public static void main(String[] args) {
        InputParser parser = new InputParser(args);
        parser.checkArgs();

        int numOfClients = parser.numOfClients;
        int numOfVerifs = parser.numOfVerifs;
        double fraction = parser.fraction;
        String outputFile = parser.outputFile;

        SecureBankVerificationSimulator simulator =  new SecureBankVerificationSimulator();
        simulator.runSimulation(numOfClients, numOfVerifs, fraction, outputFile);
    }
}
