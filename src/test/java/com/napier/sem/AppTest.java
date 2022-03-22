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
    void topNWorldCities()
    {
        app.topNWorldCitiesByPopulation( 0);
    }

    @Test
    void topNWorldCitiesNegative()
    {
        app.topNWorldCitiesByPopulation( -5);
    }

    @Test
    void topNCities()
    {
        app.topNCitiesByPopulation("Ruby", "Squirrel", 10);
    }

    @Test
    void topNCitiesNull()
    {
        app.topNCitiesByPopulation(null, null, 0);
    }


}
