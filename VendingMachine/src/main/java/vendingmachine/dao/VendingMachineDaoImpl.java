package vendingmachine.dao;

import java.util.List;
import vendingmachine.dto.Items;

/**
 *
 * @author Grant
 */
public class VendingMachineDaoImpl implements VendingMachineDao{
    // Vending Machine db variable
    private final String DATA_BASE;
    
    // Vending machine constructors
    public VendingMachineDaoImpl(){
        DATA_BASE = "vendingMachineDb.txt";
    }
    
    public VendingMachineDaoImpl(String filePath){
        DATA_BASE = filePath;
    }

    @Override
    public Items addItem(String selectionCode, Items item) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Items removeItem(String selectionCode, Items item) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Items getItem(String selectionCode) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Items> getAllItems() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
