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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 30179793
 */
public class Airline {
    HashMap flights;
    
    public Airline()
    {
        flights = new HashMap();
    }
    
    public Airline(HashMap flightsIn)
    {
        flights = flightsIn;
    }
    
    public HashMap getFlights()
    {
        return flights;
    }
    
    public void setFlights(HashMap flightsIn)
    {
        flights = flightsIn;
    }
    
    
    public Flight getFlight(String i)
    {
        return (Flight) flights.get(i);
    }
    
    public void saveDb()
    {
            try {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ScotiaDb");
            Statement stat = conn.createStatement();
            stat.executeUpdate("DELETE FROM SEATS WHERE 1 = 1");
            stat.executeUpdate("DELETE FROM FLIGHTS WHERE 1 = 1");
            stat.executeUpdate("DELETE FROM PASSENGERS WHERE 1 = 1");
            Flight flight;
            int passId = 0;
        
            for (int i = 0; i < flights.size(); i++)
            {
                flight = (Flight) flights.get(Integer.toString(i));
                stat.execute("INSERT INTO FLIGHTS " + "VALUES ('" + flight.getFlightNum() + "', '" + flight.getArrival() + "', '" + flight.getDeparture() + "', '" + Boolean.toString(flight.getBookingAvailable()) + "', '" + Boolean.toString(flight.getCheckingIn()) + "', '" + Boolean.toString(flight.getBoarding()) + "', '" + Boolean.toString(flight.getClosed()) + "', " + flight.getColumns() + ", " + Integer.valueOf(flight.getRows()) + ", '" + flight.getDate() + "', '" + flight.getTime() + "', '" + Boolean.toString(flight.getFull()) + "', "+ Integer.valueOf(flight.getFreeSeats()) + ", " + Integer.valueOf(flight.getReservedSeats()) + ", " + Integer.valueOf(flight.getBookedSeats()) + ")");
                Seat seat = new Seat();
                HashMap seats = flight.getSeats();
                
                char[] row = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
                for(int r = 0; r <= flight.getRows() - 1; r++)
                    {
                        for(int c = 1; c <= flight.getColumns(); c++)
                        {
                            seat = (Seat) seats.get(row[r] + " " + Integer.toString(c));
                            String id = null;
                            String type = null;
                            String buisName = null;
                            boolean promo = false;
                            String name = null;
                            
                            if (seat.getPassenger() instanceof BuisnessPassenger)
                            {
                                BuisnessPassenger p = (BuisnessPassenger) seat.getPassenger();
                                type = "buisness";
                                buisName = p.getBuisnessName();
                                name = p.getName();
                            }

                            else if (seat.getPassenger() instanceof OrdinaryPassenger)
                            {
                                OrdinaryPassenger p = (OrdinaryPassenger) seat.getPassenger();
                                type = "ordinary";
                                promo = p.getPromo();
                                name = p.getName();
                            }

                            else if (seat.getPassenger() instanceof IslandPassenger)
                            {
                                IslandPassenger p = (IslandPassenger) seat.getPassenger();
                                type = "island";
                                name = p.getName();
                            }
                            
                            else
                            {
                                OrdinaryPassenger p = new OrdinaryPassenger();
                            }
                            

                            
                            if (name != null)
                            {
                                stat.execute("INSERT INTO PASSENGERS VALUES(" + passId + ", '" + type + "', '" + buisName + "', '" + promo + "', '" + name + "')");
                                stat.execute("INSERT INTO SEATS VALUES('" + seat.getId() + flight.getFlightNum() + "', '" + flight.getFlightNum() + "', '" + seat.getReserved() + "', '" + seat.getBooked() + "', " + seat.getPrice() + ", " + seat.getTakings() + ", '" + passId + "' )");
                            }
                            else
                            {
                                stat.execute("INSERT INTO SEATS VALUES('" + seat.getId() + flight.getFlightNum() + "', '" + flight.getFlightNum() + "', '" + seat.getReserved() + "', '" + seat.getBooked() + "', " + seat.getPrice() + ", " + seat.getTakings() + ", NULL )");
                            }

                            
                            
                            passId++;
                        }

                    }

                
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadFlights() throws SQLException
    {
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ScotiaDb");
        Statement stat = conn.createStatement();
        ResultSet res = stat.executeQuery("SELECT * FROM FLIGHTS");
        while(res.next())
        {
            String id = res.getString("id");
            String arrival = res.getString("arrivalairport");
            String departure = res.getString("departureairport");
            int rows = res.getInt("rowsnum");
            int cols = res.getInt("columnsnum");
            boolean booking = res.getBoolean("bookingavailable");
            boolean checking = res.getBoolean("checkingin");
            boolean closed = res.getBoolean("closed");
            boolean boarding = res.getBoolean("boarding");
            int freeSeats = res.getInt("freeseats");
            int bookedSeats = res.getInt("bookedseats");
            int reservedSeats = res.getInt("reservedseats");
            String date = res.getString("date");
            String time = res.getString("time");
            Flight temp = new Flight(id, arrival, departure, cols, rows, date, time, booking, checking, boarding, closed, freeSeats, reservedSeats, bookedSeats);
            flights.put(temp.getFlightNum(), temp);
            System.out.println(temp.getFlightNum());
        }
    }
    
}
