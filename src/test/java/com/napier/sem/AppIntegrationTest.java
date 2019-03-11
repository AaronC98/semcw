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
        app.connect("db");
    }

    @Test
    void testGetEmployee()
    {
        City city = app.getCity(1);
        assertEquals(city.name, "Kabul");
        assertEquals(city.CountryCode, "AFG");
        assertEquals(city.population, 1780000);
    }
}