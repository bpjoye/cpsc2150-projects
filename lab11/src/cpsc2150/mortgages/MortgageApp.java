// Ben Joye
// CPSC 2150
// 11/28/18

package cpsc2150.mortgages;

public class MortgageApp {

    public static void main(String [] args)
    {
        IMortgageView view = new MortgageView();
        MortgageController controller = new MortgageController(view);
        view.setController(controller);
    }
}
