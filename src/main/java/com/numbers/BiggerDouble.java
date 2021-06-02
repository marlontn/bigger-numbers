package com.numbers;

import java.util.ArrayList;

/**
 * A numerical data type able to handle doubles of magnitudes far greater than those allowed by the
 * <code>Double</code> class. Extends the <code>BiggerNum</code> class and is heavily based on
 * <code>BigDecimal</code>.
 * 
 * @author marlontn
 */
public class BiggerDouble extends BiggerNum {
    protected ArrayList<Character> whole;
    protected ArrayList<Character> decimal;

    /**
     * Creates a new double and initializes it to 0.0.
     */
    public BiggerDouble() {
        whole = new ArrayList<Character>();
        decimal = new ArrayList<Character>();
        whole.add((char) 0);
        decimal.add((char) 0);
    } // BiggerDouble

    /**
     * Creates a new double and initializes it to the given <code>double</code> value.
     * 
     * @param n this double's initial value
     */
    public BiggerDouble(double n) {
        this(String.valueOf(n));
    } // BiggerDouble

    /**
     * Creates a new integer and initializes it to the given <code>String</code> value.
     * 
     * @param n this integer's initial value
     * @throws NumberFormatException if the given string cannot be converted to an integer
     */
    public BiggerDouble(String n) throws NumberFormatException {
        whole = new ArrayList<Character>();
        decimal = new ArrayList<Character>();

        boolean pass = true;
        for (char c : n.toCharArray()) {
            if (Character.isDigit(c)) {
                if (pass) {
                    whole.add((char) Character.getNumericValue(c));
                } else {
                    decimal.add((char) Character.getNumericValue(c));
                } // if-else
            } else if (c == '.') {
                pass = false;
            } else {
                throw new NumberFormatException("Could not convert String to an Integer");
            } // if-else
        } // for-each
        if (whole.size() == 0) {
            whole.add((char) 0);
        } // if
        if (decimal.size() == 0) {
            decimal.add((char) 0);
        } // if
    } // BiggerDouble

    @Override
    public void add(BiggerNum n) {
        // TODO Auto-generated method stub

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

    @Override
    public String toString() {
        String str = "";
        for (int i : whole) {
            str += i;
        } // for
        str += ".";
        for (int i : decimal) {
            str += i;
        } // for
        return str;
    } // toString
}
