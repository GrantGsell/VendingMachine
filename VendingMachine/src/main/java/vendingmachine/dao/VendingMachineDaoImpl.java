package vendingmachine.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import vendingmachine.dto.Items;

/**
 *
 * @author Grant
 */
public class VendingMachineDaoImpl implements VendingMachineDao{
    // Vending Machine db variable
    private final String DATA_BASE;
    
    // Delimeter Variable
    private final String DELIMETER = ":::";
    
    // Map to hold databse data
    Map<String, Items> itemsMap;
    
    // Vending machine constructors
    public VendingMachineDaoImpl(){
        // Instantiate itemsMap
        itemsMap = new HashMap<String, Items>();
        
        // Set text file to read, write to
        DATA_BASE = "vendingMachineDb.txt";
    }
    
    public VendingMachineDaoImpl(String filePath){
        // Instantiate itemsMap
        itemsMap = new HashMap<String, Items>();
        
        // Set text file to read, write to
        DATA_BASE = filePath;
    }

    
    /**
     * 
     * @param selectionCode
     * @param item
     * @return 
     */
    @Override
    public Items addItem(String selectionCode, Items item) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    /**
     * 
     * @param selectionCode
     * @param item
     * @return 
     */
    @Override
    public Items removeItem(String selectionCode, Items item) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    /**
     * 
     * @param selectionCode
     * @return 
     */
    @Override
    public Items getItem(String selectionCode) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    /**
     * 
     * @return 
     */
    @Override
    public List<Items> getAllItems() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    /**
     * 
     * @param selectionCode
     * @return 
     */
    @Override
    public Items incrementItemStock(String selectionCode) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    /**
     * 
     * @param selectionCode
     * @return 
     */
    @Override
    public Items decrementItemStock(String selectionCode) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    /**
     * 
     * 
     * @param item
     * @return 
     */
    private String marshallData(Items item){
       // Create string builder to hold item data 
       StringBuilder itemAsText = new StringBuilder();
       
       // Add item name and delimeter
       itemAsText.append(item.getName()).append(DELIMETER);
       
       // Add item price and delimeter
       itemAsText.append(item.getPrice()).append(DELIMETER);
       
       // Add item stock and delimeter
       itemAsText.append(item.getStock()).append(DELIMETER);
       
       // Add item selectionCode
       itemAsText.append(item.getSelectionCode()).append(DELIMETER);
       
       // Convert stringbuilder to string and return
       return itemAsText.toString();
    }
    
    
    /**
     * 
     * @param itemAsText
     * @return 
     */
    private Items unmarshallData(String itemAsText){
        // Create variable for number of fields
        int numFields = 4;
        
        // Create string array to hold string split
        String[] itemFields;
        
        // Create items object to hold data
        Items item = new Items();
        
        // Split the item string into its field components based on delimeter
        itemFields = itemAsText.split(DELIMETER, numFields);
        
        // Assign item name, field 0
        item.setName(itemFields[0]);
        
        // Convert item price, field 1, to big decimal, assign to price
        BigDecimal price = new BigDecimal(itemFields[1]);
        item.setPrice(price);
        
        // Convert item stock, field 2 to int, assign to stock
        int stock = Integer.parseInt(itemFields[2]);
        item.setStock(stock);
        
        // Assign item selectionCode, field 3
        item.setSelectionCode(itemFields[3]);
        
        // Return populated item object
        return item;        
    }
    
    
    /**
     * 
     */
    private void writeDataBase(){
        // Declare PrintWriter object
        PrintWriter out;
        
        // Declare string variable for marshalled data
        String itemAsText;
        
        // Initialize List to hold all items
        List<Items> itemsList = this.getAllItems();
        
        // Try to open the file DATA_BASE file
        try{
            out = new PrintWriter(new FileWriter(DATA_BASE));
        }catch (Exception e){
            // Throw custom exception
            return;
        }
                
        // Loop through all of the items
        for(Items item : itemsList){
            // Marshal object data into a string
            itemAsText = marshallData(item);
            
            // Write the object data as text to the file
            out.println(itemAsText);
            
            // Force the PrintWriter to write buffered data to the file
            out.flush();
        }
        // Close print writer to prevent memory leak
        out.close();
    }
    
    
    /**
     * 
     */
    private void readDataBase(){
        // Declare scanner object
        Scanner scan;
        
        // Create string variable to hold each string line
        String currentItemAsText;
        
        // Create items object to hold unmarshalled data
        Items currentItem;
        
        // Initialize scanner object for reading the database file
        try{
            scan = new Scanner(new BufferedReader(new FileReader(DATA_BASE)));
        }catch(Exception e){
            // Throw custom exception
            return;
        }
        
        // Loop through file until there is no more data to read
        while(scan.hasNextLine()){
            // Get the next line of text in the file
            currentItemAsText = scan.nextLine();
            
            // Unmarshall text data into an object
            currentItem = this.unmarshallData(currentItemAsText);
            
            // Put the object into the hashmap
            itemsMap.put(currentItem.getSelectionCode(), currentItem);
        }
        // Close the scanner to prevent memory leaks
        scan.close();        
    }
}
