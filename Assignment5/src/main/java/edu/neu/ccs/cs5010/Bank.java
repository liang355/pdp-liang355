package edu.neu.ccs.cs5010;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Integer, ClientInfo> map = new HashMap<>(); // map<ID, PK>

    public void generateClientInfo(int clientID, BigInteger[] publicKey) {
        ClientInfo clientInfo = new ClientInfo(publicKey);
        map.put(clientID, clientInfo);
    }
}
