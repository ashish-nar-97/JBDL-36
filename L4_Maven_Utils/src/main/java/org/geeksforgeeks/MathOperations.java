package org.geeksforgeeks;

public class MathOperations {

    public static int add(int a, int b){
        return a+b;
    }

    public static int subtract(int a, int b){
        return a-b;
    }

    public static int multiply(int a, int b){
        return a*b;
    }

    public static int divide(int a, int b){
        return a/b;
    }

    public static int power(int a){
        return (int) Math.pow(a, 2);
    }

    public static String concatenate(String firstName, String lastName){
        return firstName.concat(lastName);
    }

}
