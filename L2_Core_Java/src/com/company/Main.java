package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // write your code here

        // 2 ways of coding

        // 1. iterative way

        System.out.println("Available Processors : "+Runtime.getRuntime().availableProcessors());

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        numbers.add(7);
        numbers.add(8);
        numbers.add(9);
        numbers.add(10);

        // problem : given a List of numbers. find the summation of all the square of even numbers.

        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
//            System.out.println("Inside For Loop : 1");
            if (numbers.get(i) % 2 == 0) {
//                System.out.println("Inside For Loop : 2");
                sum += numbers.get(i) * numbers.get(i);
            }
        }
        System.out.println("Sum : " + sum);

        // 2. declarative way.

        // clearly visible functionality.
        // displaying the task or functionality you are performing.
        // More readable.

        // Stream :

        // numbers.stream() : it will return stream of elements.

        // filter : it is filtering the elements based on condition. if true then element will forwarded else not.

//        int result = numbers.stream().filter(new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer integer) {// filter even elements.
////                System.out.println("Inside Predicate");
//                return integer % 2 == 0;
//            }
//        }).map(new Function<Integer, Integer>() {           // square of elements.
//            @Override
//            public Integer apply(Integer integer) {
////                System.out.println("Inside Function");
//                return integer * integer;
//            }
//        }).reduce(0, new BinaryOperator<Integer>() {
//            @Override
//            public Integer apply(Integer integer, Integer integer2) {
//                return integer + integer2;              // sum of squares.
//            }
//        });

        Integer result = numbers.parallelStream()
                .filter((number) -> {
//            System.out.println("Inside Filter : "+number);
                    System.out.println("Thread : "+Thread.currentThread().getName());
                    return number % 2 == 0;
                }).map(number -> {
//            System.out.println("Inside Map : "+number);
                    System.out.println("Thread : "+Thread.currentThread().getName());
                    return number * number;
                })
                .reduce(0, (number1, number2) -> {
//            System.out.println("Inside Reduce : ");
                    return number1 + number2;
                });


//        List<Integer> squaresNumbers = numbers.stream().filter((number) -> {
//            System.out.println("Inside Filter : ");
//            return number % 2 == 0;
//        }).map(number -> {
//            System.out.println("Inside Map : ");
//            return number * number;
//        }).collect(Collectors.toList());

//        System.out.println("Square Numbers "+squaresNumbers);

//        Integer result = numbers.stream()
//                .filter(number -> number % 2 == 0)
//                .map(number -> number * number)
//                .reduce(0, (number1, number2) -> number1 + number2);

        System.out.println("Result : " + result);


    }
}
