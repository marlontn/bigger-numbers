package com.numbers;

/**
 * BiggerInt
 */
public class BiggerInt extends BiggerNum {

    public BiggerInt() {
        super();
        this.num.add(0);
    } // BiggerInt

    public BiggerInt(long n) {
        super();
        while (n > 0) {
            this.num.add(0, (int) (n % 10));
            n /= 10;
        } // while
    } // BiggerInt

    @Override
    public void add(BiggerNum n) {
        

    } // add

    @Override
    public void sub(BiggerNum n) {
        // TODO Auto-generated method stub

    } // sub

    @Override
    public void mult(BiggerNum n) {
        // TODO Auto-generated method stub

    } // mult

    @Override
    public void div(BiggerNum n) {
        // TODO Auto-generated method stub

    } // div

    public String toString() {
        String str = "";
        for (int i : this.num) {
            str += i;
        } // for
        return str;
    } // toString

} // BiggerInt
