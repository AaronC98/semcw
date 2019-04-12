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
            a.connect("localhost:33060");
        } else {
            a.connect(args[0]);
        }

        //Listing all countries in the world in descending order.
        System.out.println("All Countries in the World: ");
        ArrayList<Country> countries = a.populationWorldDesc();
        a.displayCountry(countries);

        //Listing all cities in a region.
        String region = "Nordic Countries";
        System.out.println("\nAll cities in " + region);
        ArrayList<City> citiesInRegion = a.CitiesInRegionDesc(region);
        a.displayCities(citiesInRegion);

        //Listing all countries in a region in descending order.
        region = "Nordic Countries";
        System.out.println("\nAll Countries in" + region + ": ");
        ArrayList<Country> AllCountriesInRegion = a.AllCountriesInRegion(region);
        a.displayCountriesByRegion(AllCountriesInRegion);

        //Listing top N countries in the world
        int n = 5;
        System.out.println("\nTop " + n + " countries in the world.");
        ArrayList<Country> topNCountriesInWorld = a.topNWorld(n);
        a.displayCountries(topNCountriesInWorld);

        //Listing top N capitals per continent.
        n = 10;
        System.out.println("\nTop " + n + " capitals per continent:");
        ArrayList<City> topNCapitalsContinent = a.topNCapitalsContinent(n);
        a.displayCities(topNCapitalsContinent);

        //Listing top N populated cities in the world.
        n = 10;
        System.out.println("\nTop " + n + " cities in the world:");
        ArrayList<City> topNCitiesWorld = a.topNCitiesWorld(n);
        a.displayCities(topNCitiesWorld);

        //Listing top N cities per continent.
        n = 5;
        System.out.println("\nTop " + n + " cities per continent:");
        ArrayList<City> topNCitiesContinent = a.topnNCitiesContinent(n);
        a.displayCities(topNCitiesContinent);

        //All Cities in a Continent largest population to smallest
        String cont = "Asia";
        System.out.println("\nAll cities in a continent organised by largest population to smallest:");
        ArrayList<City> CitiesInContinent = a.CitiesInContinent(cont);
        a.displayCities(CitiesInContinent);

        //All Cities in a District largest population to smallest
        String district = "Kabol";
        System.out.println("\nAll cities in a district organised by largest population to smallest:");
        ArrayList<City> CitiestInDistrict = a.CitiesInDistrict(district);
        a.displayCities(CitiestInDistrict);

        //All Cities in a Region Largest population to smallest
        region = "Caribbean";
        System.out.println("\nAll cities in a region organised by largest population to smallest");
        ArrayList<City> CitiesInRegion = a.CitiesInRegion(region);
        a.displayCities(CitiesInRegion);

        //The top N populated cities in a country
        n = 5;
        String country = "Canada";
        System.out.println("\nTop " + n + " cities in a country");
        ArrayList<City> TopNCitiesInCountry = a.TopNCitiesInCountry(country, n);
        a.displayCities(TopNCitiesInCountry);

        //The top N populated cities in a region
        n = 5;
        region = "Caribbean";
        System.out.println("\nTop " + n + "cities in a region");
        ArrayList<City> TopNCitiesInRegion = a.TopNCitiesInRegion(region, n);
        a.displayCities(TopNCitiesInRegion);



