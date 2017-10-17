package edu.neu.ccs.cs5010;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testMakeEmailFromTemplate() {
        editor = new TemplateEditor(templatePathname);
        Map<String, String> map;
        map = new HashMap<>();
        map.put("first_name", "James");
        map.put("last_name", "Butt");
        map.put("address", "6649 N Blue Gum St");
        map.put("city", "New Orleans");
        map.put("county", "Orleans");
        map.put("state", "LA");
        map.put("zip", "70116");
        map.put("phone", "504-621-8927");
        map.put("email", "jbutt@gmail.com");
        map.put("rewards", "gold");
        map.put("departure-city", "Vancouver");
        map.put("destination-city", "Seattle");
        map.put("event", "departure");
        map.put("Date", new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
        String actualEmail = editor.makeEmailFromTemplate(map);
        String expectedEmail = "2017/10/09\n" +
                "\n" +
                "To: jbutt@gmail.com\n" +
                "Subject: Please accept our apologies for the departure of your flight\n" +
                "Dear James Butt, \n" +
                "\n" +
                "We are very sorry for the departure of your flight from Vancouver to Seattle. As a valued gold member of our club your time is important to us and we will strive to improve our service in the future, and make it on time! \n" +
                "   \n" +
                "Sincerely, \n" +
                "OnTime airline customer service \n";
        String s = "2017/10/09\n" +
                "\n" +
                "To: jbutt@gmail.com\n" +
                "Subject: Please accept our apologies for the departure of your flight\n" +
                "Dear James Butt, \n" +
                "\n" +
                "We are very sorry for the departure of your flight from Vancouver to Seattle. As a valued gold member of our club your time is important to us and we will strive to improve our service in the future, and make it on time! \n" +
                "   \n" +
                "Sincerely, \n" +
                "OnTime airline customer service \n";
        System.out.println(actualEmail);
        assertEquals(true, actualEmail.equals(expectedEmail));
    }
}
