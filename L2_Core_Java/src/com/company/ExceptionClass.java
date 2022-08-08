package com.company;

import java.io.*;
import java.util.Scanner;

public class ExceptionClass {

    public static void main(String[] args) {

        // 2 Types.
        // Unchecked Exception : Runtime Exception.
        // Checked Exception : Compile Time Exceptions.

        int a = 1/ 0;

        try {
            childFunction();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            System.out.println("In Finally Block");
        }

        // try with resource :
//        try(Scanner scanner = new Scanner(System.in)) {
//            int x = scanner.nextInt();
//            System.out.println(x);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }





    }

    public static void childFunction() throws IOException {
        //        try {

        FileReader fileReader = new FileReader("/user/ashish.file.txt");
        FileWriter fileWriter = new FileWriter("/user/ashish/file.txt");
        int a = 1 / 0;
//        System.out.println("Without Exception");

//        } catch (IOException e) {
////            e.printStackTrace();
//            System.out.println("Exception "+e.getMessage());
//        }
//        catch (ArithmeticException ex){
////            ex.printStackTrace();
//            System.out.println("Exception "+ex.getMessage());
//        }

    }
}
