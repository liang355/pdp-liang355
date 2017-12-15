package edu.neu.ccs.cs5010;

import java.math.BigInteger;

public class EncryptedMessage implements IEncryptedMessage {
    int message;
    BigInteger signature;


    /**
     * @param message the raw message
     * @param signature the corresponding signature
     */
    EncryptedMessage(int message, BigInteger signature) {
        this.message = message;
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "{" + message + "," + signature + "}";
    }
}