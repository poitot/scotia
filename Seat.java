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
public class Seat {
    String id;
    Object passenger;
    String type;
    boolean reserved;
    boolean booked;
    int seatPrice;
    float seatTakings;
    
    public Seat()
    {
        id = "Z44";
        reserved = false;
        booked = false;
        seatPrice = 10;
    }
    
    public Seat(String idIn, boolean reservedIn, boolean bookedIn, int priceIn)
    {
        id = idIn;
        booked = bookedIn;
        reserved = reservedIn;
        seatPrice = priceIn;
    }
    
    public Seat(String idIn, boolean reservedIn, boolean bookedIn, int priceIn, float takingsIn, Object pass)
    {
        id = idIn;
        booked = bookedIn;
        reserved = reservedIn;
        seatPrice = priceIn;
        seatTakings = takingsIn;
        passenger = pass;
    }
    
    public void bookSeat(Object p)
    {
        booked = true;
        passenger = p;
        if (p instanceof BuisnessPassenger)
        {
            BuisnessPassenger bp = (BuisnessPassenger) p;
            seatTakings = (float) (seatPrice * bp.getDiscount());
        }
        
        if (p instanceof OrdinaryPassenger)
        {
            OrdinaryPassenger bp = (OrdinaryPassenger) p;
            seatTakings = (float) (seatPrice * bp.getDiscount());
        }
        
        if (p instanceof IslandPassenger)
        {
            IslandPassenger bp = (IslandPassenger) p;
            seatTakings = (float) (seatPrice * bp.getDiscount());
        }
    }
    
    public Seat reserveSeat(Seat s)
    {
        reserved = true;
        passenger = s.getPassenger();
        if (passenger instanceof BuisnessPassenger)
        {
            BuisnessPassenger bp = (BuisnessPassenger) passenger;
            seatTakings = (float) (seatPrice * bp.getDiscount());
        }
        
        if (passenger instanceof OrdinaryPassenger)
        {
            OrdinaryPassenger bp = (OrdinaryPassenger) passenger;
            seatTakings = (float) (seatPrice * bp.getDiscount());
        }
        
        if (passenger instanceof IslandPassenger)
        {
            IslandPassenger bp = (IslandPassenger) passenger;
            seatTakings = (float) (seatPrice * bp.getDiscount());
        }
        s.setPassenger(passenger);
        return s;
    }
    
    public String getId()
    {
        return id;
    }
    
    public String getName()
    {
        if (passenger instanceof BuisnessPassenger)
        {
            BuisnessPassenger p = (BuisnessPassenger) passenger;
            return p.getName();
        }
        
        else if (passenger instanceof OrdinaryPassenger)
        {
            OrdinaryPassenger p = (OrdinaryPassenger) passenger;
            return p.getName();
        }
        
        else if (passenger instanceof IslandPassenger)
        {
            IslandPassenger p = (IslandPassenger) passenger;
            return p.getName();
        }

        return "a";
    }
    
    public boolean getReserved()
    {
        return reserved;
    }
    
    public boolean getBooked()
    {
        return booked;
    }
    
    public Object getPassenger()
    {
        if (passenger instanceof BuisnessPassenger)
        {
            BuisnessPassenger p = (BuisnessPassenger) passenger;
            return p;
        }
        
        else if (passenger instanceof OrdinaryPassenger)
        {
            OrdinaryPassenger p = (OrdinaryPassenger) passenger;
            return p;
        }
        
        else if (passenger instanceof IslandPassenger)
        {
            IslandPassenger p = (IslandPassenger) passenger;
            return p;
        }

        return "a";
    }
    
    public int getPrice()
    {
        return seatPrice;
    }
    
    public float getTakings()
    {
        return seatTakings;
    }
    public void setId(String idIn)
    {
        id = idIn;
    }
    
    public void setReserved(boolean reservedIn)
    {
        reserved = reservedIn;
    }
    
    public void setBooked(boolean bookedIn)
    {
        booked = bookedIn;
    }
    
    public void setPassenger(Object temp)
    {
        passenger = temp;
    }
    
    public void setPrice(int priceIn)
    {
        seatPrice = priceIn;
    }
    
    public void setTakings(float takingsIn)
    {
        seatTakings = takingsIn;
    }
}
