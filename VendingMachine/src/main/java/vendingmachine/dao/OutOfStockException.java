/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vendingmachine.dao;

/**
 *
 * @author Juan B
 */
public class OutOfStockException extends Exception{
    
    public  OutOfStockException(String msg){
        super(msg);
    }
    
}
