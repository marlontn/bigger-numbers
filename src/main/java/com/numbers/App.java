package com.numbers;

/**
 * Driver for the <code>com.numbers</code> package.
 * 
 * @author marlontn
 */
public class App {
    /**
     * Driver main method.
     * 
     * @param args optional command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        BiggerInt n1 = new BiggerInt();
        System.out.println(n1);
        BiggerInt n2 = new BiggerInt(123456789);
        System.out.println(n2);
        BiggerInt n3 = new BiggerInt("123456789");
        System.out.println(n3);
    } // main
} // App
