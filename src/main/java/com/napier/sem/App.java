package com.napier.sem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
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
           // a.connect("35.246.7.68:3306");
            a.connect("localhost:33060");
        } else {
            a.connect(args[0]);
        }

        //SpringApplication.run(App.class, args);

        int n;
        String region;

        //Listing all countries in the world in descending order.
        System.out.println("All Countries in the World: "); // NOPMD
        ArrayList<Country> countries = a.populationWorldDesc();
        a.displayCountries(countries);

        //Listing all the countries in a continent descending order.
        String continents = "Europe";
        System.out.println("All the countries in a continent: "); // NOPMD
        ArrayList<Country> CountriesContinentDesc = a.CountriesContinentDesc(continents);
        a.displayCountries(CountriesContinentDesc);

        //Listing all countries in a region in descending order.
        region = "Nordic Countries";
        System.out.println("\nAll Countries in" + region + ": "); // NOPMD
        ArrayList<Country> AllCountriesInRegion = a.AllCountriesInRegion(region);
        a.displayCountriesByRegion(AllCountriesInRegion);

        //Listing all cities in a region.
        region = "Nordic Countries";
        System.out.println("\nAll cities in " + region); // NOPMD
        ArrayList<City> citiesInRegion = a.CitiesInRegionDesc(region);
        a.displayCities(citiesInRegion);

        //Listing top N countries in the world
        n = 5;
        System.out.println("\nTop " + n + " countries in the world."); // NOPMD
        ArrayList<Country> topNCountriesInWorld = a.topNWorld(n);
        a.displayCountries(topNCountriesInWorld);

        //Listing top N countries per continent
        n = 3;
        System.out.println("\nTOP " + n + " countries per continent:"); // NOPMD
        ArrayList<Country> topNContinent = a.topNCountriesContinent(n);
        a.displayCountries(topNContinent);

        //Top n countries in a region. Sam.

        //Cities in the world desc. Sam.

        //All Cities in a Continent largest population to smallest
        String cont = "Asia";
        System.out.println("\nAll cities in a continent organised by largest population to smallest:"); // NOPMD
        ArrayList<City> CitiesInContinent = a.CitiesInContinent(cont);
        a.displayCities(CitiesInContinent);

        //All Cities in a Region Largest population to smallest
        region = "Caribbean";
        System.out.println("\nAll cities in a region organised by largest population to smallest"); // NOPMD
        ArrayList<City> CitiesInRegion = a.CitiesInRegion(region);
        a.displayCities(CitiesInRegion);

        //Cities in a country desc. Sam.

        //All Cities in a District largest population to smallest
        String district = "Kabol";
        System.out.println("\nAll cities in a district organised by largest population to smallest:"); // NOPMD
        ArrayList<City> CitiestInDistrict = a.CitiesInDistrict(district);
        a.displayCities(CitiestInDistrict);

        //Listing top N populated cities in the world.
        n = 5;
        System.out.println("\nTop " + n + " cities in the world:"); // NOPMD
        ArrayList<City> topNCitiesWorld = a.topNCitiesWorld(n);
        a.displayCities(topNCitiesWorld);

        //Listing top N cities per continent.
        n = 5;
        System.out.println("\nTop " + n + " cities per continent:"); // NOPMD
        ArrayList<City> topNCitiesContinent = a.topnNCitiesContinent(n);
        a.displayCities(topNCitiesContinent);

        //The top N populated cities in a region
        n = 5;
        region = "Caribbean";
        System.out.println("\nTop " + n + "cities in a region"); // NOPMD
        ArrayList<City> TopNCitiesInRegion = a.TopNCitiesInRegion(region, n);
        a.displayCities(TopNCitiesInRegion);

        //The top N populated cities in a country
        n = 5;
        String country = "Canada";
        System.out.println("\nTop " + n + " cities in a country"); // NOPMD
        ArrayList<City> TopNCitiesInCountry = a.TopNCitiesInCountry(country, n);
        a.displayCities(TopNCitiesInCountry);

        //Top N populated cities in a district. Andreas.

        //Listing all the capital cities in the world in descending order.
        System.out.println("Listing all the capital cities in the world: "); // NOPMD
        ArrayList<City> CapitalsWorldDesc = a.CapitalsWorldDesc(n);
        a.displayCities(CapitalsWorldDesc);

        //All capitals in a continent. Andreas.

        //All capitals in a region. Fletcher.

        //Top N capitals in the world.
        n = 5;
        System.out.println("\nTop " + n + " capital cities in the world:"); // NOPMD
        ArrayList<City> topNCapitalsWorld = a.topNCapitalsWorld(n);
        a.displayCities(topNCapitalsWorld);

        //Listing top N capitals per continent.
        n = 10;
        System.out.println("\nTop " + n + " capitals per continent:"); // NOPMD
        ArrayList<City> topNCapitalsContinent = a.topNCapitalsContinent(n);
        a.displayCities(topNCapitalsContinent);

