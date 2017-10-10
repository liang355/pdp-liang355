package edu.neu.ccs.cs5010;


import java.text.SimpleDateFormat;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CSVReaderTest {
    private static String CSVPathname = "/Users/yingbinliang/IdeaProjects/pdp-liang355/Assignment3/Flight3FromVancouverToSeattle.csv";
    private static String templatePathname = "/Users/yingbinliang/IdeaProjects/pdp-liang355/Assignment3/email-template.txt";
    private static String outputDir = "/Users/yingbinliang/IdeaProjects/pdp-liang355/Assignment3/output";
    private static String event = "departure";
    private static CSVReader reader;


    @Test
    public void testInitialState() {
        reader = new CSVReader(CSVPathname);
        List<String> expectedLines = new ArrayList<>();
        expectedLines.add("\"first_name\",\"last_name\",\"address\",\"city\",\"county\",\"state\",\"zip\",\"phone\",\"email\",\"rewards\"");
        expectedLines.add("\"James\",\"Butt\",\"6649 N Blue Gum St\",\"New Orleans\",\"Orleans\",\"LA\",\"70116\",\"504-621-8927\",\"jbutt@gmail.com\",\"gold\"");
        expectedLines.add("\"Josephine\",\"Darakjy\",\"4 B Blue Ridge Blvd\",\"Brighton\",\"Livingston\",\"MI\",\"48116\",\"810-292-9388\",\"josephine_darakjy@darakjy.org\",\"silver\"");
        expectedLines.add("\"Art\",\"Venere\",\"8 W Cerritos Ave #54\",\"Bridgeport\",\"Gloucester\",\"NJ\",\"8014\",\"856-636-8749\",\"art@venere.org\",\"bronze\"");
        List<String> actualLines = reader.getLines();
        assertEquals(actualLines.size(), expectedLines.size());
        for(int i = 0; i < actualLines.size(); i++) {
            assertEquals(actualLines.get(i), expectedLines.get(i));
        }
    }

    @Test
    public void testReadCSVToMappings() {
        reader = new CSVReader(CSVPathname);
        List<Map<String, String>> expectedMappings = new ArrayList<>();
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
        expectedMappings.add(map);
        map = new HashMap<>();
        map.put("first_name", "Josephine");
        map.put("last_name", "Darakjy");
        map.put("address", "4 B Blue Ridge Blvd");
        map.put("city", "Brighton");
        map.put("county", "Livingston");
        map.put("state", "MI");
        map.put("zip", "48116");
        map.put("phone", "810-292-9388");
        map.put("email", "josephine_darakjy@darakjy.org");
        map.put("rewards", "silver");
        map.put("departure-city", "Vancouver");
        map.put("destination-city", "Seattle");
        map.put("event", "departure");
        map.put("Date", new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
        expectedMappings.add(map);
        map = new HashMap<>();
        map.put("first_name", "Art");
        map.put("last_name", "Venere");
        map.put("address", "8 W Cerritos Ave #54");
        map.put("city", "Bridgeport");
        map.put("county", "Gloucester");
        map.put("state", "NJ");
        map.put("zip", "8014");
        map.put("phone", "856-636-8749");
        map.put("email", "art@venere.org");
        map.put("rewards", "bronze");
        map.put("departure-city", "Vancouver");
        map.put("destination-city", "Seattle");
        map.put("event", "departure");
        map.put("Date", new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
        expectedMappings.add(map);
        List<Map<String, String>> actualMappings = reader.readCSVToMappings("departure");
        assertEquals(actualMappings.size(), expectedMappings.size());
        for(int i = 0; i < actualMappings.size(); i++) {
            actualMappings.get(i).equals(expectedMappings.get(i));
        }
    }

//    @Test(expected = IOException.class)
//    public void testFileNotFound() {
//        CSVPathname = "/Users/yingbinliang/IdeaProjects/pdp-liang355/Assignment3/XXXXX.csv";
//        reader = new CSVReader(CSVPathname);
//    }
}
