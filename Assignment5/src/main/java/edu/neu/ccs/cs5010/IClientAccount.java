package edu.neu.ccs.cs5010;

import java.math.BigDecimal;

public interface IClientAccount {
    int getDepositLimit();
    int getWithdrawalLimit();
    BigDecimal[] getPublicKey();
}
