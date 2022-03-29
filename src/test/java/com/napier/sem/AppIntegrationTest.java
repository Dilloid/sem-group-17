package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Fun integration tests
 */
public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
    }

    /**
     * Integration test for populationRuralUrban
     */
    @Test
    void testPopulationsRuralUrban()
    {
        Population pop = app.populationRuralUrban("Country").get(73);
        assertEquals(pop.getArea(), "United Kingdom");
        assertEquals(pop.getPopulation().toString(), "59623400");
        assertEquals(pop.getUrban().toString(), "22436673");
        assertEquals(pop.getUrbanPercentage(), 37.630699157714844);
        assertEquals(pop.getRural().toString(), "37186727");
        assertEquals(pop.getRuralPercentage(), 62.369300842285156);
    }

    /**
     * Integration test for countriesByPopulation
     */
    @Test
    void testCountriesByPopulation()
    {
        Country country = app.countriesByPopulation("Continent", "Europe").get(1);
        assertEquals(country.getCode(), "DEU");
        assertEquals(country.getName(), "Germany");
        assertEquals(country.getContinent(), "Europe");
        assertEquals(country.getRegion(), "Western Europe");
        assertEquals(country.getPopulation().toString(), "82164700");
        assertEquals(country.getCapitalName(), "Berlin");
    }

    /**
     * Integration test for worldCountriesByPopulation
     */
    @Test
    void testWorldCountriesByPopulation()
    {
        Country country = app.worldCountriesByPopulation().get(8);
        assertEquals(country.getCode(), "JPN");
        assertEquals(country.getName(), "Japan");
        assertEquals(country.getContinent(), "Asia");
        assertEquals(country.getRegion(), "Eastern Asia");
        assertEquals(country.getPopulation().toString(), "126714000");
        assertEquals(country.getCapitalName(), "Tokyo");
    }

    /**
     * Integration test for topNCountriesByPopulation
     */
    @Test
    void testTopNCountriesByPopulation()
    {
        Country country = app.topNCountriesByPopulation("Region", "Eastern Africa",10).get(4);
        assertEquals(country.getCode(), "MOZ");
        assertEquals(country.getName(), "Mozambique");
        assertEquals(country.getContinent(), "Africa");
        assertEquals(country.getRegion(), "Eastern Africa");
        assertEquals(country.getPopulation().toString(), "19680000");
        assertEquals(country.getCapitalName(), "Maputo");
    }

    /**
     * Integration test for topNWorldCountriesByPopulation
     */
    @Test
    void testTopNWorldCountriesByPopulation()
    {
        Country country = app.topNWorldCountriesByPopulation(25).get(20);
        assertEquals(country.getCode(), "FRA");
        assertEquals(country.getName(), "France");
        assertEquals(country.getContinent(), "Europe");
        assertEquals(country.getRegion(), "Western Europe");
        assertEquals(country.getPopulation().toString(), "59225700");
        assertEquals(country.getCapitalName(), "Paris");
    }

    /**
     * Integration test for citiesByPopulation
     */
    @Test
    void testCitiesByPopulation()
    {
        City city = app.citiesByPopulation("District", "Scotland").get(1);
        assertEquals(city.getName(), "Edinburgh");
        assertEquals(city.getCountry(), "United Kingdom");
        assertEquals(city.getDistrict(), "Scotland");
        assertEquals(city.getPopulation(), 450180);
    }

    /**
     * Integration test for worldCitiesByPopulation
     */
    @Test
    void testWorldCitiesByPopulation()
    {
        City city = app.worldCitiesByPopulation().get(12);
        assertEquals(city.getName(), "London");
        assertEquals(city.getCountry(), "United Kingdom");
        assertEquals(city.getDistrict(), "England");
        assertEquals(city.getPopulation(), 7285000);
    }

    /**
     * Integration test for topNCitiesByPopulation
     */
    @Test
    void testTopNCitiesByPopulation()
    {
        City city = app.topNCitiesByPopulation("District", "Florida", 15).get(3);
        assertEquals(city.getName(), "Saint Petersburg");
        assertEquals(city.getCountry(), "United States");
        assertEquals(city.getDistrict(), "Florida");
        assertEquals(city.getPopulation(), 248232);
    }

    /**
     * Integration test for topNWorldCitiesByPopulation
     */
    @Test
    void testTopNWorldCitiesByPopulation()
    {
        City city = app.topNWorldCitiesByPopulation(25).get(9);
        assertEquals(city.getName(), "New York");
        assertEquals(city.getCountry(), "United States");
        assertEquals(city.getDistrict(), "New York");
        assertEquals(city.getPopulation(), 8008278);
    }

    @Test
    void testCapitalCitiesByPopulation()
    {
        CapitalCity city = app.capitalCitiesByPopulation("Continent", "Asia").get(0);
        assertEquals(city.getName(), "Seoul");
        assertEquals(city.getPopulation(), 9981619);
        assertEquals(city.getCountry(), "South Korea");
    }

    @Test
    void testWorldCapitalCitiesByPopulation()
    {
        CapitalCity city = app.worldCapitalCitiesByPopulation().get(10);
        assertEquals(city.getName(), "Bangkok");
        assertEquals(city.getPopulation(), 6320174);
        assertEquals(city.getCountry(), "Thailand");
    }

    @Test
    void testTopNCapitalCitiesByPopulation()
    {
        CapitalCity city = app.topNCapitalCitiesByPopulation("Region", "Central America", 10).get(0);
        assertEquals(city.getName(), "Ciudad de México");
        assertEquals(city.getPopulation(), 8591309);
        assertEquals(city.getCountry(), "Mexico");
    }

    @Test
    void testTopNWorldCapitalCitiesByPopulation()
    {
        CapitalCity city = app.topNWorldCapitalCitiesByPopulation(10).get(7);
        assertEquals(city.getName(), "Cairo");
        assertEquals(city.getPopulation(), 6789479);
        assertEquals(city.getCountry(), "Egypt");
    }

    @Test
    void testAreaPopulation()
    {
        Population pop = app.areaPopulation("Continent", "South America");
        assertEquals(pop.getArea(), "South America");
        assertEquals(pop.getPopulation().toString(), "345780000");
    }

    @Test
    void testWorldPopulation()
    {
        Population pop = app.worldPopulation();
        assertEquals(pop.getArea(), "World");
        assertEquals(pop.getPopulation().toString(), "6078749450");
    }

    @Test
    void testLanguagePops()
    {
        Population pop = app.languagePops(new String[]{"French"}).get(0);
        assertEquals(pop.getArea(), "French");
        assertEquals(pop.getPopulation().toString(), "148556800");
        assertEquals(pop.getUrbanPercentage(), 2.4439001083374023);
    }
}
