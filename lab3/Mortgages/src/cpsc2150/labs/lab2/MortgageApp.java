// Name: Ben Joye
// Date: 10.18.18

package cpsc2150.labs.lab2;
import java.util.Scanner;

public class MortgageApp {

    public static void main(String [] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the customer's name?");
        String name = scanner.nextLine();

        System.out.println("What is the customer's credit score?");
        int creditScore = scanner.nextInt();
        while (creditScore < 0 || creditScore > 850) {
            System.out.println("Credit score must be between 0 and 850.");
            System.out.println("What is the customer's credit score?");
            creditScore = scanner.nextInt();
        }

        System.out.println("What is the customer's income?");
        double income = scanner.nextDouble();
        while (income < 0) {
            System.out.println("Income must be greater than zero.");
            System.out.println("What is the customer's income?");
            income = scanner.nextDouble();
        }

        System.out.println("What is the customer's debt?");
        double debt = scanner.nextDouble();
        while (debt < 0) {
            System.out.println("Debt must be greater than zero.");
            System.out.println("What is the customer's debt?");
            debt = scanner.nextDouble();
        }

        System.out.println("What is the house cost?");
        double houseCost = scanner.nextDouble();
        while (houseCost < 0) {
            System.out.println("House cost must be greater than zero.");
            System.out.println("What is the house cost?");
            houseCost = scanner.nextDouble();
        }

        System.out.println("What is the down payment for the house?");
        double downPayment = scanner.nextDouble();
        while (downPayment < 0) {
            System.out.println("Down payment must be greater than zero.");
            System.out.println("What is the down payment for the house?");
            downPayment = scanner.nextDouble();
        }

        System.out.println("How many years is the mortgage for?");
        int years = scanner.nextInt();
        while (years != 15 && years != 20 && years != 25 && years != 30) {
            System.out.println("Year has to be 15, 20, 25, or 30.");
            System.out.println("How many years is the mortgage for?");
            years = scanner.nextInt();
        }


        Customer customer = new Customer(debt, income, creditScore, name);
        customer.applyForLoan(downPayment, houseCost, years);
        System.out.println(customer.toString());
//        Customer cust = new Customer(1200, 105000, 720, "John Campbell");
//        cust.applyForLoan(8000, 145000, 30);
//        System.out.println(cust.toString());


    }
}
