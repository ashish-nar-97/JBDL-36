package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        // write your code here

//        JDK : compile from .java to .class
//        JRE : contains JVM where your .class file runs.

        System.out.println("Hello World");

        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        System.out.println(list);

        System.out.println();

        /*
        List
        Map
        Set

         */

        // problem : you are given a map of numbers and their frequencies
        // example (1, 2) ==> 1, 1
        // (2, 4) ==> 2, 2, 2, 2
        // we have to find the cumulative sum of all the numbers.
        // solution : 1*2 + 2*4 = 10

        //reference : instance
        //Map       : HashMap
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        HashMap<Integer, Integer> frequencyMap2 = new HashMap<>();
        frequencyMap.put(2, 4);
        frequencyMap.put(1, 45);
        frequencyMap.put(3, 50);

        frequencyMap.put(2, 5);

        int sum = 0;

        Set<Map.Entry<Integer, Integer>> entrySet = frequencyMap.entrySet();

        // Enhanced for loop.
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            sum += entry.getKey() * entry.getValue();
        }
        System.out.println("Sum : " + sum);


        // reference is implementation class and instance is implementation class.
        MyHashMap myHashMap = new MyHashMap();
        System.out.println("myHashMap : " + myHashMap.add(1, 2));

        // reference is interface and instance is implementation class.
        MyMap myMap = new MyHashMap();
        System.out.println("myMap : " + myMap.add(10, 20));

        // reference is interface and instance is interface.
        // anonymous function
        MyMap myMap2 = new MyMap() {
            @Override
            public int add(int a, int b) {
                return a + b + 1;
            }
        };
        // lambda expression :
//        () -> {}
//        -> : lambda.
//        LHS : parameteres to pass i function
//        RHS : function definition.

//        MyMap myMap3 = (c, d) -> c+d+1; // single line lambda.

        // multi line lambda.
        MyMap myMap3 = (c, d) -> {
            System.out.println(c + d);
            return c + d + 1;
        };

//        System.out.println("myMap : " + myMap.add(10, 20));
        System.out.println("MyMap 2 : " + myMap2.add(10, 20));
//        System.out.println("myMap default method : " + myMap.multiply(1, 2));
        System.out.println("MyMap 3 : " + myMap3.add(10, 20));


//        Functional Interface :
        // Note : Any interface which has only one abstract method and any number of default methods
        // is called Functional Interfaces.

/*
    Next Class :
    Streams
    MultiThreading
    Exception Handling

    OR

    Maven
 */


    }

    private static class InnerClass {
        private int x;

        public void func(int s) {
            this.x = s;
            System.out.println("X : " + x);
        }
    }
}
