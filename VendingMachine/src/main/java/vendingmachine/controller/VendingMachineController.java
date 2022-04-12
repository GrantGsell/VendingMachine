/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vendingmachine.controller;

import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachineDaoImpl;
import vendingmachine.dao.VendingMachinePersistenceException;
import vendingmachine.ui.VendingMachineView;

/**
 *
 * @author Grant
 */
public class VendingMachineController {

    // Model and VIew class objects
    private VendingMachineView view = new VendingMachineView();
    private VendingMachineDao dao = new VendingMachineDaoImpl();

    // Function that controls program flow
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing) {
                view.displayInventoryBanner();
                dao.listInventory(); //Begins with Inventor Banner and list of inventory every time

                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        addFunds();
                        break;
                    case 2:
                        buyItem();
                        break;
                    case 3:
                        browseAffordableItems();
                        break;
                    case 4:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
        } catch (VendingMachinePersistenceException e) {
            System.out.println(e.getMessage());
        }
        exitMessage();
    }

    private void addFunds() throws VendingMachinePersistenceException {
        view.displayAddFundsBanner();
        Double fundsAdditionAmount = view.getFundsAdditionAmount();
        service.addFunds(fundsAdditionAmount);
        view.displayFundsAddedBanner();
    }

    private void buyItem() throws VendingMachinePersistenceException {
        view.displayBuyItemBanner();
        String itemCode = view.getItemCode();
        service.buyItem(itemCode);
        view.displayEnjoyBanner();
    }

    private void browseAffordableItems() throws VendingMachinePersistenceException {
        view.displayAffordableItemsBanner();
        dao.affordableItems;
    }

    // Let's user know they've exited
    private void exitMessage() {
        view.displayExitBanner();
    }

    // Prompts user for a known command
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    // Requests view to display the menu
    private int getMenuSelection() {
        return view.getMenuSelection();
    }
    
}
