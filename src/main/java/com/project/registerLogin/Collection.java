package com.project.registerLogin;

import java.util.HashMap;
import java.util.Map;

public class Collection {
    public static void main (String arg[]){
        Map<String, Integer> studentScores = new HashMap<>();
        studentScores.put("Alice", 95);
        studentScores.put("Bob", 88);
        studentScores.put("Charlie", 76);
        studentScores.put("Buck", 88);
        studentScores.put("Chad", 76);
        System.out.println(studentScores.containsKey("Bob"));
        System.out.println(studentScores.size());
        System.out.println(studentScores.keySet());
    }

}
