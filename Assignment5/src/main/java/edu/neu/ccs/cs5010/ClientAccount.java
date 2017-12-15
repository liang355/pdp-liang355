package edu.neu.ccs.cs5010;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class ClientAccount {
    // CONSTANTS:
    private static final int MAX_DEPOSIT_LIMIT = 2000;
    private static final int MAX_WITHDRAWAL_LIMIT = 3000;

    private int depositLimit;
    private int withdrawalLimit;
    private BigDecimal[] publicKey;

    public ClientAccount(BigDecimal[] publicKey) {
        Random rand = new Random();
        this.depositLimit = rand.nextInt(MAX_DEPOSIT_LIMIT) + 1; // random [1, 2000]
        this.withdrawalLimit = rand.nextInt(MAX_WITHDRAWAL_LIMIT) + 1; // random [1, 3000]
        this.publicKey = publicKey;
    }

    public int getDepositLimit() {
        return depositLimit;
    }

    public int getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public BigDecimal[] getPublicKey() {
        return publicKey;
    }

    @Override
    public String toString() {
        return "[" + depositLimit + "," + withdrawalLimit + "," + Arrays.toString(publicKey) + "]";
    }
}
