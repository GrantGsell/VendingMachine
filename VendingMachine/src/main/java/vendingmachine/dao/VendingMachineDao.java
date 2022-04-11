package vendingmachine.dao;

import java.util.List;
import vendingmachine.dto.Items;

/**
 *
 * @author Grant
 */
public interface VendingMachineDao {
    
    /**
     * Increments the stock of the selected item, or adds the selected item, to
     * the database. Returns null if the item associated with this selection 
     * code matches, otherwise returns the item that is being replaced.
     * 
     * @param selectionCode, the vending machine location for the given item.
     * @param item, the item whose stock will increase.
     * @return null if the associated item matches the one provided, otherwise
     * the item that is being replaced.
     */
    Items addItem(String selectionCode, Items item);
    
    
    /**
     * Decrements the stock of the selected item. Returns the item being 
     * decremented, otherwise returns null if there is no item matching the 
     * selection code.
     * 
     * @param selectionCode, the vending machine location for the given item.
     * @param item, the item whose stock will decrease.
     * @return 
     */
    Items removeItem(String selectionCode, Items item);
    
    
    /**
     * Gets an item associated with the given selection code.
     * 
     * @param selectionCode, the vending machine location for the given item.
     * @return an Items object that is associated with selection code, otherwise
     * null.
     */
    Items getItem(String selectionCode);
    
    
    /**
     * Obtains and returns a list of all items within the vending machine.
     * 
     * @return a list of all items in the vending machine.
     */
    List<Items> getAllItems();
}
