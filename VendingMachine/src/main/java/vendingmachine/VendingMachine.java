package vendingmachine;

import vendingmachine.controller.VendingMachineController;

/**
 *
 * @author Grant
 */
public class VendingMachine {

    public static void main(String[] args) {
        try{
            VendingMachineController runner = new VendingMachineController();
            runner.run();
        }catch(Exception e){
            System.out.println("Error");
        }
        
    }
}
