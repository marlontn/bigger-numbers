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
        if (n.charAt(0) == '-') {
            sign = '1';
            n = n.substring(1);
        } // if the number is negative

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

    /**
     * Gets this number's nth (0-based) whole digit (pre-decimal point).
     * 
     * @param n the desired position
     * @return the digit at the given position
     */
    public int getWholeDigit(int n) {
        return whole.get(n);
    } // getWholeDigit

    /**
     * Gets this number's nth (0-based) decimal digit (post-decimal point).
     * 
     * @param n the desired position
     * @return the digit at the given position
     */
    public int getDecimalDigit(int n) {
        return decimal.get(n);
    } // getDecimalDigit

    /**
     * Gets the total number of digits in this number.
     * 
     * @return the number of digits
     */
    public int getNumDigits() {
        return whole.size() + decimal.size();
    } // getNumDigits

    /**
     * Gets the total number of whole digits (pre-decimal point) in this number.
     * 
     * @return the number of digits
     */
    public int getNumWholes() {
        return whole.size();
    } // getNumWholes

    /**
     * Gets the total number of decimal digits (post-decimal point) in this number.
     * 
     * @return the number of digits
     */
    public int getNumDecimals() {
        return decimal.size();
    } // getNumDecimals

    @Override
    public String toString() {
        String str = "";
        if (sign == '1') {
            str += "-";
        } // if number is negative
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
