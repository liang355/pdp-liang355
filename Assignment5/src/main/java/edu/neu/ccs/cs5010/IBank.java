package edu.neu.ccs.cs5010;

import java.math.BigDecimal;

public interface IBank {
    boolean verifyTransaction(int clientId, EncryptedMessage encryptedMessage);
    ClientAccount getAccountByClientId(int clientId);
    BigDecimal[] getPublicKeyByClientId(int clientId);
}
