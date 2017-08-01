/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author 30179793
 */
public class Flight {
    private String flightNum;
    private HashMap seats;
    private String arrival;
    private String departure;
    private boolean bookingAvailable;
    private boolean checkingIn;
    private boolean boarding;
    private boolean closed;
    private int columns;
    private int rows;
    private String date;
    private String time;
    private boolean full;
    private int freeSeats;
    private int reservedSeats;
    private int bookedSeats;
    
    public Flight()
    {
        flightNum = "22";
        seats = new HashMap();
        arrival = "default";
        departure = "default";
        bookingAvailable = true;
        checkingIn = false;
        boarding = false;
        closed = false;
        columns = 1;
        rows = 1;
        date = "0/0/0";
        time = "0:00";
        seats = new HashMap(200);
        freeSeats = 1;
        full = false;
        freeSeats = 1;
        reservedSeats = 0;
        bookedSeats = 0;
    }
    
    public Flight(String flightNumIn, String arrivalIn, String departureIn, int columnsIn, int rowsIn, String dateIn, String timeIn)
    {
        flightNum = flightNumIn;
        arrival = arrivalIn;
        departure = departureIn;
        bookingAvailable = true;
        checkingIn = false;
        closed = false;
        boarding = false;
        columns = columnsIn;
        rows = rowsIn;
        date = dateIn;
        time = timeIn;
        seats = new HashMap(columns * rows);
        full = false;
        freeSeats = columns * rows;
        reservedSeats = 0;
        bookedSeats = 0;
    }
    
        public Flight(String flightNumIn, String arrivalIn, String departureIn, int columnsIn, int rowsIn, String dateIn, String timeIn, boolean bookingIn, boolean checking, boolean boardingIn, boolean closedIn, int freeSeatsIn, int reservedSeatsIn, int bookedSeatsIn)
    {
        flightNum = flightNumIn;
        arrival = arrivalIn;
        departure = departureIn;
        bookingAvailable = bookingIn;
        checkingIn = checking;
        closed = closedIn;
        boarding = boardingIn;
        columns = columnsIn;
        rows = rowsIn;
        date = dateIn;
        time = timeIn;
        seats = new HashMap(columns * rows);
        full = false;
        freeSeats = freeSeatsIn;
        reservedSeats = reservedSeatsIn;
        bookedSeats = bookedSeatsIn;
        generateSeats();
    }
    
    public void generateSeats()
    {
        char[] row = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
        for(int i = 0; i <= rows - 1; i++)
        {
            for(int j = 1; j <= columns; j++)
            {
                String id = Character.toString(row[i]) + " " + Integer.toString(j);
                Seat temp = new Seat(id, false, false, 10);
                seats.put(id, temp);
                
            }
            
        }
    }
    
    
    
    public int FreeSeats()
    {
        return freeSeats;
        
    }
    
    public int BookedSeats()
    {
        return bookedSeats;
    }
    
    public int ReservedSeats()
    {
        return reservedSeats;
    }
    
    public void bookingMade()
    {
        freeSeats--;
        bookedSeats++;
    }

    public void reservationMade()
    {
        freeSeats--;
        reservedSeats++;
    }

    public void bookingCanceled()
    {
        freeSeats++;
        bookedSeats--;
    }

    public void reservationCanceled()
    {
        freeSeats++;
        reservedSeats--;
    }
    
    public Seat getSeat(String rc)
    {
        String r = rc.substring(0, rc.indexOf(" "));
        String c = rc.substring(rc.indexOf(" "));
        return (Seat) seats.get(r + c);
    }
    
