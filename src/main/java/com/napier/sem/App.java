package com.napier.sem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class App {
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        if (args.length < 1) {
            a.connect("35.246.7.68:3306");
        } else {
            a.connect(args[0]);
        }

        SpringApplication.run(App.class, args);

        //Listing all countries in the world in descending order.
        System.out.println("All Countries in the World: ");
        ArrayList<Country> countries = a.populationWorldDesc();
        a.displayCountry(countries);

        //Listing all cities in a region
        String region = "Nordic Countries";
        System.out.println("\nListing all cities in " + region);
        ArrayList<City> citiesInRegion = a.CitiesInRegionDesc(region);
        a.displayCities(citiesInRegion);
        
        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private static Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public static void connect(String location) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public static void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public ArrayList<Country> populationWorldDesc() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Population "
                            + "FROM country "
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next()) {

                Country country = new Country();
                country.Name = rset.getString("Name");
                country.Population = rset.getInt("Population");
                countries.add(country);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public void displayCountry(ArrayList<Country> countries) {
        // Check employees is not null
        if (countries == null)
        {
            System.out.println("No countries.");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-20s", "Name", "Population"));
        // Loop over all employees in the list
        for (Country country : countries) {
            if (country == null)
                continue;
            String ctry_string =
                    String.format("%-10s %-20s",
                            country.Name, country.Population);
            System.out.println(ctry_string);
            
        }
    }

    public ArrayList<City>CitiesInRegionDesc(String region)
    {
        try {

            ArrayList<City> cities = new ArrayList<City>();

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "SELECT city.Name, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE country.Region = '" + region +"' "
                            + "AND  city.CountryCode = country.Code "
                            + "ORDER BY Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.district = rset.getString("city.District");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public void displayCities(ArrayList<City> cities) {
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s", "Name", "District", "Population"));
        // Loop over all employees in the list
        for (City city : cities) {
            String emp_string =
                    String.format("%-10s %-15s %-20s",
                            city.name, city.district, city.population);
            System.out.println(emp_string);
        }
    }



    public City getCity(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, CountryCode, District, Population FROM city "
                            + "WHERE ID = " + ID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City city = new City();
                city.name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
                city.population = rset.getInt("Population");
                return city;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }
}