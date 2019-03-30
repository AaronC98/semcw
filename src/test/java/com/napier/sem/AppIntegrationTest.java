package com.napier.sem;

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

    @Test
    void testGetCity()
    {
        City city = app.getCity(12);
        assertEquals(city.name, "Breda");
        assertEquals(city.CountryCode, "NLD");
        assertEquals(city.population, 160398);
    }

}