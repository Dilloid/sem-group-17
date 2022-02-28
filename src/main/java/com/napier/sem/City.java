package com.napier.sem;

public class City {

    /**
     * Constructor for City class
     * @param name name of the city
     * @param countryCode country of the city as a country code
     * @param district district of the city
     * @param population population of the city
     */
    public City(String name, String countryCode, String district, int population)
    {
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    /**
     * Name of the city
     */
    private String name;

    /**
     * Country of the city as a country code
     */
    private String countryCode;

    /**
     * District of the city
     */
    private String district;

    /**
     * Population of the city
     */
    private int population;

    /**
     * Get name of the city
     */
    public String getName() {
        return name;
    }

    /**
     * Get country of the city as a country code
     */
    public String getCountryCode() {
        return countryCode;
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
                ", countryCode=" + countryCode +
                ", district=" + district +
                ", population=" + population +
                "}";
    }
}