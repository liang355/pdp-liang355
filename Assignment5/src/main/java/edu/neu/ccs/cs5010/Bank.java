package edu.neu.ccs.cs5010;

import org.nevec.rjm.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * The class represent bank entity
 */
public class Bank implements IBank{
    private Map<Integer, ClientAccount> map = new HashMap<>(); // map<ID, ClientAccount>


    /**
     * register clients, create clientAccount for them
     * @param clientID unique ID of a client
     * @param publicKey publicKey to register
     */
    public void register(int clientID, BigDecimal[] publicKey) {
        ClientAccount clientAccount = new ClientAccount(publicKey);
        map.put(clientID, clientAccount);
    }


    /**
     * verify a encryptedMessage using clientId's public key
     * @param clientId unique ID of a client
     * @param encryptedMessage the sigma in RSA encryption
     * @return if this transaction failed to be verified
     */
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


    /**
     * get the ClientAccount object by clientId
     * @param clientId the id of a client
     * @return the matched ClientAccount
     */
    public ClientAccount getAccountByClientId(int clientId) {
        return map.get(clientId);
    }


    /**
     * retrieve the public key
     * @param clientId the id of a client
     * @return the matched public key
     */
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
