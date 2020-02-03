// Name: Ben Joye
// Date: 11.13.18

package cpsc2150.mortgages;

public class Customer {
    /**
     * @invariant
     *  creditScore >= 0 and creditScore <= 850
     *  income > 0
     *  monthlyDebtPayments > 0
     */
    private String name;

    private double monthlyDebtPayments;
    private double income;
    private int creditScore;
    private Mortgage loan;

    /**
     * Class constructor
     * @param debt amount of debt the customer has
     * @param inc amount of income the customer has
     * @param score the customer's credit score
     * @param n the customer's name
     * @pre
     *  0 <= score <= 850
     * @post
     *  the object has all the values passed in the parameters
     */
    Customer( double debt, double inc, int score, String n)
    {
        income = inc;
        creditScore = score;
        name = n;
        monthlyDebtPayments = debt;
        /*
        Set the monthly debt payments, income, credit score and name
        Credit scores range from 0 - 850
         */


    }

    /**
     * Saves the loan details as the customers current loan and returns if it was approved
     * @param downPayment value of the down payment
     * @param houseCost value of the house
     * @param years number of years to pay it off
     * @return true if the loan was approved, false if it wasn't
     * @pre
     *  downPayment > 0, houseCost > 0, years > 0
     * @post
     *  loan has these new values
     */
    public boolean applyForLoan(double downPayment, double houseCost, int years)
    {
        //Take the parameters and apply for a loan. Save the loan details as the customers current loan
        // Return whether or not the loan was approved
        loan = new Mortgage(houseCost,downPayment,years,this);
        return loan.loanApproved();
    }

    /**
     * Gets the interest rate on the the current loan
     * @return the interest rate on the current loan
     * @post
     *  the interest rate is returned
     */
    public double getRate()
    {
        //return the interest rate on the current loan
        return loan.getRate();
    }

    /**
     * Gets the monthly payment on the current loan
     * @pre: the loan has been initialized
     * @return the monthly payment on the current loan
     * @post
     *  the monthly payment on the current loan is returned
     */
    public double getMonthlyPay()
    {
        //return the monthly payment on the current loan
        return loan.getPayment();
    }

    /**
     * Gets the customer's monthly debt payments
     * @pre: monthlydebtpayments has been initialized
     * @return the customer's monthly debt payments
     * @post
     *  the customer's monthly debt payments are returned
     */
    public double getMonthlyDebtPayments()
    {
        //return the customers monthly debt payments
        return monthlyDebtPayments;
    }

    /**
     * Gets the customer's income
     * @pre: income has been initialized
     * @return the customer's income
     * @post
     *  the customer's income is returned
     */
    public double getIncome()
    {
        //return the customers income
        return income;
    }

    /**
     * Gets the customer's credit score
     * @pre: creditscore has been initialized
     * @return the customer's credit score
     * @post
     *  the customer's credit score is returned
     */
    public int getCreditScore()
    {
        //return the customers credit score
        return creditScore;
    }

    /**
     * Combines all customer information into a string
     * @pre: the customer has been initialized
     * @post: the customer's information will be returned in a string
     * @return a string with the name, income, creditScore, and monthlyDebtPayments
     */
    @Override
    public String toString() {
        String str = "";
        str += "Name: " + name + "\n";
        str += "Income: $" + income + "\n";
        str += "Credit Score: " + creditScore + "\n";
        str += "Monthly Debt: $" + monthlyDebtPayments + "\n";
        str += "Mortgage info: \n";
        if(loan != null) {
            str += loan.toString();
        }
        return str;
    }
}