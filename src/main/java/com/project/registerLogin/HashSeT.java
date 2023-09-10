package com.project.registerLogin;

import io.swagger.models.auth.In;
import java.util.*;

public class HashSeT {

    public void hashSet() {

        //        Syntex Hashset
        HashSet<String> setName=new HashSet<>();
        System.out.println(setName);

//        Insertion
        setName.add("1");
        setName.add("1");
        setName.add("2");
        setName.add("4");
        setName.add("1");
        setName.add("5");
        setName.add("6");


//        Seraching
        if(setName.contains("2"))
            System.out.println("Present");
        else System.out.println("Not Present");

        setName.remove("1");
        System.out.println(setName.size());
        System.out.println(setName);

        Iterator it = setName.iterator();
//        Hasnext ;next
        while(it.hasNext())
        {
            System.out.print(it.next()+" ");
        }
        System.out.println("");
    }
    public static class HashMap1 {
        public void HashMaps(){
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

    public  static void main(String[] arg)
    {
        HashSeT hashSeT =new HashSeT();
        HashMap1 hashMap1 = new HashMap1();
        hashMap1.HashMaps();
        hashSeT.hashSet();
    }

}
