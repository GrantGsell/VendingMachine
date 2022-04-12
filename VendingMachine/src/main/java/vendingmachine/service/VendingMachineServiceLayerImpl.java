/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vendingmachine.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.dao.VendingMachineDaoImpl;
import vendingmachine.dao.VendingMachinePersistenceException;
import vendingmachine.dto.Items;

/**
 *
 * @author Juan B
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    enum Coins {
        QUARTER(new BigDecimal("0.25")),
        DIME(new BigDecimal("0.10")),
        NICKLE(new BigDecimal("0.5")),
        PENNY(new BigDecimal("0.01"));

        private BigDecimal value;

        Coins(BigDecimal amount) {
            value = amount;
        }

        BigDecimal getValue() {
            return value;
        }

    }

    //Dao and local variables
    private VendingMachineDaoImpl dao;
    private BigDecimal credit = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);
    private BigDecimal zero = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);

    // constructor
    public VendingMachineServiceLayerImpl() throws VendingMachinePersistenceException {
        dao = new VendingMachineDaoImpl();
    }

    /*
        This method takes in a code and will check if it is a valid code.
        If the code is invalid a null value is returned. If the cod esi valid
        then we will see if the item is purchaseable. If it is not then an exception will be
        thrown. If it is purchaseable it will decrement the available stock and update the 
        customer's credit. 
     */
    @Override
    public Items buyItem(String code) throws VendingMachinePersistenceException, InsufficientFundsException, OutOfStockException {

        Items item = dao.getItem(code);

        if (item == null) {
            return null;
        }

        if (item.getStock() < 1) {
            throw new OutOfStockException("Out of stock");
        }

        BigDecimal itemCost = item.getPrice().setScale(2, RoundingMode.HALF_UP);
        credit = credit.subtract(itemCost);

        if (credit.compareTo(zero) != -1) {
            dao.decrementItemStock(code);
            return item;
        }

        throw new InsufficientFundsException("Not enough funds");
    }

    /*
        This method will return the amount the user has available. 
     */
    @Override
    public BigDecimal getChange() throws VendingMachinePersistenceException {
        BigDecimal tmp = new BigDecimal(credit.toString());
        //credit = new BigDecimal("0.00");

        BigDecimal quarters = credit.divide(Coins.QUARTER.getValue());
        credit = credit.divideAndRemainder(Coins.QUARTER.getValue())[1];

        BigDecimal dimes = credit.divide(Coins.DIME.getValue());
        credit = credit.divideAndRemainder(Coins.DIME.getValue())[1];

        BigDecimal nickles = credit.divide(Coins.NICKLE.getValue());
        credit = credit.divideAndRemainder(Coins.NICKLE.getValue())[1];

        BigDecimal penny = credit.divide(Coins.PENNY.getValue());
        credit = credit.divideAndRemainder(Coins.PENNY.getValue())[1];

        System.out.println("Quarters: " + quarters + "\nDimes: " + dimes +"\nNickles: " + nickles+"\nPennies: " + penny);
        
        return tmp;

    }

    /*
        This will display all the items the user can purchase with their current
        balance.
     */
    @Override
    public List<Items> purchaseable() throws VendingMachinePersistenceException {

        return dao.getAllItems().stream().filter((item) -> item.getPrice().compareTo(credit) != 1).collect(Collectors.toList());

    }

    /*
        this method allows the user to add funds to the vending machine.
        negative values are not accepted, nothing happens if a negative value is passed in.
     */
    @Override
    public void addCredit(BigDecimal amount) throws VendingMachinePersistenceException {

        if (amount.compareTo(zero) == 1) {
            credit = credit.add(amount);
        }

    }

    /*
        this method returns the ammount of credit the user has
     */
    @Override
    public BigDecimal checkCredit() throws VendingMachinePersistenceException {

        return credit;

    }

    /*
        this will return the available items in the vending machine.
     */
    @Override
    public List<Items> getAllItems() throws VendingMachinePersistenceException {

        return dao.getAllItems();

    }

}
