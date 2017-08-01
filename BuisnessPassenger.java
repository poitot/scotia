/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Label;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author 30179793
 */
public class BuisnessPassenger {
    private String buisnessName;
    private double discount;
    private String name;
    
    public String getName()
    {
        return name;
    }
    
    public BuisnessPassenger()
    {
        name = "";
        buisnessName = "buisness";
        discount = 25;
    }
    
    public BuisnessPassenger(String nameIn, String buisName)
    {
        name = nameIn;
        buisnessName = buisName;
        discount = 0.75;
    }
    
 public double getDiscount()
    {
        return discount;
    }
    
    
    public String getBuisnessName()
    {
        return buisnessName;
    }
}
