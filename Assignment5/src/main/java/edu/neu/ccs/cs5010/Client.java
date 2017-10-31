package edu.neu.ccs.cs5010;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Client {
    private int clientID;
    private BigInteger[] privateKey = new BigInteger[2];
    public BigInteger[] publicKey = new BigInteger[2];
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger m;
    private BigInteger a;
    private BigInteger b;

    public Client(Bank bank) {
        Random rand = new Random();
        Set<Integer> clientIDs = new HashSet<>();
        int temp;
        while(true) {
            if(!clientIDs.contains(temp = rand.nextInt())) {
                clientIDs.add(temp);
                this.clientID = temp;
                break;
            }
        }
        generateRSAPair();
        bank.generateClientInfo(clientID, publicKey);
    }

    public BigInteger generateSig(int m) {
        BigInteger bigM = new BigInteger(String.valueOf(m));
        return bigM.pow(a.intValue()).mod(n);
    }

    private void generateRSAPair() {
        p = BigInteger.probablePrime(128, new Random());
        q = BigInteger.probablePrime(128, new Random());
        n = p.multiply(q);
        m = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        while(true) {
            BigInteger temp = BigInteger.probablePrime(128, new Random());
            if(gcd(temp, n).equals(BigInteger.ONE) && gcd(temp, m).equals(BigInteger.ONE)) {
                a = temp;
                break;
            }
        }
        b = BigInteger.ONE.mod(m).divide(a);
        this.privateKey[0] = a;
        this.privateKey[1] = n;
        this.publicKey[0] = b;
        this.publicKey[1] = n;
    }

    // Euclid's method for finding GCD:
    private BigInteger gcd(BigInteger c, BigInteger d) {
        BigInteger t;
        while(d.equals(BigInteger.ZERO)){
            t = c;
            c = b;
            d = t.mod(d);
        }
        return c;
    }

    public int getClientID() {
        return clientID;
    }
}
