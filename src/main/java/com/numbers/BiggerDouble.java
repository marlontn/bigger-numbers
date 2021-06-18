package com.numbers;

import java.util.ArrayList;

/**
 * A numerical data type able to handle doubles of magnitudes far greater than those allowed by the
 * <code>Double</code> class. Extends the <code>BiggerNum</code> class and is heavily based on
 * <code>BigDecimal</code>.
 * 
 * @author marlontn
 */
public class BiggerDouble extends BiggerNum implements Comparable<BiggerDouble> {
    protected ArrayList<Character> whole;
    protected ArrayList<Character> decimal;

    /**
     * Creates a new double and initializes it to 0.0.
     */
    public BiggerDouble() {
        whole = new ArrayList<Character>();
        decimal = new ArrayList<Character>();
        sign = 0;
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
     * Creates a new double and initializes it to the given <code>String</code> value.
     * 
     * @param n this double's initial value
     * @throws NumberFormatException if the given string cannot be converted to a double
     */
    public BiggerDouble(String n) throws NumberFormatException {
        whole = new ArrayList<Character>();
        decimal = new ArrayList<Character>();
        sign = 0;
        if (n.charAt(0) == '-') {
            sign = 1;
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
        normalize();
    } // BiggerDouble

    /**
     * Creates a new double and initializes it to hold the given BiggerDouble's digits.
     * 
     * @param n this double's initial value
     */
    public BiggerDouble(BiggerDouble n) {
        whole = new ArrayList<Character>();
        decimal = new ArrayList<Character>();
        sign = 0;
        whole.addAll(n.whole);
        decimal.addAll(n.decimal);
        sign = n.sign;
    } // BiggerDouble

    @Override
    public void add(BiggerNum n) {

    } // add

    @Override
    public void sub(BiggerNum n) {
        // TODO Auto-generated method stub

    } // sub

    @Override
    public void mul(BiggerNum n) {
        // TODO Auto-generated method stub

    } // mul

    /**
     * {@inheritDoc}
     * 
     * @throws ArithmeticException if there is a divide-by-zero error
     */
    @Override
    public void div(BiggerNum n) {
        // TODO Auto-generated method stub

    } // div

    /**
     * Removes leading zeros (pre-decimal point) and trailing zeros (post-decimal point).
     */
    public void normalize() {
        while (whole.size() > 1 && whole.get(0) == 0) {
            whole.remove(0);
        } // while there are leading zeros in the whole-number part
        while (decimal.size() > 1 && decimal.get(decimal.size() - 1) == 0) {
            decimal.remove(decimal.size() - 1);
        } // while there are trailing zeros in the decimal part
        if (whole.get(0) == 0 && decimal.get(0) == 0 && sign == 1) {
            sign = 0;
        } // if there is a negative sign for zero
    } // normalize

    /**
     * Gets this number's nth (0-based) whole digit (pre-decimal point).
     * 
     * @param n the desired position
     * @return the digit at the given position
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public int getWholeDigit(int n) throws IndexOutOfBoundsException {
        if (n < 0 || n > getNumWholes()) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        } // if the index is out of bounds
        return whole.get(n);
    } // getWholeDigit

    /**
     * Gets this number's nth (0-based) decimal digit (post-decimal point).
     * 
     * @param n the desired position
     * @return the digit at the given position
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public int getDecimalDigit(int n) throws IndexOutOfBoundsException {
        if (n < 0 || n > getNumDecimals()) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        } // if the index is out of bounds
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

    /**
     * Converts the given number into a BiggerDouble.
     * 
     * @param n the number to convert
     * @return the given number as a BiggerDouble
     */
    public static <T extends BiggerNum> BiggerDouble valueOf(T n) {
        BiggerDouble res = new BiggerDouble();
        if (n instanceof BiggerInt) {
            BiggerInt x = (BiggerInt) n;
            res.whole.clear();
            res.whole.addAll(x.num);
        } // if n is a BiggerInt
        return res;
    } // valueOf

    @Override
    public String toString() {
        String str = "";
        if (sign == 1) {
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

    @Override
    public int compareTo(BiggerDouble n) {
        return 0;
    } // compareTo
}
