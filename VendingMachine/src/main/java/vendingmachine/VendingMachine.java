package vendingmachine;

import vendingmachine.controller.VendingMachineController;

/**
 *
 * @author Grant
 */
public class VendingMachine {

    public static void main(String[] args) {
        VendingMachineController runner = new VendingMachineController();
        runner.run();
    }
}
