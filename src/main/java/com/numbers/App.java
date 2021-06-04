package com.numbers;

import java.util.Scanner;

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
        Scanner scan = new Scanner(System.in);

        String input = "";
        while (!(input.equals("q") || input.equals("quit") || input.equals("exit"))) {
            System.out.print("Enter a command: ");
            input = scan.nextLine().toLowerCase();
            String[] arr = input.split(" ");
            if (arr[0].equals("i")) {
                BiggerInt n = new BiggerInt(arr[1]);
                System.out.println(n);
            } else if (arr[0].equals("d")) {
                BiggerDouble n = new BiggerDouble(arr[1]);
                System.out.println(n);
            } // if-else
        } // while
    } // main
} // App
