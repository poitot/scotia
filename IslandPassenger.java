/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author 30179793
 */
public class IslandPassenger{
    private double discount;
    private String name;
    
     String getName()
    {
        return name;
    }
    public IslandPassenger()
    {
        name = "";
        discount = 0.9;
    }
    
    public IslandPassenger(String nameIn)
    {
        name = nameIn;
        discount = 0.9;
    }
    
 public double getDiscount()
    {
        return discount;
    }
    

}
