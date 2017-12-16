package edu.neu.ccs.cs5010;

/**
 * Data structure class that represents a transaction record
 */
public class TransactionRecord implements ITransactionRecord {
    private String transactionNumber;
    private String date;
    private String time;
    private String clientIdString;
    private String message;
    private String digitalSignature;
    private String verified;
    private String transactionsStatus;


    /**
     * initialize TransactionRecord class with so many params
     * @param transactionNumber the id of this transaction record
     * @param date the date when this transaction happens
     * @param time the time when this transaction happens
     * @param clientIdString the String representation of client Id
     * @param message the message contained in this transaction
     * @param digitalSignature the digital signature created on the message
     * @param verified boolean value indicating whether this transaction is successfully verified
     * @param transactionsStatus String that is one of four values indicating the transaction status:
     *                           "deposit accepted", "deposit rejected",
     *                           "withdrawal accepted", "withdrawal rejected"
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

    /**
     * convert the fields of this class into an aggregate String
     * @return the string representation of instance of this class
     */
    @Override
    public String toString() {
        return String.join(", ", transactionNumber, date, time, clientIdString, message, digitalSignature,
                verified, transactionsStatus);
    }
}
