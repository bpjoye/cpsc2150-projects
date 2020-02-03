// Name: Ben Joye
// Date: 11.13.18

package cpsc2150.mortgages;
import java.util.Scanner;

public class MortgageView {

    // this class is the only one with access to the scanner
    private Scanner scanner = new Scanner(System.in);

    // default constructor
    MortgageView() {}

    // asks user for house cost
    public double getHouseCost() {
        System.out.println("How much does the house cost?");
        return scanner.nextDouble();
    }

    // asks user for down payment
    public double getDownPayment() {
        System.out.println("How much is the down payment?");
        return scanner.nextDouble();
    }

    // asks user for years
    public int getYears() {
        System.out.println("How many years?");
        return scanner.nextInt();
    }

    // asks user for monthly debt
    public double getMonthlyDebt() {
        System.out.println("How much are your monthly debt payments?");
        return scanner.nextDouble();
    }

    // asks user for yearly income
    public double getYearlyIncome() {
        System.out.println("How much is your yearly income?");
        return scanner.nextDouble();
    }

    // asks user for credit score
    public int getCreditScore() {
        System.out.println("What is your credit score?");
        return scanner.nextInt();
    }

    // asks user for name
    public String getName() {
        System.out.println("What's your name?");
        return scanner.nextLine();
    }

    // prints a string to the user
    public void printToUser(String message) {
        System.out.println(message);
    }

    // asks user if they want another mortgage
    public boolean getAnotherMortgage() {
        System.out.println("Would you like to apply for another mortgage? Y/N");
        char c = scanner.next().charAt(0);

        while (c != 'y' && c != 'Y' && c != 'n' && c != 'N') {
            System.out.println("Would you like to apply for another mortgage? Y/N");
            c = scanner.next().charAt(0);
        }

        if (c == 'y' || c == 'Y')
            return true;
        else
            return false;
    }

    // asks the user if they want another customer
    public boolean getAnotherCustomer() {

        System.out.println("Would you like to consider another customer? Y/N");
        char c = scanner.next().charAt(0);

        while (c != 'y' && c != 'Y' && c != 'n' && c != 'N') {
            System.out.println("Would you like to consider another customer? Y/N");
            c = scanner.next().charAt(0);
        }
        scanner.nextLine();

        if (c == 'y' || c == 'Y')
            return true;
        else
            return false;
    }
}
