package com.napier.sem;

/**
 * Represents a City
 */
public class City {

    /**
     * Name of the city
     */
    private String name;

    /**
     * Country of the city as a country code
     */
    private String country;

    /**
     * District of the city
     */
    private String district;

    /**
     * Population of the city
     */
    private int population;

    /**
     * Constructor for City class
     * @param name name of the city
     * @param country country of the city as a country code
     * @param district district of the city
     * @param population population of the city
     */
    public City(String name, String country, String district, int population)
    {
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    /**
     * Get name of the city
     */
    public String getName() {
        return name;
    }

    /**
     * Get country of the city as a country code
     */
    public String getCountry() {
        return country;
    }

    /**
     * Get district of the city
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Get population of the city
     */
    public int getPopulation() {
        return population;
    }

    /**
     * ToString for capital city
     */
    @Override
    public String toString() {
        return "City{" +
                "name=" + name +
                ", countryCode=" + country +
                ", district=" + district +
                ", population=" + population +
                "}";
    }
}