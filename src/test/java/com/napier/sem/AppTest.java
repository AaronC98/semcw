package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

   @Test
    void displayCountries() {
        ArrayList<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.Name = "Sweden";
        country.Continent = "Europe";
        country.Population = 8861400;
        countries.add(country);
        app.displayCountries(countries);
   }

   @Test
   void displayCities() {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.name = "Edinburgh";
        city.district = "West Lothian";
        city.population = 100000;

        cities.add(city);
        app.displayCities(cities);
   }

   @Test
    void displayCountriesByRegion() {
       ArrayList<Country> countries = new ArrayList<>();
       Country country = new Country();
       Country country1 = new Country();
       Country country2 = new Country();

       country.Name = "Sweden";
       country.Continent = "Europe";
       country.Population = 8861400;
       countries.add(country);

       country.Name = "Aruba";
       country.Continent = "North America";
       country.Population = 103000;
       countries.add(country1);

       app.displayCountriesByRegion(countries);
   }


}