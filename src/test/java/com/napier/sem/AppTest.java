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
    void Bananas()
    {
        // Bananas
    }

    /**
     * Test for Banana
     */
    @Test
    void populationRuralUrbanBanana()
    {
        app.populationRuralUrban("Banana");
    }

    /**
     * Test for null
     */
    @Test
    void populationRuralUrbanNull()
    {
        app.populationRuralUrban(null);
    }

    @Test
    void countriesByPopulationTestBothNull()
    {
        app.countriesByPopulation(null, null);
    }

    @Test
    void countriesByPopulationTestAreaTypeNull()
    {
        app.countriesByPopulation(null, "Europe");
    }

    @Test
    void countriesByPopulationTestAreaTypeInvalid()
    {
        app.countriesByPopulation("Banana", "Europe");
    }

    @Test
    void countriesByPopulationTestAreaNameNull()
    {
        app.countriesByPopulation("Continent", null);
    }

    @Test
    void topNCountriesByPopulationTestBothNull()
    {
        app.topNCountriesByPopulation(null, null, 10);
    }

    @Test
    void topNCountriesByPopulationTestAreaTypeNull()
    {
        app.topNCountriesByPopulation(null, "Europe", 10);
    }

    @Test
    void topNCountriesByPopulationTestAreaTypeInvalid()
    {
        app.topNCountriesByPopulation("Banana", "Europe", 10);
    }

    @Test
    void topNCountriesByPopulationTestAreaNameNull()
    {
        app.topNCountriesByPopulation("Continent", null, 10);
    }

    @Test
    void topNCountriesByPopulationTestNZero()
    {
        app.topNCountriesByPopulation("Continent", "Europe", 0);
    }

    @Test
    void topNCountriesByPopulationTestNMinus()
    {
        app.topNCountriesByPopulation("Continent", "Europe", -10);
    }
}
