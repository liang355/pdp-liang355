package edu.neu.ccs.cs5010;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TemplateEditorTest {
    private static String CSVPathname = "/Users/yingbinliang/IdeaProjects/pdp-liang355/Assignment3/Flight3FromVancouverToSeattle.csv";
    private static String templatePathname = "/Users/yingbinliang/IdeaProjects/pdp-liang355/Assignment3/email-template.txt";
    private static String outputDir = "/Users/yingbinliang/IdeaProjects/pdp-liang355/Assignment3/output";
    private static String event = "departure";
    private static TemplateEditor editor;

    @Test
    public void testInitialState() {
        editor = new TemplateEditor(templatePathname);
        String expectedTemplateString;
        FileReader inputFile = null;
        StringBuilder sb = new StringBuilder();
        try {
            inputFile = new FileReader(templatePathname);
            int charInt;
            while ((charInt = inputFile.read()) != -1) {
                sb.append((char)charInt);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Something went wrong! : " + ioe.getMessage());
            ioe.printStackTrace();
        }

        expectedTemplateString = sb.toString();
        String actualTemplateString = editor.getTemplateString();
        assertEquals(actualTemplateString, expectedTemplateString);
    }
}
