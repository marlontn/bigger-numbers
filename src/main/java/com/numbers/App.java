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
                BiggerInt n1 = new BiggerInt(arr[1]);
                BiggerInt n2 = new BiggerInt(arr[2]);
                System.out.println("n1: " + n1);
                System.out.println("n2: " + n2);
                switch (n1.compareTo(n2)) {
                    case 1:
                        System.out.println("n1 > n2");
                        break;
                    case -1:
                        System.out.println("n1 < n2");
                        break;
                    default:
                        System.out.println("n1 == n2");
                } // switch
                n1.add(n2);
                System.out.println("n1 + n2: " + n1);
                System.out.println("n2: " + n2);
                n1.sub(n2);
                System.out.println("n1: " + n1);
            } else if (arr[0].equals("d")) {
                BiggerDouble n1 = new BiggerDouble(arr[1]);
                BiggerDouble n2 = new BiggerDouble(arr[2]);
                System.out.println("n1: " + n1);
                System.out.println("n2: " + n2);
                /*
                switch (n1.compareTo(n2)) {
                    case 1:
                        System.out.println("n1 > n2");
                        break;
                    case -1:
                        System.out.println("n1 < n2");
                        break;
                    default:
                        System.out.println("n1 == n2");
                } // switch
                */
                n1.add(n2);
                System.out.println("n1 + n2:" + n1);
            } // if-else
        } // while
    } // main
} // App
