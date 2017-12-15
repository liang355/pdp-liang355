package edu.neu.ccs.cs5010;

public class TransactionRecord implements ITransactionRecord {
    String transactionNumber;
    String date;
    String time;
    String clientIdString;
    String message;
    String digitalSignature;
    String verified;
    String transactionsStatus;


    /**
     * initialize TransactionRecord class with so many params
     * @param transactionNumber
     * @param date
     * @param time
     * @param clientIdString
     * @param message
     * @param digitalSignature
     * @param verified
     * @param transactionsStatus
     */
    TransactionRecord(String transactionNumber, String date, String time, String clientIdString, String message,
                      String digitalSignature, String verified, String transactionsStatus) {
        this.transactionNumber = transactionNumber;
        this.date = date;
        this.time = time;
        this.clientIdString = clientIdString;
        this.message = message;
        this.digitalSignature = digitalSignature;
        this.verified = verified;
        this.transactionsStatus = transactionsStatus;
    }

    @Override
    public String toString() {
        return String.join(", ", transactionNumber, date, time, clientIdString, message, digitalSignature,
                verified, transactionsStatus);
    }
}
