package edu.neu.ccs.cs5010;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

/**
 * The data structure class for client account, storing
 * deposit limit, withdrawal limit, public key. The instance of this data structure
 * will be mapped with client Id by Bank
 */
public class ClientAccount implements IClientAccount {
    // CONSTANTS:
    private static final int MAX_DEPOSIT_LIMIT = 2000;
    private static final int MAX_WITHDRAWAL_LIMIT = 3000;

    private int depositLimit;
    private int withdrawalLimit;
    private BigDecimal[] publicKey;


    /** create ClientAccount with public key
     * @param publicKey the public key
     */
    public ClientAccount(BigDecimal[] publicKey) {
        Random rand = new Random();
        this.depositLimit = rand.nextInt(MAX_DEPOSIT_LIMIT) + 1; // random [1, 2000]
        this.withdrawalLimit = rand.nextInt(MAX_WITHDRAWAL_LIMIT) + 1; // random [1, 3000]
        this.publicKey = publicKey;
    }

    /**
     * getter for depositLimit
     * @return deposit limit for the client account
     */
    public int getDepositLimit() {
        return depositLimit;
    }

    /**
     * getter for withdrawalLimit
     * @return withdrawal limit for the client account
     */
    public int getWithdrawalLimit() {
        return withdrawalLimit;
    }

    /**
     * getter for publicKey
     * @return
     */
    public BigDecimal[] getPublicKey() {
        return publicKey;
    }

    @Override
    public String toString() {
        return "[" + depositLimit + "," + withdrawalLimit + "," + Arrays.toString(publicKey) + "]";
    }
}
