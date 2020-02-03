// Name: Ben Joye
// Date: 11.13.18

package cpsc2150.mortgages;

public class MortgageApp {

    public static void main(String [] args) {

        MortgageView view = new MortgageView();
        MortgageController controller = new MortgageController(view);
        controller.run();
    }
}
