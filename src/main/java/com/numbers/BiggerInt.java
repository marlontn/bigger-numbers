package com.numbers;

/**
 * A numerical data type able to handle integers of magnitudes far greater than those allowed by the
 * <code>Integer</code> class. Extends the <code>BiggerNum</code> class and is heavily based on
 * <code>BigInteger</code>.
 * 
 * @author marlontn
 */
public class BiggerInt extends BiggerNum implements Comparable<BiggerInt> {

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
            sign = 1;
            n = n.substring(1);
        } // if the number is negative

        for (char c : n.toCharArray()) {
            if (Character.isDigit(c)) {
                num.add((char) Character.getNumericValue(c));
            } else {
                throw new NumberFormatException("Could not convert String to an Integer.");
            } // if-else
        } // for-each
        if (num.size() == 0) {
            num.add((char) 0);
        } // if
        normalize();
    } // BiggerInt

    /**
     * Creates a new integer and initializes it to hold the given BiggerInt's digits.
     * 
     * @param n this integer's initial value
     */
    public BiggerInt(BiggerInt n) {
        super();
        num.addAll(n.num);
        sign = n.sign;
    } // BiggerInt

    @Override
    public <T extends BiggerNum> void add(final T n) {
        if (n instanceof BiggerInt) {
            BiggerInt x = new BiggerInt((BiggerInt) n);
            if (sign == 0 && x.sign == 1) { // if this number is positive but n is negative
                /* (num + -x) = (num - x) */
                x.sign = 0;
                sub(x);
                x.sign = 1; // unnecessary but done for consistency
            } else if (sign == 1 && x.sign == 0) { // if this number is negative but n is positive
                /* (-num + x) = -(num + -x) = -(num - x) */
                sign = 0;
                sub(x);
                sign = (char) (sign == 0 ? 1 : 0); // flips sign
            } else { // if signs are the same
                /* (num + x) = (num + x) */
                /* (-num + -x) = -(num + x), where the sign bit is already negative */
                int l1 = getNumDigits() - 1;
                int l2 = x.getNumDigits() - 1;
                int carry = 0;
                for (; l1 >= 0; l1--, l2--) {
                    int res = l2 >= 0 ? num.get(l1) + x.num.get(l2) : num.get(l1);
                    if (carry == 1) {
                        res++;
                        carry = 0;
                    } // if there is a carry bit from the previous addition
                    if (res > 9) {
                        res %= 10;
                        carry = 1;
                    } // if there needs to be a carry bit
                    num.set(l1, (char) res);
                } // for
                for (; l2 >= 0; l2--) {
                    int res = x.num.get(l2);
                    if (carry == 1) {
                        res++;
                        carry = 0;
                    } // if there is a carry bit from the previous addition
                    if (res > 9) {
                        res %= 10;
                        carry = 1;
                    } // if there needs to be a carry bit
                    num.add(0, (char) res);
                } // for
                if (carry == 1) {
                    num.add(0, (char) 1);
                } // if there is an extra carry bit
            } // if-else
        } else {
            add(BiggerInt.valueOf(n));
        } // if-else
        normalize();
    } // add

    /**
     * Adds 0 or more given numbers to a given initial number.
     * 
     * @param n the number to add to
     * @param nums the optional number(s) to be added
     * @return the result of the sum(s)
     */
    public static BiggerInt add(final BiggerInt n, final BiggerInt... nums) {
        BiggerInt res = new BiggerInt(n);
        for (BiggerInt x : nums) {
            res.add(x);
        } // for
        return res;
    } // add

    @Override
    public <T extends BiggerNum> void sub(final T n) {
        if (n instanceof BiggerInt) {
            BiggerInt x = new BiggerInt((BiggerInt) n);
            if (sign == 0 && x.sign == 1) { // if this number is positive but n is negative
                /* (num - -x) = (num + x) */
                x.sign = 0;
                add(x);
                x.sign = 1; // unnecessary but done for consistency
            } else if (sign == 1 && x.sign == 0) { // if this number is negative but n is positive
                /* (-num - x) = -(num - -x) = -(num + x) */
                sign = 0;
                add(x);
                sign = (char) (sign == 0 ? 1 : 0); // flips sign
            } else if (sign == 1 && x.sign == 1) { // if both numbers are negative
                /* (-num - -x) = -(num - x) */
                sign = 0;
                x.sign = 0;
                sub(x);
                sign = (char) (sign == 0 ? 1 : 0); // flips sign
                x.sign = 1; // unnecessary but done for consistency
            } else { // if both numbers are positive
                /* (num - x) = (num - x) */
                if (compareTo(x) == -1) { // if this number is less than n
                    /* (num - x) = -(x - num) */
                    BiggerInt copy = new BiggerInt(x);
                    copy.sub(this);
                    num.clear();
                    num.addAll(copy.num);
                    sign = (char) (x.sign == 0 ? 1 : 0); // flips sign
                } else { // if this number is greater than or equal to n
                    int l1 = getNumDigits() - 1;
                    int l2 = x.getNumDigits() - 1;
                    int carry = 0;
                    for (; l1 >= 0; l1--, l2--) {
                        int res = l2 >= 0 ? num.get(l1) - x.num.get(l2) : num.get(l1);
                        if (carry == 1) {
                            res--;
                            carry = 0;
                        } // if there is a carry bit from the previous subtraction
                        if (res < 0) {
                            res += 10;
                            carry = 1;
                        } // if there needs to be a carry bit
                        num.set(l1, (char) res);
                    } // for
                } // if-else
            } // if-else
        } else {
            sub(BiggerInt.valueOf(n));
        } // if-else
        normalize();
    } // sub

    /**
     * Subtracts 0 or more given numbers from a given initial number.
     * 
     * @param n the number to subtract from
     * @param nums the optional number(s) to be subtracted
     * @return the result of the subtraction(s)
     */
    public static BiggerInt sub(final BiggerInt n, final BiggerInt... nums) {
        BiggerInt res = new BiggerInt(n);
        for (BiggerInt x : nums) {
            res.sub(x);
        } // for
        return res;
    } // sub

    @Override
    public <T extends BiggerNum> void mul(final T n) {
        if (n instanceof BiggerInt) {
            BiggerInt x = new BiggerInt((BiggerInt) n);
            BiggerInt res = new BiggerInt();
            for (int l1 = getNumDigits(); l1 > 0; l1--) {
                BiggerInt temp = new BiggerInt();
                temp.num.clear(); // gets rid of extra zero
                int a = num.get(l1 - 1);
                int carry = 0;
                for (int l2 = x.getNumDigits(); l2 > 0; l2--) {
                    int b = x.num.get(l2 - 1);
                    int c = a * b;
                    if (carry > 0) {
                        c += carry;
                        carry = 0;
                    } // if there is a carry bit from the previous multiplication
                    temp.num.add(0, (char) (c % 10));
                    carry = c / 10; // max value of c is 89 so this should work
                } // for
                if (carry > 0) {
                    temp.num.add(0, (char) carry);
                } // if there is a leftover carry bit
                for (int i = getNumDigits(); i > l1; i--) {
                    temp.num.add((char) 0); // pads with zeroes to reflect shift
                } // for
                res.add(temp);
            } // for
            num.clear();
            num.addAll(res.num);
            sign = (char) (sign == x.sign ? 0 : 1); // positive if equal, negative otherwise
        } else {
            mul(BiggerInt.valueOf(n));
        } // if-else
        normalize();
    } // mul

    /**
     * Multiplies 0 or more given numbers with a given initial number.
     * 
     * @param n the number to multiply to
     * @param nums the optional multiplier(s)
     * @return the result of the multiplication(s)
     */
    public static BiggerInt mul(final BiggerInt n, final BiggerInt... nums) {
        BiggerInt res = new BiggerInt(n);
        for (BiggerInt x : nums) {
            res.mul(x);
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
        if (n instanceof BiggerInt) {
            BiggerInt x = new BiggerInt((BiggerInt) n);
            if (x.compareTo(new BiggerInt()) == 0) {
                throw new ArithmeticException("Cannot divide by zero!");
            } // if x = 0
            x.sign = 0; // makes sure comparisons are based on magnitude
            char saveSign = sign;
            sign = 0; // same as with x's sign
            if (compareTo(x) != -1) { // if this number is greater than or equal to n
                BiggerInt temp = new BiggerInt();
                BiggerInt xMult = new BiggerInt();
                temp.num.clear();
                int count = 0;
                for (int i = 0; i < num.size(); i++) {
                    temp.num.add(num.get(i));
                    int c = xMult.compareTo(temp);
                    while (c == -1) {
                        xMult.add(x);
                        c = xMult.compareTo(temp);
                        count++;
                    } // while the divisor multiple is less than the temporary dividend
                    if (c == 0) { // if the divisor multiple equals the temporary dividend
                        temp.num.clear();
                    } else { // if the divisor multiple is greater than the temporary dividend
                        xMult.sub(x);
                        count--;
                        temp.sub(xMult); // remainder
                    } // if-else
                    num.set(i, (char) count);
                } // for
            } else { // if this number is less than n
                num.clear();
                num.add((char) 0);
            } // if-else
            sign = (char) (saveSign == n.sign ? 0 : 1); // positive if equal, negative otherwise
        } else {
            div(BiggerInt.valueOf(n));
        } // if-else
        normalize();
    } // div

    /**
     * Divides a given initial number by 0 or more numbers.
     * 
     * @param n the dividend
     * @param nums the optional divisor(s)
     * @return the result of the division(s)
     */
    public static BiggerInt div(final BiggerInt n, final BiggerInt... nums) {
        BiggerInt res = new BiggerInt(n);
        for (BiggerInt x : nums) {
            res.div(x);
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
    public static BiggerInt rem(final BiggerInt n1, final BiggerInt... n2) {
        BiggerInt quotient = BiggerInt.div(n1, n2);
        BiggerInt mult = BiggerInt.mul(quotient, n2);
        return BiggerInt.sub(n1, mult);
    } // rem

    /**
     * Removes leading zeros.
     */
    public void normalize() {
        while (num.size() > 1 && num.get(0) == 0) {
            num.remove(0);
        } // while there are leading zeros
        if (num.get(0) == 0 && sign == 1) {
            sign = 0;
        } // if there is a negative sign for zero
    } // normalize

    /**
     * Gets this number's nth (0-based) digit.
     * 
     * @param n the desired position
     * @return the digit at the given position
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public int getDigit(int n) throws IndexOutOfBoundsException {
        if (n < 0 || n > getNumDigits()) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        } // if the index is out of bounds
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

    /**
     * Converts the given number into a BiggerInt.
     * 
     * @param n the number to convert
     * @return the given number as a BiggerInt
     */
    public static <T extends BiggerNum> BiggerInt valueOf(T n) {
        BiggerInt res = new BiggerInt();
        if (n instanceof BiggerDouble) {
            BiggerDouble x = (BiggerDouble) n;
            res.num.clear();
            res.num.addAll(x.whole);
            res.sign = x.sign;
        } // if n is a BiggerDouble
        return res;
    } // valueOf

    @Override
    public String toString() {
        String str = "";
        if (sign == 1) {
            str += "-";
        } // if number is negative
        for (int i : num) {
            str += i;
        } // for
        return str;
    } // toString

    @Override
    public int compareTo(BiggerInt n) {
        if (sign != n.sign) {
            return sign == 0 ? 1 : -1;
        } // if the numbers have different signs
        int l1 = getNumDigits();
        int l2 = n.getNumDigits();
        if (l1 > l2) {
            return sign == 0 ? 1 : -1; // 10 > 1, but -10 < -1
        } // if this number has more digits than n
        if (l1 < l2) {
            return sign == 0 ? -1 : 1; // 1 < 10, but -1 > -10
        } // if this number has less digits than n

        for (int i = 0; i < l1; i++) {
            if (num.get(i) > n.num.get(i)) {
                return sign == 0 ? 1 : -1; // 2 > 1, but -2 < -1
            } // if this number's digit at i is greater than n's
            if (num.get(i) < n.num.get(i)) {
                return sign == 0 ? -1 : 1; // 1 < 2, but -1 > -2
            } // if this number's digit at i is less than n's
        } // for each digit, from most to least significant

        return 0; // both numbers are exactly equal
    } // compareTo
} // BiggerInt
