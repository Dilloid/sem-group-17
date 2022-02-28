package com.napier.sem;

public class City {

    /**
     * Name of the city
     */
    private String name;

    /**
     * Country of the city
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
     * Get name of the city
     */
    public String getName() {
        return name;
    }

    /**
     * Get country of the city
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
                ", country='" + country +
                ", district='" + district +
                ", population=" + population +
                '}';
    }
}