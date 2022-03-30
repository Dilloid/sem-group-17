package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Fun unit tests
 */
public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    /**
     * Populations print test for null
     */
    @Test
    void populationPrintNull()
    {
        app.printPopulations(null);
    }

    /**
     * Populations print test for empty list
     */
    @Test
    void populationPrintZero()
    {
        ArrayList<Population> populations = new ArrayList<Population>();
        app.printPopulations(populations);
    }

    /**
     * City print test for null
     */
    @Test
    void cityPrintNull()
    {
        app.printCities(null);
    }

    /**
     * City print test for empty list
     */
    @Test
    void cityPrintZero()
    {
        ArrayList<City> cities = new ArrayList<City>();
        app.printCities(cities);
    }

    /**
     * Capital print test for null
     */
    @Test
    void capitalCityPrintNull()
    {
        app.printCapitalCities(null);
    }

    /**
     * Capital city print test for empty list
     */
    @Test
    void capitalCityPrintZero()
    {
        ArrayList<CapitalCity> cities = new ArrayList<CapitalCity>();
        app.printCapitalCities(cities);
    }

    /**
     * Country print test for null
     */
    @Test
    void countryPrintNull()
    {
        app.printCountries(null);
    }

    /**
     * Country print test for empty list
     */
    @Test
    void countryPrintZero()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        app.printCountries(countries);
    }

    /**
     * Populations language print test for null
     */
    @Test
    void populationLangPrintNull()
    {
        app.printLanguagePopulations(null);
    }

    /**
     * Populations language print test for empty list
     */
    @Test
    void populationLangPrintZero()
    {
        ArrayList<Population> langPopulations = new ArrayList<Population>();
        app.printLanguagePopulations(langPopulations);
    }

    /**
     * Populations test for Banana
     */
    @Test
    void populationRuralUrbanBanana()
    {
        app.populationRuralUrban("Banana");
    }

    /**
     * Populations test for null
     */
    @Test
    void populationRuralUrbanNull()
    {
        app.populationRuralUrban(null);
    }

    /**
     * Countries test for null, null
     */
    @Test
    void countriesByPopulationTestBothNull()
    {
        app.countriesByPopulation(null, null);
    }

    /**
     * Countries test for null
     */
    @Test
    void countriesByPopulationTestAreaTypeNull()
    {
        app.countriesByPopulation(null, "Europe");
    }

    /**
     * Countries test for Banana
     */
    @Test
    void countriesByPopulationTestAreaTypeInvalid()
    {
        app.countriesByPopulation("Banana", "Europe");
    }

    /**
     * Countries test for area name null
     */
    @Test
    void countriesByPopulationTestAreaNameNull()
    {
        app.countriesByPopulation("Continent", null);
    }

    /**
     * N countries test for null, null
     */
    @Test
    void topNCountriesByPopulationTestBothNull()
    {
        app.topNCountriesByPopulation(null, null, 10);
    }

    /**
     * N countries test for null
     */
    @Test
    void topNCountriesByPopulationTestAreaTypeNull()
    {
        app.topNCountriesByPopulation(null, "Europe", 10);
    }

    /**
     * N countries test for Banana
     */
    @Test
    void topNCountriesByPopulationTestAreaTypeInvalid()
    {
        app.topNCountriesByPopulation("Banana", "Europe", 10);
    }

    /**
     * N countries test for area name null
     */
    @Test
    void topNCountriesByPopulationTestAreaNameNull()
    {
        app.topNCountriesByPopulation("Continent", null, 10);
    }

    /**
     * N countries test for 0
     */
    @Test
    void topNCountriesByPopulationTestNZero()
    {
        app.topNCountriesByPopulation("Continent", "Europe", 0);
    }

    /**
     * N world countries test for 0
     */
    @Test
    void topNWorldCities()
    {
        app.topNWorldCitiesByPopulation( 0);
    }

    /**
     * N world cities test for -5
     */
    @Test
    void topNWorldCitiesNegative()
    {
        app.topNWorldCitiesByPopulation( -5);
    }

    /**
     * N cities test for name, name
     */
    @Test
    void topNCities()
    {
        app.topNCitiesByPopulation("Ruby", "Squirrel", 10);
    }

    /**
     * N cities test for null, null, 0
     */
    @Test
    void topNCitiesNull()
    {
        app.topNCitiesByPopulation(null, null, 0);
    }

    /**
     * Cities test for null, null
     */
    @Test
    void citiesByPopulationTestNull()
    {
        app.citiesByPopulation(null, null);
    }

    /**
     * Cities test for name, null
     */
    @Test
    void citiesByPopulationTestAreaNameMisspell()
    {
        app.citiesByPopulation("Pariz", null);
    }

    /**
     * Cities test for null, name
     */
    @Test
    void citiesByPopulationTestAreaMisspell()
    {
        app.citiesByPopulation(null,"Frence" );
    }

    @Test
    void languagePopul() {app.languagePops(new String[]{"Chin", "Enish", "Hdi", "Spanh", "Aric"});}


}