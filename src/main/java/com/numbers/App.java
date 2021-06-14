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

        System.out.println("Welcome! Enter h to see a list of commands/operations or q to quit");
        System.out.println();

        String input = "";
        while (!(input.equals("q") || input.equals("quit") || input.equals("exit"))) {
            System.out.print("Enter a command: ");
            input = scan.nextLine().toLowerCase();
            String[] arr = input.split(" ");
            if (arr[0].equals("i")) {
                BiggerInt n1 = new BiggerInt(arr[1]);
                String[] op = {"yuh"};
                while (true) {
                    System.out.print("Enter an operation: ");
                    op = scan.nextLine().toLowerCase().split(" ");
                    if (op[0].equals("so")) {
                        break;
                    } // if
                    BiggerInt n2 = new BiggerInt(op[1]);
                    System.out.println("n1: " + n1);
                    System.out.println("n2: " + n2);
                    switch (op[0]) {
                        case "+":
                        case "add":
                            n1.add(n2);
                            System.out.println("n1 + n2: " + n1);
                            break;
                        case "-":
                        case "sub":
                            n1.sub(n2);
                            System.out.println("n1 - n2: " + n1);
                            break;
                        case "*":
                        case "x":
                        case "mul":
                            n1.mul(n2);
                            System.out.println("n1 * n2: " + n1);
                            break;
                        case "/":
                        case "div":
                            n1.div(n2);
                            System.out.println("n1 / n2: " + n1);
                            break;
                        case "%":
                        case "rem":
                            System.out.println("n1 % n2: " + BiggerInt.rem(n1, n2));
                        case "comp":
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
                            break;
                    } // switch
                } // while
            } else if (arr[0].equals("d")) {
                BiggerDouble n1 = new BiggerDouble(arr[1]);
            } else if (arr[0].equals("h")) {
                // TODO: show command list
            }// if-else
        } // while
    } // main
} // App
