// Ben Joye
// CPSC 2150
// 11/28/18

package cpsc2150.mortgages;

public class MortgageController {

    private IMortgageView view;

    public MortgageController(IMortgageView v) {
        view = v;
    }

    public void submitApplication() {

        String name;
        double income;
        double debt;
        int creditScore;
        double houseCost;
        double downPayment;
        int years;

        // keeps track of if all the data is good to calculate
        boolean calculate = true;

        name = view.getName();

        income = view.getYearlyIncome();
        if (income <= 0) {
            view.printToUser("Income must be greater than 0.");
            calculate = false;
        }

        debt = view.getMonthlyDebt();
        if (debt < 0) {
            view.printToUser("Debt must be greater than or equal to 0.");
            calculate = false;
        }

        creditScore = view.getCreditScore();
        if (creditScore >= 850 || creditScore <= 0) {
            view.printToUser("Credit Score must be greater than 0 and less than 850");
            calculate = false;
        }

        // creating customer object
        Customer customer = new Customer(debt, income, creditScore, name);

        houseCost = view.getHouseCost();
        if (houseCost <= 0) {
            view.printToUser("Cost must be greater than 0.");
            calculate = false;
        }

        downPayment = view.getDownPayment();
        if (downPayment <= 0 || downPayment >= houseCost) {
            view.printToUser("Down Payment must be greater than 0 and less than the cost of the house.");
            calculate = false;
        }

        years = view.getYears();
        if (years <= 0) {
            view.printToUser("Years must be greater than 0.");
            calculate = false;
        }

        // if all the numbers are valid, calculate the mortgage
        if (calculate) {
            if (customer.applyForLoan(downPayment, houseCost, years)) {
                // loan approved
                view.displayApproved(true);
                view.displayRate(customer.getRate());
                view.displayPayment(customer.getMonthlyPay());
            } else {
                // loan denied
                view.displayApproved(false);
                view.displayRate(0);
                view.displayPayment(0);
            }
        }
    }
}
