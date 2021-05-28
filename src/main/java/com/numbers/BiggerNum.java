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

    /**
     * Creates a new number.
     */
    public BiggerNum() {
        this.num = new ArrayList<Character>();
    } // BiggerNum

    /**
     * Adds another number to this number
     * 
     * @param n the number to add
     */
    public abstract void add(BiggerNum n);

    /**
     * Subtracts another number from this number
     * 
     * @param n the number to subtract
     */
    public abstract void sub(BiggerNum n);

    /**
     * Multiplies this number by another number
     * 
     * @param n the multiplier
     */
    public abstract void mult(BiggerNum n);

    /**
     * Divides this number by another number
     * 
     * @param n the divisor
     */
    public abstract void div(BiggerNum n);
} // BiggerNum
