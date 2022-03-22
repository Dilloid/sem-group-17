package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

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

    @Test
    void populationRuralUrbanBanana()
    {
        app.populationRuralUrban("Banana");
    }

    @Test
    void populationRuralUrbanNull()
    {
        app.populationRuralUrban(null);
    }

    @Test
    void citiesByPopulationTestNull()
    {
        app.citiesByPopulation(null, null);
    }
}