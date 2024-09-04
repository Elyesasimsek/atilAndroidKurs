package com.example.a002javalearning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Arrays {
    public static void main(String[] args) {
        //array
        String[] myStringArray = new String[3];
        myStringArray[0] = "Elyesa";
        myStringArray[1] = "Fatma";
        myStringArray[2] = "Meryem";

        System.out.println(myStringArray[2]);

        int[] myIntegerArray = new int[3];
        myIntegerArray[0] = 25;
        myIntegerArray[1] = 26;
        myIntegerArray[2] = 0;

        System.out.println(myIntegerArray[2]);

        int[] myNumberArray = {1,2,3,4,5,6,7,8,9};
        System.out.println(myNumberArray[2]);

        //list
        ArrayList<String> myFamily = new ArrayList<>();
        myFamily.add("Elyesa");
        myFamily.add("Fatma");
        myFamily.add("Meryem");

        System.out.println(myFamily.get(0));
        System.out.println(myFamily.get(1));
        System.out.println(myFamily.get(2));

        System.out.println(myFamily.size());

        //set

        HashSet<String> mySet = new HashSet<>();
        mySet.add("Elyesa");
        mySet.add("Elyesa");

        System.out.println(mySet.size());

        //map

        HashMap<String, String> myHashMap = new HashMap<>();
        myHashMap.put("name", "Elyesa");
        myHashMap.put("family", "father");

        System.out.println(myHashMap.get("name"));
        System.out.println(myHashMap.get("family"));

        HashMap<String, Integer> mySecondMap = new HashMap<>();

        mySecondMap.put("run", 100);
        mySecondMap.put("basketball", 200);

        System.out.println(mySecondMap.get("run"));
    }
}
