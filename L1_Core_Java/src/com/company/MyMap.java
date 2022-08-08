package com.company;

@FunctionalInterface
public interface MyMap {

    int x = 10;

    int add(int a, int b);

//    int subtract(int a, int b);

    default int multiply(int a, int b){
        return a*b;
    }

    default int multiply2(int a, int b){
        return a*b;
    }

    default int multiply3(int a, int b){
        return a*b;
    }
}
