package vendingmachine.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import vendingmachine.dto.Items;

/**
 *
 * @author Grant Gsell
 */
public class VendingMachineDaoImpl implements VendingMachineDao{
    // Vending Machine db variable
    private final String DATA_BASE;
    
    // Delimeter Variable
    private final String DELIMETER = ":::";
    
    // Map to hold database data
    Map<String, Items> itemsMap = new HashMap<String, Items>();
    
    /**
     * Vending machine constructors
     */
    public VendingMachineDaoImpl() throws VendingMachinePersistenceException{
        // Instantiate itemsMap
        itemsMap = new HashMap<String, Items>();
        
        // Set text file to read, write to
        DATA_BASE = "vendingMachineDb.txt";
        
        // Load in the initalized data
        try{
        this.readDataBase();
        }catch (Exception e){
            throw new VendingMachinePersistenceException("Inital database read error", e);
        }
    }
    
    public VendingMachineDaoImpl(String filePath){
        // Instantiate itemsMap
        //itemsMap = new HashMap<String, Items>();
        
        // Set text file to read, write to
        DATA_BASE = filePath;
    }

    
    /**
     * Adds an item to the vending machine. Returns null if the item associated 
     * with this selection code matches, otherwise returns the item that is 
     * being replaced.
     * 
     * @param selectionCode, the vending machine location for the given item.
     * @param item, the item that will be added to the vending machine.
     * @return null if the associated item matches the one provided, otherwise
     * the item that is being replaced.
     */
    @Override
    public Items addItem(String selectionCode, Items item) throws VendingMachinePersistenceException {
        // Read in the database first to ensure map is up to date
        readDataBase();
        
        // Add the new item to the map, obtain map return value
        Items returnedItem = itemsMap.put(selectionCode, item);
        
        // Write the updated map to the database
        writeDataBase();
        
        // Return new item
        return returnedItem;
    }

    
    /**
     * Removes the selected item from the vending machine. Returns the item 
     * being removed otherwise returns null if there is no item matching  the
     * selection code.
     * 
     * @param selectionCode, the vending machine location for the given item.
     * @param item, the item that will be removed.
     * @return 
     */
    @Override
    public Items removeItem(String selectionCode, Items item) throws VendingMachinePersistenceException {
        // Read in the database first to ensure the map is up to date
        this.readDataBase();
        
        // Remove the item from the map
        Items returnedItem = itemsMap.remove(selectionCode);
        
        // Write the updated map to the database
        this.writeDataBase();
        
        // Return the removed item value
        return returnedItem;
    }

    
    /**
     * Gets an item associated with the given selection code.
     * 
     * @param selectionCode, the vending machine location for the given item.
     * @return an Items object that is associated with selection code, otherwise
     * null.
     */
    @Override
    public Items getItem(String selectionCode) throws VendingMachinePersistenceException {
        // Read in the database first to ensure the map is up to date
        this.readDataBase();
        
        // Return the specified item 
        return itemsMap.get(selectionCode);
    }

    
    /**
     * Obtains and returns a list of all items within the vending machine.
     * 
     * @return a list of all items in the vending machine.
     */
    @Override
    public List<Items> getAllItems() throws VendingMachinePersistenceException {
        // Read in the database first to ensure the map is up to date
        //this.readDataBase();
        
        // Return all values of the map, which corresponds to all items objects
        return new ArrayList(itemsMap.values());
    }

    
    /**
     * Increments the stock field of the item associated with selection code. 
     * 
     * @param selectionCode, the code associated with the item whose stock is 
     * going to be incremented.
     * @return the item object whose stock has been incremented.
     */
    @Override
    public Items incrementItemStock(String selectionCode) throws VendingMachinePersistenceException {
        // Read in the database first to ensure the map is up to date
        this.readDataBase();
        
        // Exctract the object
        Items item = itemsMap.get(selectionCode);
        
        // Increment the specified item stock
        item.setStock(item.getStock() + 1);
        
        // Update the HashMap and database
        addItem(item.getSelectionCode(), item);
        
        // Return the specified, updated item
        return item;
    }

    
    /**
     * Decrements the stock field of the item associated with the provided 
     * selection code. Throws error if the stock is already zero.
     * 
     * @param selectionCode, the code associated with the item whose stock is
     * going to be decremented.
     * @return the item object whose stock has been decremented.
     */
    @Override
    public Items decrementItemStock(String selectionCode) throws VendingMachinePersistenceException {
        // Read in the database first to ensure the map is up to date      
        this.readDataBase(); 
        
        // Exctract the object
        Items item = itemsMap.get(selectionCode);
                
        // Decrement the specified item stock
        item.setStock(item.getStock() - 1);
        
        // Update the HashMap and database
        addItem(item.getSelectionCode(), item);
        
        // Return the specified, updated item
        return item;
    }
    
    
    /**
     * Marshalls data from an Items object into a string for file writing.
     * 
     * @param item, the item whose data is going to be marhsalled.
     * @return a string denoting the marshalled data.
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
       itemAsText.append(item.getSelectionCode());
       
       // Convert stringbuilder to string and return
       return itemAsText.toString();
    }
    
    
    /**
     * Unmarshalls data from string into an Items object.
     * 
     * @param itemAsText the object data in string form split by a delimeter.
     * @return an Items object containing all of the appropriate field data.
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
     * Write data from itemsMap into the database text file.
     */
    private void writeDataBase() throws VendingMachinePersistenceException{
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
            throw new VendingMachinePersistenceException("Error writing to file", e);
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
     *  Reads data from the database text file and stores it into the itemsMap.
     */
    private void readDataBase() throws VendingMachinePersistenceException{
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
            throw new VendingMachinePersistenceException("Error reading from file!", e);
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
