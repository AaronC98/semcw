package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }

    @AfterAll
    static void disconnect() {
        app.disconnect();
    }

    @Test
    void populationWorldDesc() {
        ArrayList<Country> countries = app.populationWorldDesc();
        Country country = countries.get(0);

        int a = 1277558000;
        int b = country.Population;
        assertEquals(a, b);

    }

    @Test
    void citiesInRegionDesc() {
        ArrayList<City> cities = app.CitiesInRegionDesc("Nordic Countries");
        City city = cities.get(0);
        int a = 750348;
        int b = city.population;
        assertEquals(a, b);

    }

    @Test
    void AllCountriesInRegion() {
        ArrayList<Country> countries = app.AllCountriesInRegion("Nordic Countries");
        Country country = countries.get(0);
        int a =  8861400;
        int b = country.Population;
        assertEquals(a, b);

    }

    @Test
    void topNWorld() {
        ArrayList<Country> countries = app.topNWorld(5);
        String a = "China";
        String b = countries.get(0).Name;
        assertEquals(a, b);

    }

    @Test
    void topNCapitalsContinent() {
        ArrayList<City> city = app.topNCapitalsContinent(5);
        String a = city.get(0).name;
        String b = "Seoul";
        assertEquals(a, b);
    }


    @Test
    void topNCitiesContinent() {
        ArrayList<City> city = app.topnNCitiesContinent(5);
        String a = "Mumbai (Bombay)";
        String b = city.get(0).name;
        assertEquals(a, b);
    }

    @Test
    void TopNpopulatedCities() {
        ArrayList<City> city = app.topNCitiesWorld(4);
        String a = "Mumbai (Bombay)";
        String b = city.get(0).name;
        assertEquals(a, b);
    }



    }