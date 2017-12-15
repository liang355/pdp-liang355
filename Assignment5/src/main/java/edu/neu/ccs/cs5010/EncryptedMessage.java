package edu.neu.ccs.cs5010;

import java.math.BigInteger;

public class EncryptedMessage {
    int message;
    BigInteger signature;

    EncryptedMessage(int message, BigInteger signature) {
        this.message = message;
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "{" + message + "," + signature + "}";
    }
}