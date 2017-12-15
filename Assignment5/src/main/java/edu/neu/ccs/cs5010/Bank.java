package edu.neu.ccs.cs5010;

import org.nevec.rjm.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Integer, ClientAccount> map = new HashMap<>(); // map<ID, ClientAccount>

    public void register(int clientID, BigDecimal[] publicKey) {
        ClientAccount clientAccount = new ClientAccount(publicKey);
        map.put(clientID, clientAccount);
    }

    public boolean verifyTransaction(int clientId, EncryptedMessage encryptedMessage) {
        BigDecimal[] publicKey = map.get(clientId).getPublicKey();
        BigDecimal b = publicKey[0];
        BigDecimal n = publicKey[1];
        int expectedMessage = encryptedMessage.message;
        BigInteger sig = encryptedMessage.signature;
        int actualMessage = BigDecimalMath.pow(new BigDecimal(sig), b).intValue();
//        System.out.println(expectedMessage + ", " + actualMessage);
        return expectedMessage == actualMessage;
    }

    public ClientAccount getAccountByClientId(int clientId) {
        return map.get(clientId);
    }

    public BigDecimal[] getPublicKeyByClientId(int clientId) {
        return map.get(clientId).getPublicKey();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int key : map.keySet()) {
            sb.append("{").append(key).append(":").append(map.get(key)).append("}\n");
        }
        return sb.toString();
    }
}
