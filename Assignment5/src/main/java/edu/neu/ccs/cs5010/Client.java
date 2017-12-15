package edu.neu.ccs.cs5010;

import org.nevec.rjm.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Random;

public class Client {
    private int clientID;
    private BigInteger[] privateKey = new BigInteger[2];
    private BigDecimal[] publicKey = new BigDecimal[2];

    // parameters for generating RSA pairs
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger m;
    private BigInteger a;
    private BigDecimal b;

    public Client(Bank bank, int clientID) {
        this.clientID = clientID;
        generateRSAPair();
        // As we create each Client object, pass clientIdString and public_key to bank
        bank.register(clientID, publicKey);
    }

    public BigInteger generateSig(int message) {
        BigInteger bigM = new BigInteger(String.valueOf(message));
        return bigM.modPow(a, n);
    }

    private void generateRSAPair() {
        Random rand = new Random();
        p = BigInteger.probablePrime(128, new Random());
        while (true) {
            BigInteger temp = BigInteger.probablePrime(128, new Random());
            if(!p.equals(temp)) {
                q = temp;
                break;
            }
        }
        n = p.multiply(q);
        m = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        while(true) {
            BigInteger temp = BigInteger.valueOf(rand.nextInt(15) + 5);
            if(temp.gcd(n).equals(BigInteger.ONE) && temp.gcd(m).equals(BigInteger.ONE)) {
                a = temp;
                break;
            }
        }
        BigInteger dividend = BigInteger.ONE.mod(m);
        BigInteger divisor = a;
        b = new BigDecimal(dividend).divide(new BigDecimal(divisor), 80, BigDecimal.ROUND_HALF_UP);
        this.privateKey[0] = a;
        this.privateKey[1] = n;
        this.publicKey[0] = b;
        this.publicKey[1] = new BigDecimal(n);
    }

    public int getClientID() {
        return clientID;
    }
}