//        //TO-DO
//        //Listing the population of people, people in cities, and people not living in cities in each continent
//        System.out.println("\nListing the population of people, people in cities, and people not living in cities in each continent.");
//        ArrayList<Population> populationReportRegion = a.populationReportRegion();
//        a.displayPopulation(populationReportRegion);

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
        if (countries == null) {
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

    public ArrayList<City> CitiesInRegionDesc(String region) {
        try {

            ArrayList<City> cities = new ArrayList<City>();

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "SELECT city.Name, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE country.Region = '" + region + "' "
                            + "AND  city.CountryCode = country.Code "
                            + "ORDER BY Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.district = rset.getString("city.District");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public ArrayList<Country> AllCountriesInRegion(String region) {
        try {

            ArrayList<Country> countries = new ArrayList<Country>();

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Region, Population "
                            + "FROM country "
                            + "WHERE Region = '" + region + "' "
                            + "ORDER BY Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                Country country = new Country();
                country.Name = rset.getString("Name");
                country.region = rset.getString("Region");
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

    public ArrayList<Country> topNWorld(int n) {
        try {
            Statement stmt = con.createStatement();
            String strtopNWorld =
                    "SELECT Name, Continent, Population "
                            + "FROM country "
                            + "ORDER BY Population DESC LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strtopNWorld);
            ArrayList<Country> topNcountries = new ArrayList<Country>();
            while (rset.next()) {
                Country country = new Country();
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Population = rset.getInt("Population");
                topNcountries.add(country);
            }
            return topNcountries;
        } catch (Exception e) {
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

    public void displayCountriesByRegion(ArrayList<Country> countries) {
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s", "Name", "Region", "Population"));
        // Loop over all employees in the list
        for (Country country : countries) {
            String emp_string =
                    String.format("%-10s %-15s %-20s",
                            country.Name, country.region, country.Population);
            System.out.println(emp_string);
        }
    }

    public void displayCountries(ArrayList<Country> countries) {
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s", "Name", "Continent", "Population"));
        // Loop over all employees in the list
        for (Country country : countries) {
            String emp_string =
                    String.format("%-10s %-15s %-20s",
                            country.Name, country.Continent, country.Population);
            System.out.println(emp_string);
        }
    }

    /*
        public void displayPop(ArrayList<Population> pops){
            System.out.println(String.format("%-15s %-20s %-15s %-20s","Continent", "Continent Pop", "City Pop", "Not City Pop"));
            for (Population pop : pops) {
                String emp_string =
                        String.format("%-15s %-20s %-15s %-20s",
                                pop.population, pop.regoionPop, pop.cityPop, pop.nonCityPop);
                System.out.println(emp_string);
            }
        }
    */
    public ArrayList<City> topNCapitalsContinent(int n) {
        try {
            String[] continents = new String[]{"Asia", "Europe", "North America", "Africa", "Oceania", "Antarctica", "South America"};

            ArrayList<City> cities = new ArrayList<City>();

            for (String cont : continents) {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT city.Name, city.District, city.Population "
                                + "FROM city, country "
                                + "WHERE country.Continent = '" + cont + "' "
                                + "AND city.ID = country.Capital "
                                + "ORDER BY Population DESC LIMIT " + n;
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract employee information

                while (rset.next()) {
                    City city = new City();
                    city.name = rset.getString("Name");
                    city.district = rset.getString("District");
                    city.population = rset.getInt("Population");
                    cities.add(city);
                }
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }


    public ArrayList<City> topNpopulatedCitiesInContinent(int n) {
        try {

            String[] continents = new String[]{"Asia", "Europe", "North America", "Africa", "Oceania", "Antarctica", "South America"};
            ArrayList<City> topNpopulationCities = new ArrayList<City>();

            for (String cont : continents) {
                Statement stmt = con.createStatement();
                String strtopNWorld =
                        "SELECT city.Name, city.District, city.Population "
                                + "FROM city, country "
                                + "WHERE Continent = '" + cont + "' "
                                + "AND city.CountryCode = country.Code "
                                + "ORDER BY Population DESC LIMIT " + n;

                ResultSet rset = stmt.executeQuery(strtopNWorld);

                while (rset.next()) {
                    City city = new City();
                    city.name = rset.getString("Name");
                    city.district = rset.getString("District");
                    city.population = rset.getInt("Population");
                    topNpopulationCities.add(city);
                }
            }
            return topNpopulationCities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public ArrayList<City> topNCitiesWorld(int n) {
        try {
            Statement stmt = con.createStatement();
            String strtopNWorld =
                    "SELECT Name, District,Population "
                            + "FROM city "
                            + "ORDER BY Population DESC LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strtopNWorld);
            ArrayList<City> topNpopulationCities = new ArrayList<City>();
            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                topNpopulationCities.add(city);
            }
            return topNpopulationCities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }


    public ArrayList<City> topnNCitiesContinent(int n) {
        try {
            String[] continents = new String[]{"Asia", "Europe", "North America", "Africa", "Oceania", "Antarctica", "South America"};
            ArrayList<City> topNpopulationCities = new ArrayList<City>();

            for (String cont : continents) {
                Statement stmt = con.createStatement();
                String strtopNWorld =
                        "SELECT city.Name, city.District, city.Population "
                                + "FROM city, country "
                                + "WHERE Continent = '" + cont + "' "
                                + "AND city.CountryCode = country.Code "
                                + "ORDER BY Population DESC LIMIT " + n;

                ResultSet rset = stmt.executeQuery(strtopNWorld);

                while (rset.next()) {
                    City city = new City();
                    city.name = rset.getString("Name");
                    city.district = rset.getString("District");
                    city.population = rset.getInt("Population");
                    topNpopulationCities.add(city);
                }
            }
            return topNpopulationCities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    //All the cities in a continent organised by largest population to smallest
    public ArrayList<City> CitiesInContinent(String cont) {
        try {
        ArrayList<City> cities = new ArrayList<>();

            Statement stmt = con.createStatement();
            String strCitiesInCont =
                    "SELECT city.Name, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE Continent = '" + cont + "' "
                            + "AND city.CountryCode = country.Code "
                            + "ORDER BY Population DESC";

            ResultSet rset = stmt.executeQuery(strCitiesInCont);

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                cities.add(city);
            }

            return cities;
        } catch (
                Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    //All the cities in a district organised by largest population to smallest
    public ArrayList<City> CitiesInDistrict(String district) {
        try {
            ArrayList<City> cities = new ArrayList<>();

            Statement stmt = con.createStatement();
            String strCitiesInDistrict =
                    "SELECT city.Name, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE city.District = '" + district + "' "
                            + "AND city.CountryCode = country.Code "
                            + "ORDER BY Population DESC";

            ResultSet rset = stmt.executeQuery(strCitiesInDistrict);

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                cities.add(city);
            }

            return cities;
        } catch (
                Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    //All the countries in a region organised by largest population to smallest
    public ArrayList<City> CitiesInRegion(String region) {
        try {
            ArrayList<City> cities = new ArrayList<>();

            Statement stmt = con.createStatement();
            String strCitiesInRegion =
                    "SELECT city.Name, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE country.Region = '" + region + "' "
                            + "AND city.CountryCode = country.Code "
                            + "ORDER BY Population DESC";

            ResultSet rset = stmt.executeQuery(strCitiesInRegion);

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                cities.add(city);
            }

            return cities;
        } catch (
                Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    //The top N populated cities in a country
    public ArrayList<City> TopNCitiesInCountry(String country, int n) {
        try {
            ArrayList<City> cities = new ArrayList<>();

            Statement stmt = con.createStatement();
            String strTopNCitiesInCountry =
                    "SELECT city.Name, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE country.Name = '" + country + "' "
                            + "AND city.CountryCode = country.Code "
                            + "ORDER BY Population DESC LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strTopNCitiesInCountry);

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                cities.add(city);
            }

            return cities;
        } catch (
                Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    //The top N populated capital cities in a region
    public ArrayList<City> TopNCitiesInRegion(String region, int n) {
        try {
            ArrayList<City> cities = new ArrayList<>();

            Statement stmt = con.createStatement();
            String strTopNCitiesInRegion =
                    "SELECT city.Name, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE country.Region = '" + region + "' "
                            + "AND city.CountryCode = country.Code "
                            + "ORDER BY Population DESC LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strTopNCitiesInRegion);

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                cities.add(city);
            }

            return cities;
        } catch (
                Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }


}