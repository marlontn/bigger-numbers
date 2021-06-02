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
        super();
        while (n > 0) {
            num.add(0, (char) (n % 10)); // adds last digit of n to num
            n /= 10; // takes off n's last digit
        } // while
    } // BiggerInt

    /**
     * Creates a new integer and initializes it to the given <code>String</code> value.
     * 
     * @param n this integer's initial value
     * @throws NumberFormatException if the given string cannot be converted to an integer
     */
    public BiggerInt(String n) throws NumberFormatException {
        super();
        for (char c : n.toCharArray()) {
            if (Character.isDigit(c)) {
                num.add((char) Character.getNumericValue(c));
            } else {
                throw new NumberFormatException("Could not convert String to an Integer");
            } // if-else
        } // for-each
        if (num.size() == 0) {
            num.add((char) 0);
        }  // if
    } // BiggerInt

    @Override
    public void add(BiggerNum n) {
        if (n instanceof BiggerInt) {

        } else if (n instanceof BiggerDouble) {

        } // if-else
    } // add

    public void add(BiggerInt n) {

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
        for (int i : num) {
            str += i;
        } // for
        return str;
    } // toString
} // BiggerInt
