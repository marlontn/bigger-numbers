package com.numbers;

/**
 * A numerical data type able to handle integers of magnitudes far greater than those allowed by the
 * <code>Integer</code> class. Extends the <code>BiggerNum</code> class and is heavily based on
 * <code>BigInteger</code>.
 * 
 * @author marlontn
 */
public class BiggerInt extends BiggerNum {

    /**
     * Creates a new integer and initializes it to 0.
     */
    public BiggerInt() {
        super();
        num.add((char) 0);
    } // BiggerInt

    /**
     * Creates a new integer and initializes it to the given <code>long</code> value.
     * 
     * @param n this integer's initial value
     */
    public BiggerInt(long n) {
        this(String.valueOf(n));
    } // BiggerInt

    /**
     * Creates a new integer and initializes it to the given <code>String</code> value.
     * 
     * @param n this integer's initial value
     * @throws NumberFormatException if the given string cannot be converted to an integer
     */
    public BiggerInt(String n) throws NumberFormatException {
        super();
        if (n.charAt(0) == '-') {
            sign = '1';
            n = n.substring(1);
        } // if the number is negative

        for (char c : n.toCharArray()) {
            if (Character.isDigit(c)) {
                num.add((char) Character.getNumericValue(c));
            } else {
                throw new NumberFormatException("Could not convert String to an Integer");
            } // if-else
        } // for-each
        if (num.size() == 0) {
            num.add((char) 0);
        } // if
    } // BiggerInt

    @Override
    public <T extends BiggerNum> void add(T n) {
        if (n instanceof BiggerInt) {
            return;
        } else if (n instanceof BiggerDouble) {
            add(BiggerInt.valueOf((BiggerDouble) n));
        } // if-else
    } // add


    @Override
    public <T extends BiggerNum> void sub(T n) {
        // TODO Auto-generated method stub

    } // sub

    @Override
    public <T extends BiggerNum> void mult(T n) {
        // TODO Auto-generated method stub

    } // mult

    @Override
    public <T extends BiggerNum> void div(T n) {
        // TODO Auto-generated method stub

    } // div

    /**
     * Gets this number's nth (0-based) digit.
     * 
     * @param n the desired position
     * @return the digit at the given position
     */
    public int getDigit(int n) {
        return num.get(n);
    } // getDigit
    
    /**
     * Gets the total number of digits in this number.
     * 
     * @return the number of digits
     */
    public int getNumDigits() {
        return num.size();
    } // getNumDigits

    @Override
    public String toString() {
        String str = "";
        if (sign == '1') {
            str += "-";
        } // if number is negative
        for (int i : num) {
            str += i;
        } // for
        return str;
    } // toString

    /**
     * Converts the given <code>BiggerDouble</code> into a BiggerInt.
     * 
     * @param n the number to convert
     * @return the given number as a BiggerInt
     */
    public static BiggerInt valueOf(BiggerDouble n) {
        BiggerInt num = new BiggerInt();
        for (int i = 0; i < n.getNumWholes(); i++) {
            num.num.add((char) n.getWholeDigit(i));
        } // for
        return new BiggerInt();
    } // valueOf
} // BiggerInt
