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

        whole.addAll(n.whole);
        decimal.addAll(n.decimal);
        sign = n.sign;
    } // BiggerDouble

    @Override
    public <T extends BiggerNum> void add(final T n) {

    } // add

    /**
     * Adds 0 or more given numbers to a given initial number.
     * 
     * @param n the number to add to
     * @param nums the optional number(s) to be added
     * @return the result of the sum(s)
     */
    public static BiggerDouble add(final BiggerDouble n, final BiggerDouble... nums) {
        BiggerDouble res = new BiggerDouble(n);
        for (BiggerDouble x : nums) {
            res.add(x);
        } // for
        return res;
    } // add

    @Override
    public <T extends BiggerNum> void sub(final T n) {
        // TODO Auto-generated method stub

    } // sub

    /**
     * Subtracts 0 or more given numbers from a given initial number.
     * 
     * @param n the number to subtract from
     * @param nums the optional number(s) to be subtracted
     * @return the result of the subtraction(s)
     */
    public static BiggerDouble sub(final BiggerDouble n, final BiggerDouble... nums) {
        BiggerDouble res = new BiggerDouble(n);
        for (BiggerDouble x : nums) {
            res.add(x);
        } // for
        return res;
    } // sub

    @Override
    public <T extends BiggerNum> void mul(final T n) {
        // TODO Auto-generated method stub

    } // mul

    /**
     * Multiplies 0 or more given numbers with a given initial number.
     * 
     * @param n the number to multiply to
     * @param nums the optional multiplier(s)
     * @return the result of the multiplication(s)
     */
    public static BiggerDouble mul(final BiggerDouble n, final BiggerDouble... nums) {
        BiggerDouble res = new BiggerDouble(n);
        for (BiggerDouble x : nums) {
            res.add(x);
        } // for
        return res;
    } // mul

    /**
     * {@inheritDoc}
     * 
     * @throws ArithmeticException if there is a divide-by-zero error
     */
    @Override
    public <T extends BiggerNum> void div(final T n) throws ArithmeticException {
        // TODO Auto-generated method stub

    } // div

    /**
     * Divides a given initial number by 0 or more numbers.
     * 
     * @param n the dividend
     * @param nums the optional divisor(s)
     * @return the result of the division(s)
     */
    public static BiggerDouble div(final BiggerDouble n, final BiggerDouble... nums) {
        BiggerDouble res = new BiggerDouble(n);
        for (BiggerDouble x : nums) {
            res.add(x);
        } // for
        return res;
    } // div

    /**
     * Finds the remainder when dividing n1 by n2.
     * 
     * @param n1 the dividend
     * @param n2 the divisor
     * @return the remainder
     */
    public static BiggerDouble rem(final BiggerDouble n1, final BiggerDouble... n2) {
        BiggerDouble quotient = BiggerDouble.div(n1, n2);
        BiggerDouble mult = BiggerDouble.mul(quotient, n2);
        return BiggerDouble.sub(n1, mult);
    } // rem

    /**
     * Shifts this number's digits by a specified amount. If n > 0, it shifts the digits to the
     * right, moving the decimal point n places left (equivalent to num / 10^n). If n < 0, it
     * shifts the digits to the left, moving the decimal point n places right (equivalent to num *
     * 10^|n|). If n = 0, nothing is done.
     * 
     * @param n the shift amount
     */
    public void shift(int n) {
        while (n > 0) {
            char c = whole.size() > 0 ? whole.remove(whole.size() - 1) : 0;
            decimal.add(0, c);
            n--;
        } // while there are n > 0 digits to shift right
        while (n < 0) {
            char c = decimal.size() > 0 ? decimal.remove(0) : 0;
            whole.add(c);
            n++;
        } // while there are |n| > 0 digits to shift left
        normalize();
    } // shift

    /**
     * Shifts this number's digits left once (equivalent to num * 10).
     */
    public void shiftLeft() {
        shift(-1);
    } // shiftLeft

    /**
     * Shifts this number's digits right once (equivalent to num / 10).
     */
    public void shiftRight() {
        shift(1);
    } // shiftRight

    /**
     * Removes leading zeros (pre-decimal point) and trailing zeros (post-decimal point).
     */
    public void normalize() {
        if (whole.size() == 0) {
            whole.add((char) 0);
        } // if the numbmer has no whole digits
        if (decimal.size() == 0) {
            decimal.add((char) 0);
        } // if the number has no decimal digits
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
            res.sign = x.sign;
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
        BiggerInt x = BiggerInt.valueOf(this);
        BiggerInt y = BiggerInt.valueOf(n);
        int c = x.compareTo(y);
        if (c != 0) {
            return c;
        } // if the whole number parts of n and this number are different

        int l1 = getNumDecimals();
        int l2 = n.getNumDecimals();
        int i = 0;
        for (; i < l1; i++) {
            if (i >= l2 || decimal.get(i) > n.decimal.get(i)) {
                return sign == 0 ? 1 : -1; // 0.2 > 0.1, but -0.2 < -0.1
            } // if l1 > l2 or if this number's decimal digit at i is greater than n's
            if (decimal.get(i) < n.decimal.get(i)) {
                return sign == 0 ? -1 : 1; // 0.1 < 0.2, but -0.1 > -0.2
            } // if this number's decimal digit at i is less than n's
        } // for
        if (i < l2) {
            return sign == 0 ? -1 : 1;
        } // if l2 > l1
        return 0;
    } // compareTo
}
