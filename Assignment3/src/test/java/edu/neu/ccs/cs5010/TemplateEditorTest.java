package edu.neu.ccs.cs5010;

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
    }
}
