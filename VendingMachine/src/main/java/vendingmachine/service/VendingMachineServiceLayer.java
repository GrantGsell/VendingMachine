/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vendingmachine.service;

import java.math.BigDecimal;
import java.util.List;
import vendingmachine.dto.Items;

/**
 *
 * @author Grant
 */
public interface VendingMachineServiceLayer {
    
    public Items buyItem(Items item);
    
    public BigDecimal getChange();
    
    public List<Items> purchaseable();
    
    public void addCredit(BigDecimal amount);
    
    public BigDecimal checkCredit();
        
}
