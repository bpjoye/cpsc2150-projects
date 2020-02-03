// Name: Ben Joye
// Date: 11.13.18

package cpsc2150.mortgages;

public class MortgageController {

    private MortgageView view;

    // constructor, stores the view
    MortgageController(MortgageView v) {
        view = v;
    }

    // controls getting new customer information
    public Customer newCustomer() {

        String name;
        double income;
        double debt;
        int creditScore;

        name = view.getName();

        income = view.getYearlyIncome();
        while (income <= 0) {
            view.printToUser("Income must be greater than 0.");
            income = view.getYearlyIncome();
        }

        debt = view.getMonthlyDebt();
        while (debt < 0) {
            view.printToUser("Debt must be greater than or equal to 0.");
            debt = view.getMonthlyDebt();
        }

        creditScore = view.getCreditScore();
        while (creditScore >= 850 || creditScore <= 0) {
            view.printToUser("Credit Score must be greater than 0 and less than 850");
            creditScore = view.getCreditScore();
        }

        // creates customer object, returns it
        Customer customer = new Customer(debt, income, creditScore, name);
        return customer;
    }


    // controls getting new mortgage information
    public void newMortgage(Customer customer) {

        double houseCost;
        double downPayment;
        int years;

        houseCost = view.getHouseCost();
        while (houseCost <= 0) {
            view.printToUser("Cost must be greater than 0.");
            houseCost = view.getHouseCost();
        }

        downPayment = view.getDownPayment();
        while (downPayment <= 0 || downPayment >= houseCost) {
            view.printToUser("Down Payment must be greater than 0 and less than the cost of the house.");
            downPayment = view.getDownPayment();
        }

        years = view.getYears();
        while (years <= 0) {
            view.printToUser("Years must be greater than 0.");
            years = view.getYears();
        }

        // applies for a loan with new info
        customer.applyForLoan(downPayment, houseCost, years);
    }


    // main function of control class
    public void run() {

        boolean newC = false;
        boolean newM = false;

        Customer customer = newCustomer();
        newMortgage(customer);
        System.out.println(customer.toString());

        newM = view.getAnotherMortgage();
        if (!newM) {
            newC = view.getAnotherCustomer();
        }

        // program loop
        while (newC || newM) {

            if (newC) {
                customer = newCustomer();
                newMortgage(customer);
                System.out.println(customer.toString());
            } else if (newM) {
                newMortgage(customer);
                System.out.println(customer.toString());
            }

            newC = false;
            newM = view.getAnotherMortgage();
            if (!newM) {
                newC = view.getAnotherCustomer();
            }

        }
    }
}
