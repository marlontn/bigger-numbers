package com.numbers;
import java.util.ArrayList;

/**
 * BiggerNum
 */
public abstract class BiggerNum {
    protected ArrayList<Integer> num;

    public BiggerNum() {
        this.num = new ArrayList<Integer>();
    } // BiggerNum

    public abstract void add(BiggerNum n); // add

    public abstract void sub(BiggerNum n); // sub

    public abstract void mult(BiggerNum n); // multiply

    public abstract void div(BiggerNum n); // divide
} // BiggerNum
