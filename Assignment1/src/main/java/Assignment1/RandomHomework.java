package Assignment1;

import java.util.*;

public class RandomHomework {
    public static void main(String[] argv) {
        RandomHomework randomHomework = new RandomHomework();
        System.out.println(randomHomework.getCourse());
    }

    private String getCourse () {
        List<String> courses = new ArrayList<String>();
        courses.add("Programming Design Paradigms");
        courses.add("Object Oriented Design");
        courses.add("Data Structures and Algorithms");
        courses.add("Data Mining");

        Random rand = new Random();
        return courses.get(rand.nextInt(4));
    }
}
