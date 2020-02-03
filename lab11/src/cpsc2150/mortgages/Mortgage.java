// Ben Joye
// CPSC 2150
// 11/28/18

package cpsc2150.mortgages;

import java.lang.Math;

public class Mortgage {
    /**
     * @invariant
     *  houseCost > 0
     *  downPayment > 0
     *  0 <= percentDown <= 100
     *  years = 15 || y = 20 || y = 25 ||  y = 30
     *  interestRate > 0 and interestRate < 100
     *  payment > 0
     */


    private double houseCost;
    private double downPayment;
    private double percentDown;
    private int years;
    private double interestRate;
    private double payment;
    private Customer customer;

    /**
     * Sets the house cost, down payment, years and customer. Calculates the percent down, rate, and payment
     * @param cost the cost of the house
     * @param dp the down payment of the house
     * @param y the years to pay it off
     * @param cust the customer
     * @pre
     *  cost > 0, dp > 0, y = 15 || y = 20 || y = 25 ||  y = 30
     * @post
     *  [percentDown will be calculated and stored], [interestRate will be calculated and stored], [payment will be calculated and stored]
     */
    Mortgage(double cost, double dp, int y, Customer cust)
    {
        houseCost = cost;
        downPayment = dp;
        years = y;
        customer = cust;
        percentDown = (downPayment/houseCost)*100;
        calcRate();
        calcPayment();
        /*
        Sets the house cost, down payment, years and customer. Calculates the percent down, rate, and payment
         */
    }

    /**
     * Calculates the interest rate to use.
     * @pre: the constructor has been called
     * @post
     *  interestRate = 2.5
     *  interstRate += 1 iff (years == 30)
     *  interstRate += 0.5 iff (years < 30)
     *  interestRate += 10 iff (creditScore <= 500)
     *  interestRate += 5 iff (creditScore > 500 && creditScore < 600)
     *  interestRate += 1 iff (creditScore >= 600 && creditScore < 700)
     *  interestRate += 0.5 iff (creditScore >= 700 && creditScore < 750)
     *  interestRate += 0.5 iff (downPayment  < 0.2(cost))
     */
    private void calcRate()
    {
        /*
        Calculates the interest rate to use.
        The rate starts at the base rate of 2.5%
        If the loan is for 30 years, add the normal addition of 1%
        If the loan is for for less than 30 years, add the good addition of .5%
        The loan must be for 15, 20, 25 or 30 years

        Add the rate for the credit score based on the following table
        Credit rating | Credit score | add to rate
        Bad           | 0 - 500      | 10%
        Fair          | 500 - 599    | 5%
        Good          | 600 - 699    | 1%
        Great         | 700 - 749    | .5%
        Excellent     | 750 - 850    | 0%

        If the down payment is less than 20% of the price of the house, add .5%
         */

        interestRate = 0.025;

        if (years == 30) {
            interestRate += 0.01;
        } else if (years < 30) {
            interestRate += 0.005;
        }

        if (customer.getCreditScore() < 500) {
            interestRate += 0.1;
        }
        if (customer.getCreditScore() >= 500 && customer.getCreditScore() <= 599) {
            interestRate += 0.05;
        }
        if (customer.getCreditScore() >= 600 && customer.getCreditScore() <= 699) {
            interestRate += 0.01;
        }
        if (customer.getCreditScore() >= 700 && customer.getCreditScore() <= 749) {
            interestRate += 0.005;
        }

        if (downPayment < 0.2*houseCost) {
            interestRate += 0.005;
        }
    }

    /**
     * Calculates the monthly payment
     * @pre: the constructor has been called
     * @post
     *  r = interestRate / 12
     *  p = houseCost - downPayment
     *  n = 12p / years
     *  payment = (rp)/(1-(1+r)^-n)
     */
    private void calcPayment()
    {
        /*
        Calculate the monthly payment
        terms:
        r - monthly rate - the interest rate divided by 12
        p - principal balance - the cost of the house minus the down payment
        n - number of payments - the total number of monthly payments

        monthly payment is (rp)/(1-(1+r)^-n)
         */
        double r = interestRate/12;
        double p = houseCost - downPayment;
        double n = years*12;

        payment = (r*p)/(1-Math.pow((1+r),(-1*n)));

    }

    /**
     * Checks if the loan should be approved
     * @pre: the constructor has been called
     * @return false iff (interestRate >= 10 || downPayment < 0.035houseCost || (debt/12)/(income/12) > 0.4 || ) else true
     * @post
     *  [will return true or false depending if the loan was approved]
     */
    public boolean loanApproved()
    {
        /*
        If the interest rate is too high (10% or higher) the loan is denied
        If the down payment is less than 3.5% of the price of the house then the loan is denied

        If the Debt to income ratio is above 40% the loan is denied
        The debt to income ratio is the total monthly debt payments (including the mortgage payment) / monthly income

        Otherwise the loan is approved
         */

        if (interestRate >= 0.10) {
            return false;
        }
        if (downPayment < 0.035*houseCost) {
            return false;
        }
        if (((customer.getMonthlyDebtPayments()+payment)/(customer.getIncome()/12)) >= 0.4) {
            return false;
        }


        return true;
    }

    /**
     * Gets the monthly payment on the loan
     * @pre: payment has been initialized
     * @return the monthly payment on the loan
     * @post
     *  monthly payment on the the loan will be returned
     */
    public double getPayment()
    {
        //return the monthly payment on the loan
        return payment;
    }

    /**
     * Gets the interest rate on the loan
     * @pre: interestRate has been initialized
     * @return the interest rate on the loan
     * @post
     *  interest rate on the loan will be returned
     */
    public double getRate()
    {
        //return the interest rate on the loan
        return interestRate;
    }

    /**
     * Combines all customer information into a string
     * @pre: the mortgage has been initialized
     * @post: loan information will be return in a string
     * @return a string with the name, income, creditScore, and monthlyDebtPayments
     */
    @Override
    public String toString()
    {
        String str = "";
        if(loanApproved())
        {
            str += "Principal Amount: $" + (houseCost - downPayment) + "\n";
            str += "Interest Rate: " + (interestRate * 100) + "%\n";
            str += "Term: " + years + " years\n";
            str += "Monthly Payment: $" + payment + "\n";
        }
        else
        {
            str += "Loan was not approved";
        }
        return str;
    }

}
