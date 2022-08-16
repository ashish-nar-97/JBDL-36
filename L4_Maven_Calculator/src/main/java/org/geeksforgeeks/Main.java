package org.geeksforgeeks;

public class Main {
    public static void main(String[] args) {

        int a = 30, b = 20;

        System.out.println("Addition of a = "+a+" and B = "+b+" Sum = "+MathOperations.add(a, b));
        System.out.println("Subtraction of a = "+a+" and B = "+b+" Sum = "+MathOperations.subtract(a, b));
        System.out.println("Multiplication of a = "+a+" and B = "+b+" Sum = "+MathOperations.multiply(a, b));
        System.out.println("Division of a = "+a+" and B = "+b+" Sum = "+MathOperations.divide(a, b));

        System.out.println("Power of "+b+" = "+MathOperations.power(20));
        System.out.println("Full Name : "+MathOperations.concatenate("Ashish", "Nar"));
    }
}