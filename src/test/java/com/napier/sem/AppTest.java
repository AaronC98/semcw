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
    void printSalariesTestNull()
    {
        app.displayCountry(null);
    }

    @Test
    void printSalariesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        app.displayCountry(countries);
    }

    @Test
    void printSalariesTestContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        app.displayCountry(countries);
    }

    @Test
    void printSalaries()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.Name="Kenya";
        country.Population= 200000;
        countries.add(country);
        app.displayCountry(countries);
    }
}