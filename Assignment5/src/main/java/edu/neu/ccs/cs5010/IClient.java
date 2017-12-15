package edu.neu.ccs.cs5010;

import java.math.BigInteger;

public interface IClient {
    BigInteger generateSig(int message);
    int getClientID();
}