    public boolean getFull()
    {
        if (freeSeats == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int getFreeSeats()
    {
        return freeSeats;
    }
    
    public int getReservedSeats()
    {
        return reservedSeats;
    }
    
    public int getBookedSeats()
    {
        return bookedSeats;
    }
    
    public void makeCancellation(String c, String r)
    {
        Seat s = (Seat) seats.get(c + r);
        if(s.getBooked())
        {
            freeSeats++;
            bookedSeats--;
            s.setBooked(false);
            s.setPassenger("");
            seats.remove(s.getId());
            seats.put(s.getId(), s);
        }
        
        if(s.getReserved())
        {
            freeSeats++;
            reservedSeats--;
            s.setReserved(false);
            s.setPassenger("");
            seats.remove(s.getId());
            seats.put(s.getId(), s);
        }
    }
    
    public void loadSeats() throws SQLException
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ScotiaDb");
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT * FROM SEATS WHERE FLIGHTID = '" + flightNum + "'");
            while(res.next())
            {
                String id = res.getString("id").substring(0, 3);
                String passengerId = res.getString("passengerid");
                boolean reserved = res.getBoolean("reserved");
                boolean booked = res.getBoolean("Booked");
                int price = res.getInt("price");
                float takings = (float) res.getDouble("takings");
                Statement pass = conn.createStatement();
                int i = Integer.valueOf(passengerId);
                ResultSet resPass = pass.executeQuery("SELECT * FROM PASSENGERS WHERE ID = " + i);
                while(resPass.next())
                {


                Object passenger = null;
                switch(resPass.getString("type"))
                {
                    case("buisness"):
                        String name = resPass.getString("name");
                        String buisName = resPass.getString("buisnessName");
                        BuisnessPassenger buisP = new BuisnessPassenger(name, buisName);
                        passenger = buisP;
                        break;

                    case("ordinary"):
                        OrdinaryPassenger ordP = new OrdinaryPassenger(resPass.getString("name"), resPass.getBoolean("promotion"));
                        passenger = ordP;
                        break;

                    case("island"):
                        IslandPassenger islP = new IslandPassenger(resPass.getString("name"));
                        passenger = islP;
                        break;
                }
                Seat temp = new Seat(id, reserved, booked, price, takings, passenger);
                temp.setId(id);
                seats.remove(id);
                seats.put(temp.getId(), temp);
            }
        }

        }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
    }
    
    public void closeFlight()
    {
        closed = true;
    }
    
    public String getFlightNum()
    {
        return flightNum;
    }
    
    public HashMap getSeats()
    {
        return seats;
    }
    
    public String getArrival()
    {
        return arrival;
    }
    
    public String getDeparture()
    {
        return departure;
    }
    
    public boolean getBookingAvailable()
    {
        return bookingAvailable;
    }
    
    public boolean getCheckingIn()
    {
        return checkingIn;
    }
    
    public boolean getBoarding()
    {
        return boarding;
    }
    
    public boolean getClosed()
    {
        return closed;
    }
    
    public int getColumns()
    {
        return columns;
    }
    
    public int getRows()
    {
        return rows;
    }
    
    public String getDate()
    {
        return date;
    }
    
    public String getTime()
    {
        return time;
    }
    
    public void setFlightNum(String n)
    {
        flightNum = n;
    }
    
    public void setSeats(HashMap seatsIn)
    {
        seats = seatsIn;
    }
    
    public void setArrival(String arrivalIn)
    {
        arrival = arrivalIn;
    }
    
    public void setDeparture(String departureIn)
    {
        departure = departureIn;
    }
    
    public void setBookingAvailable(boolean b)
    {
        bookingAvailable = b;
    }
    
    public void setCheckingIn(boolean b)
    {
        checkingIn = b;
    }
    
    public void setBoarding(boolean b)
    {
        boarding = b;
    }
    
    public void setclosed(boolean b)
    {
        closed = b;
    }
    
    public void setColumns(int i)
    {
        columns = i;
    }
    
    public void setRows(int i)
    {
        rows = i;
    }
    
    public void setDate(String dateIn)
    {
        date = dateIn;
    }
    
    public void setTime(String timeIn)
    {
        time = timeIn;
    }
    
    public void reduceFreeSeats()
    {
        freeSeats--;
    }
    
    public void IncreaseFreeSeat()
    {
        freeSeats++;
    }
    
    public void reduceBookedSeats()
    {
        bookedSeats--;
    }
    
    public void increaseBookedSeats()
    {
        bookedSeats++;
    }
    
    public void reduceReservedSeats()
    {
        reservedSeats--;
    }
    
    public void increaseReducedSeats()
    {
        reservedSeats++;
    }
}
