package edu.neu.ccs.cs5010;

import java.util.*;
import java.io.*;

public class EmailAutomator {
    private int emailCount = 0;

    private void printEmailToFile(String email, String outputDir) {
        File directory = new File(outputDir);
        if (!directory.exists()){
            directory.mkdirs();
        }
        String filePathname = outputDir + "/email" + ++emailCount + ".txt";
        try {
            PrintWriter out = new PrintWriter(filePathname);
            out.print(email);
            out.flush();
        } catch (FileNotFoundException fnfe) {
            System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
            fnfe.printStackTrace();
        }
    }

    public void run(String templatePathname, String outputDir, String CSVPathname, String event) {
        CSVReader reader = new CSVReader(CSVPathname);
        TemplateEditor editor = new TemplateEditor(templatePathname);
        // 1. create mappings from CSV
        List<Map<String, String>> mappings = reader.readCSVToMappings(event);
        for(Map<String, String> map : mappings) {
            // 2. make email
            try {
                String email = editor.makeEmailFromTemplate(map);
                System.out.println(email);
                printEmailToFile(email, outputDir);
            } catch (IllegalStateException ise) {
                System.out.println("Failed to fill all placeholders ...");
                ise.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String CSVPathname = "/Users/yingbinliang/IdeaProjects/pdp-liang355/Assignment3/Flight3FromVancouverToSeattle.csv";
        String templatePathname = "/Users/yingbinliang/IdeaProjects/pdp-liang355/Assignment3/email-template.txt";
        String outputDir = "/Users/yingbinliang/IdeaProjects/pdp-liang355/Assignment3/output";
        String event = "departure";
        EmailAutomator automator = new EmailAutomator();
        automator.run(templatePathname, outputDir, CSVPathname, event);
    }
}
