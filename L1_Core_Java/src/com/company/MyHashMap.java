package com.company;

public class MyHashMap implements MyMap{

    int x = 15;

    @Override
    public int add(int a, int b) {
        return a+b;
    }

//    @Override
//    public int subtract(int a, int b) {
//        return a-b;
//    }

    public int divide(int a, int b){
        return a/b;
    }

    public int getElement(){
        return this.x;
    }
}
