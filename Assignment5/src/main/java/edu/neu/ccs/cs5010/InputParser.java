package edu.neu.ccs.cs5010;

import java.util.Arrays;

public class InputParser implements IInputParser {
    private String[] args;
    int numOfClients;
    int numOfVerifs;
    double fraction;
    String outputFile;

    public InputParser(String[] arguments) {
        args = arguments;
        numOfClients = Integer.valueOf(arguments[0]);
        numOfVerifs = Integer.valueOf(arguments[1]);
        fraction = Double.valueOf(arguments[2]);
        outputFile = arguments[3];
    }

    public void checkArgs() {
        //when argument number is less than 4
        if (args.length < 4) {
            System.out.println("Error: One or more arguments are missing.");
            throw new IllegalArgumentException("invalid number of arguments");
        }

        //when number of clients are out of range
        if (numOfClients <= 0 || numOfClients > 50000) {
            System.out.println("Error: number of clients should be in range [1, 50000]");
            throw new IllegalArgumentException("Error: number of clients should be in range [1, 50000]");
        }

        //when number of verifications are out of range
        if (numOfVerifs <= 0 || numOfVerifs > 10000) {
            System.out.println("Error: number of verifications should be in range [1, 10000]");
            throw new IllegalArgumentException("Error: number of verifications should be in range [1, 10000]");
        }

        //when fraction are out of range
        if (fraction < 0 || fraction > 1) {
            System.out.println("Error: fraction should be in range [0, 1]");
            throw new IllegalArgumentException("Error: fraction should be in range [0, 1]");
        }

        //try to get third optional argument  numberOfRecommendations
        if(!outputFile.endsWith(".csv")) {
            throw  new IllegalArgumentException("Error: invalid file extension, filename should end with \".csv\"");
        }
    }
}
