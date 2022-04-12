/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package vendingmachine.service;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vendingmachine.dao.VendingMachineDaoImpl;
import vendingmachine.dto.Items;

/**
 *
 * @author Juan B
 */
public class VendingMachineServiceLayerImplTest {
    
    private VendingMachineDaoImpl dao;
     private VendingMachineServiceLayer service;// = new VendingMachineServiceLayerImpl();private 
    
    public VendingMachineServiceLayerImplTest() {
    }
    
    
    @BeforeEach
    public void setUp() {
        
        try{
            dao = new VendingMachineDaoImpl();
            service = new VendingMachineServiceLayerImpl();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        
    }
    
    @AfterEach
    public void tearDown() {
    }

 
    @Test
    public void testBuyItem(){
        try {
            service.addCredit(new BigDecimal("5.00"));
            Items item = dao.getItem("A1");
            BigDecimal cost = item.getPrice();
            //BigDecimal
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        
    }
    
}