//        //Top N capitals in region. Fletcher.
//
//        //Population reports

        System.out.println("\nShowing the population of chosen values: "); // NOPMD
        ChosenValue chosenValue = a.chosenValues("Europe", "South America", "United Kingdom", "Scotland", "Edinburgh");
        a.displayValues(chosenValue);

        //Language reports.

//        //Listing the population of people, people in cities, and people not living in cities in each region
//        System.out.println("\nListing the population of people, people in cities, and people not living in cities in each region.");
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
            System.out.println("Could not load SQL driver"); // NOPMD
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database..."); // NOPMD
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected"); // NOPMD
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i)); // NOPMD
                System.out.println(sqle.getMessage()); // NOPMD
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen."); // NOPMD
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
                System.out.println("Error closing connection to database"); // NOPMD
            }
        }
    }

    //Display methods.


    public void displayCountries(ArrayList<Country> countries) {
        // Print header
        System.out.println(String.format("%-5s %-10s %-20s %-20s %-20s %-20s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        // Loop over all employees in the list
        for (Country country : countries) {
            String emp_string =
                    String.format("%-3s %-10s %-20s %-20s %-20s %-20s",
                            country.Code, country.Name, country.Continent, country.region, country.Population, country.Capital);
            System.out.println(emp_string);
        }
    }


    public void displayCities(ArrayList<City> cities) {
        // Print header
        System.out.println(String.format("%-10s %-4s %-15s %-20s", "Name", "Country", "District", "Population"));
        // Loop over all employees in the list
        for (City city : cities) {
            String emp_string =
                    String.format("%-10s %-4s %-15s %-20s",
                            city.name, city.CountryCode, city.district, city.population);
            System.out.println(emp_string);
        }
    }

    public void displayCountriesByRegion(ArrayList<Country> countries) {
        // Print header
        System.out.println(String.format("%-3s %-10s %-20s %-20s %-20s %-20s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        // Loop over all employees in the list
        for (Country country : countries) {
            String emp_string =
                    String.format("%-3s %-10s %-20s %-20s %-20s %-20s",
                            country.Code, country.Name, country.Continent, country.region, country.Population, country.Capital);
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

    public void displayValues(ChosenValue chosenValue) {
        System.out.println(String.format("%-10s %-15s %-20s %-25s %-30s %-35s", "World", chosenValue.continent, chosenValue.region, chosenValue.country, chosenValue.district, chosenValue.city));
        String emp_string =
                String.format("%-10s %-15s %-20s %-25s %-30s %-35s",
                        chosenValue.worldPop, chosenValue.continentPop, chosenValue.regionPop, chosenValue.countryPop, chosenValue.districtPop, chosenValue.cityPop);
        System.out.println(emp_string);
    }

    public ArrayList<Country> populationWorldDesc() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next()) {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.Population = rset.getInt("Population");
                country.Capital = rset.getInt("Capital");
                countries.add(country);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public ArrayList<Country> CountriesContinentDesc(String continents) {
        try {

            ArrayList<Country> countries = new ArrayList<Country>();

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "WHERE Continent = '" + continents + "' "
                            + "ORDER BY Population DESC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.Population = rset.getInt("Population");
                country.Capital = rset.getInt("Capital");
                countries.add(country);
            }
            return countries;
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
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "WHERE Region = '" + region + "' "
                            + "ORDER BY Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.Population = rset.getInt("Population");
                country.Capital = rset.getInt("Capital");
                countries.add(country);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public ArrayList<City> CitiesInRegionDesc(String region) {
        try {

            ArrayList<City> cities = new ArrayList<City>();

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE country.Region = '" + region + "' "
                            + "AND  city.CountryCode = country.Code "
                            + "ORDER BY Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                cities.add(city);
            }
            return cities;
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
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "ORDER BY Population DESC LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strtopNWorld);
            ArrayList<Country> topNcountries = new ArrayList<Country>();
            while (rset.next()) {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.Population = rset.getInt("Population");
                country.Capital = rset.getInt("Capital");
                topNcountries.add(country);
            }
            return topNcountries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public ArrayList<Country> topNCountriesContinent(int n) {
        try {
            String[] continents = new String[]{"Asia", "Europe", "North America", "Africa", "Oceania", "Antarctica", "South America"};

            ArrayList<Country> countries = new ArrayList<Country>();

            for (String cont : continents) {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT Code, Name, Continent, Region, Population, Capital "
                                + "FROM country "
                                + "WHERE Continent = '" + cont + "' "
                                + "ORDER BY Population DESC LIMIT " + n;
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract employee information

                while (rset.next()) {
                    Country country = new Country();
                    country.Code = rset.getString("Code");
                    country.Name = rset.getString("Name");
                    country.Continent = rset.getString("Continent");
                    country.region = rset.getString("Region");
                    country.Population = rset.getInt("Population");
                    country.Capital = rset.getInt("Capital");
                    countries.add(country);
                }
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    //Top n countries in a region. Sam.

    //Cities in the world desc. Sam.

    //All the cities in a continent organised by largest population to smallest
    public ArrayList<City> CitiesInContinent(String cont) {
        try {
            ArrayList<City> cities = new ArrayList<>();

            Statement stmt = con.createStatement();
            String strCitiesInCont =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE Continent = '" + cont + "' "
                            + "AND city.CountryCode = country.Code "
                            + "ORDER BY Population DESC";

            ResultSet rset = stmt.executeQuery(strCitiesInCont);

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
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
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE country.Region = '" + region + "' "
                            + "AND city.CountryCode = country.Code "
                            + "ORDER BY Population DESC";

            ResultSet rset = stmt.executeQuery(strCitiesInRegion);

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
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
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE city.District = '" + district + "' "
                            + "AND city.CountryCode = country.Code "
                            + "ORDER BY Population DESC";

            ResultSet rset = stmt.executeQuery(strCitiesInDistrict);

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
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

    public ArrayList<City> topNCitiesWorld(int n) {
        try {
            Statement stmt = con.createStatement();
            String strtopNWorld =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM city "
                            + "ORDER BY Population DESC LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strtopNWorld);
            ArrayList<City> topNpopulationCities = new ArrayList<City>();
            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
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

    /*
        public ArrayList<City> topNpopulatedCitiesInContinent(int n) {
            try {

                String[] continents = new String[]{"Asia", "Europe", "North America", "Africa", "Oceania", "Antarctica", "South America"};
                ArrayList<City> topNpopulationCities = new ArrayList<City>();

                for (String cont : continents) {
                    Statement stmt = con.createStatement();
                    String strtopNWorld =
                            "SELECT city.Name, city.CountryCode, city.District, city.Population "
                                    + "FROM city, country "
                                    + "WHERE Continent = '" + cont + "' "
                                    + "AND city.CountryCode = country.Code "
                                    + "ORDER BY Population DESC LIMIT " + n;

                    ResultSet rset = stmt.executeQuery(strtopNWorld);

                    while (rset.next()) {
                        City city = new City();
                        city.name = rset.getString("Name");
                        city.CountryCode = rset.getString("CountryCode");
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
    */
    //The top N populated capital cities in a region
    public ArrayList<City> TopNCitiesInRegion(String region, int n) {
        try {
            ArrayList<City> cities = new ArrayList<>();

            Statement stmt = con.createStatement();
            String strTopNCitiesInRegion =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE country.Region = '" + region + "' "
                            + "AND city.CountryCode = country.Code "
                            + "ORDER BY Population DESC LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strTopNCitiesInRegion);

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
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
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE country.Name = '" + country + "' "
                            + "AND city.CountryCode = country.Code "
                            + "ORDER BY Population DESC LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strTopNCitiesInCountry);

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
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

    public ArrayList<City> topnNCitiesContinent(int n) {
        try {
            String[] continents = new String[]{"Asia", "Europe", "North America", "Africa", "Oceania", "Antarctica", "South America"};
            ArrayList<City> topNpopulationCities = new ArrayList<City>();

            for (String cont : continents) {
                Statement stmt = con.createStatement();
                String strtopNWorld =
                        "SELECT city.Name, city.CountryCode, city.District, city.Population "
                                + "FROM city, country "
                                + "WHERE Continent = '" + cont + "' "
                                + "AND city.CountryCode = country.Code "
                                + "ORDER BY Population DESC LIMIT " + n;

                ResultSet rset = stmt.executeQuery(strtopNWorld);

                while (rset.next()) {
                    City city = new City();
                    city.name = rset.getString("Name");
                    city.CountryCode = rset.getString("CountryCode");
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

    //Top N populated cities in a district. Andreas.

    public ArrayList<City> CapitalsWorldDesc(int n) {
        try {
            Statement stmt = con.createStatement();
            String strtopNWorld =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE city.ID = country.Capital "
                            + "ORDER BY Population DESC ";

            ResultSet rset = stmt.executeQuery(strtopNWorld);
            ArrayList<City> CapitalsWorldDesc = new ArrayList<City>();
            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                CapitalsWorldDesc.add(city);
            }
            return CapitalsWorldDesc;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    //Capitals in a continent desc. Andreas

    public ArrayList<City> topNCapitalsWorld(int n) {
        try {
            Statement stmt = con.createStatement();
            String strtopNWorld =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM country, city "
                            + "WHERE city.ID = country.Capital "
                            + "ORDER BY city.Population DESC LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strtopNWorld);
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public ArrayList<City> topNCapitalsContinent(int n) {
        try {
            String[] continents = new String[]{"Asia", "Europe", "North America", "Africa", "Oceania", "Antarctica", "South America"};

            ArrayList<City> cities = new ArrayList<City>();

            for (String cont : continents) {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT city.Name, city.CountryCode, city.District, city.Population "
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
                    city.CountryCode = rset.getString("CountryCode");
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

    //Top N capitals in region. Fletcher.

    public ChosenValue chosenValues(String continent, String region, String country, String district, String city) {
        ChosenValue chosenValue = new ChosenValue();
        try {
            Statement stmt = con.createStatement();
            String worldPop =
                    "SELECT SUM(country.Population) AS Population "
                            + "FROM country ";
            ResultSet worldPopulation = stmt.executeQuery(worldPop);
            while (worldPopulation.next()) {
                chosenValue.worldPop = worldPopulation.getLong("Population");
            }

            String continentPop =
                    "SELECT SUM(Population) AS Population "
                            + "FROM country "
                            + "WHERE Continent = '" + continent + "' ";
            ResultSet rsetCont = stmt.executeQuery(continentPop);
            while (rsetCont.next()) {
                chosenValue.continent = continent;
                chosenValue.continentPop = rsetCont.getLong("Population");
            }

            String regionPop =
                    "SELECT SUM(Population) As Population "
                            + "FROM country "
                            + "WHERE Region = '" + region + "' ";
            ResultSet rsetRegion = stmt.executeQuery(regionPop);
            while (rsetRegion.next()) {
                chosenValue.region = region;
                chosenValue.regionPop = rsetRegion.getInt("Population");
            }

            String countryPop =
                    "SELECT Population "
                            + "FROM country "
                            + "WHERE Name = '" + country + "' ";
            ResultSet rsetCountry = stmt.executeQuery(countryPop);
            while (rsetCountry.next()) {
                chosenValue.country = country;
                chosenValue.countryPop = rsetCountry.getInt("Population");
            }

            String districtPop =
                    "SELECT SUM(Population) As Population "
                            + "FROM city "
                            + "WHERE District = '" + district + "' ";
            ResultSet rsetDistrict = stmt.executeQuery(districtPop);
            while (rsetDistrict.next()) {
                chosenValue.district = district;
                chosenValue.districtPop = rsetDistrict.getInt("Population");
            }

            String cityPop =
                    "SELECT SUM(Population) As Population "
                            + "FROM city "
                            + "WHERE Name = '" + city + "' ";
            ResultSet rsetCity = stmt.executeQuery(cityPop);
            while (rsetCity.next()) {
                chosenValue.city = city;
                chosenValue.cityPop = rsetCity.getInt("Population");
            }
            return chosenValue;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    //Language reports.


}