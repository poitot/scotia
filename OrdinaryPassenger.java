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
public class OrdinaryPassenger{
    
    private boolean isPromotion;
    private double discount;
    private String name;
    
    public String getName()
    {
        return name;
    }
    
    public OrdinaryPassenger()
    {
        discount = 1;
        isPromotion = false;
        name = "";
    }
    
    public OrdinaryPassenger(String nameIn)
    {
        name = nameIn;
        isPromotion = false;
        discount = 1;
    }
    
    public OrdinaryPassenger(String nameIn, boolean promo)
    {
        name = nameIn;
        isPromotion = promo;
        discount = 1;
    }
    
    public boolean getPromo()
    {
        return isPromotion;
    }
    
    public double getDiscount()
    {
        if (isPromotion == true)
        {
            discount = 0.95;
        }
        return discount;
    }

}
