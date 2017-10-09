package edu.neu.ccs.cs5010;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class TemplateEditor {
    private String templateString;

    public String getTemplateString() {
        return this.templateString;
    }

    public String makeEmailFromTemplate(Map<String, String> map) {
        // 1. fill
        String email = this.templateString;
        for(String key : map.keySet()) {
            Pattern p;
            Matcher m;
            p = Pattern.compile("\\[\\[" + key + "\\]\\]");
            m = p.matcher(email);
            while (m.find()) {
                String value = map.get(key);
                email = m.replaceAll(value);
                m = p.matcher(email);
            }
        }
        // 2. check if all placeholders are filled
        Pattern p;
        Matcher m;
        p = Pattern.compile("\\[\\[.*?\\]\\]");
        m = p.matcher(email);
        if(m.find()) {
            throw new IllegalStateException();
        }
        return email;
    }

    public String[] getPlaceholders() {
        List<String> stringList = new ArrayList<>();
        StringBuilder sb;
        String s;
        Pattern p;
        Matcher m;
        p = Pattern.compile("\\[\\[.*?\\]\\]");
        m = p.matcher(templateString);
        while (m.find()) {
            s = m.group();
            sb = new StringBuilder(s);
            stringList.add(sb.substring(2, s.length() - 2));
        }
        return stringList.toArray(new String[stringList.size()]);
    }

    public TemplateEditor(String templatePathname) {
        FileReader inputFile = null;
        StringBuilder sb = new StringBuilder();
        String templateString = "";
        try {
            inputFile = new FileReader(templatePathname);
            int charInt;
            while ((charInt = inputFile.read()) != -1) {
                sb.append((char)charInt);
            }
            templateString = sb.toString();
        } catch (FileNotFoundException fnfe) {
            System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Something went wrong! : " + ioe.getMessage());
            ioe.printStackTrace();
        } finally {
            if (inputFile != null) {
                try {
                    inputFile.close();
                } catch (IOException e) {
                    System.out.println("Failed to close input stream in finally block");
                    e.printStackTrace();
                }
            }
            this.templateString = templateString;
        }
    }

    public static void main(String[] args) {

    }
}
