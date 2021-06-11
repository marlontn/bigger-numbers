package com.numbers;

import java.util.ArrayList;

/**
 * An abstract numerical data type able to handle numbers of magnitudes far greater than those
 * allowed by default Java classes and data types. Each digit is stored as a <code>Character</code>
 * in an <code>ArrayList</code>.
 * 
 * @author marlontn
 */
public abstract class BiggerNum {
    protected ArrayList<Character> num;
    protected char sign;

    /**
     * Creates a new number.
     */
    public BiggerNum() {
        num = new ArrayList<Character>();
        sign = 0; // positive
    } // BiggerNum

    /**
     * Adds another number to this number
     * 
     * @param n the number to add
     */
    public abstract <T extends BiggerNum> void add(final T n);

    /**
     * Subtracts another number from this number
     * 
     * @param n the number to subtract
     */
    public abstract <T extends BiggerNum> void sub(final T n);

    /**
     * Multiplies this number by another number
     * 
     * @param n the multiplier
     */
    public abstract <T extends BiggerNum> void mul(final T n);

    /**
     * Divides this number by another number
     * 
     * @param n the divisor
     */
    public abstract <T extends BiggerNum> void div(final T n);
} // BiggerNum
