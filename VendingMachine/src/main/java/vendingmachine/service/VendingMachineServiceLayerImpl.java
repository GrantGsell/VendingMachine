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
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{

    //Dao and local variables
    private VendingMachineDaoImpl dao;
    private BigDecimal credit = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);
    private BigDecimal zero = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);
    
    // constructor
    public VendingMachineServiceLayerImpl() throws VendingMachinePersistenceException{
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
    public Items buyItem(String code)  throws VendingMachinePersistenceException, InsufficientFundsException, OutOfStockException{
       
       Items item = dao.getItem(code);
       
       if (item == null) return null;
       
       if (item.getStock() < 1) throw new OutOfStockException("Out of stock");
       
       BigDecimal itemCost = item.getPrice().setScale(2, RoundingMode.HALF_UP);
       credit = credit.subtract(itemCost);
       
       if(credit.compareTo(zero) != -1){
           dao.decrementItemStock(code);
           return item;
       }
       
       throw new InsufficientFundsException("Not enough funds");
    }

    @Override
    public BigDecimal getChange()  throws VendingMachinePersistenceException{
        BigDecimal tmp = new BigDecimal(credit.toString());
        credit = new BigDecimal("0.00");
        return tmp;
        
    }

    @Override
    public List<Items> purchaseable()  throws VendingMachinePersistenceException{
        
        return dao.getAllItems().stream().filter((item) -> item.getPrice().compareTo(credit) != 1).collect(Collectors.toList());
        
    }

    @Override
    public void addCredit(BigDecimal amount)  throws VendingMachinePersistenceException{
        
        credit = credit.add(amount);
        
    }

    @Override
    public BigDecimal checkCredit()  throws VendingMachinePersistenceException{
        
        return credit;
        
    }

    @Override
    public List<Items> getAllItems() throws VendingMachinePersistenceException {
        
        return dao.getAllItems();
        
    }
    
}
