/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vendingmachine.ui;

/**
 *
 * @author harle
 */
public class VendingMachineView {
    // Display menu selection

    private UserIO io = new UserIOConsoleImpl();

    // Display, take, and return an integer for a selection from the menu
    public int getMenuSelection() {
        io.print("Main Menu");
        io.print("1. Add funds");
        io.print("2. Buy an item");
        io.print("3. See what you can afford");
        io.print("4. EXIT");
        return io.readInt("Select one of the choices above:", 1, 4);
    }

    public void displayAddFundsBanner() {
        io.print("=== Add Funds ===");
    }

    public Double getFundsAdditionAmount() {
        return io.readDouble("Please enter payment amount.");
    }

    public void displayFundsAddedBanner() {
        io.print("=== Your funds have been added! ===");
    }

    public void displayBuyItemBanner() {
        io.print("=== Buy an Item ===");
    }

    public String getItemCode() {
        return io.readString("Please enter the code for the item to be purchased.");
    }

    public void displayAffordableItemsBanner() {
        io.print("=== Currently Affordable Items ===");
    }

    public void displayExitBanner() {
        io.print("=== Good Bye! ===");
    }

    public void displayUnknownCommandBanner() {
        io.print("=== Unknown Command ===");
    }

    public void displayEnjoyBanner() {
        io.print("=== Enjoy! ===");
    }

    public void displayInventoryBanner() {
        io.print("=== Inventory ===");
    }


}
