package sdf.dec29;

import java.util.*;

public class StreamMain {

    public static void main(String... args) {


        List<String> words = Arrays.asList("big", "black", "bug", "bleeds", "black", "blood");
        List<String> uppercase = new LinkedList<>();

        // traditional way
        for (String s: words) {
            // filter
            if (s.length() <= 3)
                continue;
            // transformation
            String uc = s.toUpperCase();
            uppercase.add(uc);
        }

        System.out.println(">>> " + uppercase);

        List<String> converted = words.stream()
            .filter(v -> v.length() > 3)
            .map(String::toUpperCase)
            .toList();

        System.out.println(">>> " + converted);

        String sentence = words.stream()
            .filter(v -> v.length() > 3)
            .map(v -> v.toUpperCase())
            .reduce(">>>>> ", (s0, s1) -> "%s %s".formatted(s1, s0));

        System.out.println(">>> " + sentence);
    }
    
}
