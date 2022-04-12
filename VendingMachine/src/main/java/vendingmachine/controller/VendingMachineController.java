/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vendingmachine.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachineDaoImpl;
import vendingmachine.dao.VendingMachinePersistenceException;
import vendingmachine.dto.Items;
import vendingmachine.service.VendingMachineServiceLayer;
import vendingmachine.ui.VendingMachineView;

/**
 *
 * @author Grant
 */
public class VendingMachineController {

    // Model and VIew class objects
    private VendingMachineView view = new VendingMachineView();
    private VendingMachineDao dao = new VendingMachineDaoImpl();
    private VendingMachineServiceLayer service = new VendingMachineServiceLayer();

    // Function that controls program flow
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing) {

                listInventory(); //Begins with Inventor Banner and list of inventory every time

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
                        getChange();
                        break;
                    case 5:
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

    private void listInventory() throws VendingMachinePersistenceException {
        view.displayInventoryBanner();
        //TODO
    }

    private void addFunds() throws VendingMachinePersistenceException {
        view.displayAddFundsBanner();
        String fundsAdditionAmount = view.getFundsAdditionAmount();
        BigDecimal fundsBigDecimal = new BigDecimal(fundsAdditionAmount).setScale(2, RoundingMode.HALF_UP);
        service.addCredit(fundsBigDecimal);
        view.displayFundsAddedBanner();
    }

    private void buyItem() throws VendingMachinePersistenceException {
        view.displayBuyItemBanner();
        String itemCode = view.getItemCode();
        Items item = dao.getItem(itemCode);
        service.buyItem(item);
        view.displayEnjoyBanner();
    }

    private void browseAffordableItems() throws VendingMachinePersistenceException {
        view.displayAffordableItemsBanner();
        service.purchaseable();
    }

    private void getChange() throws VendingMachinePersistenceException {
        view.displayGetChangeBanner();
        service.getChange();
        view.displayChangeSuccessBanner();
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
