package com.napier.sem;

public class CapitalCity {

    /**
     * Name of the capital city
     */
    private String name;

    /**
     * Country of the capital city
     */
    private String country;

    /**
     * Population of the capital city
     */
    private int population;

    /**
     * Constructor for capital city
     */
    public CapitalCity(String name, String country, int population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    /**
     * Get name of the capital city
     */
    public String getName() {
        return name;
    }

    /**
     * Get country of the capital city
     */
    public String getCountry() {
        return country;
    }

    /**
     * Get population of the capital city
     */
    public int getPopulation() {
        return population;
    }

    /**
     * ToString for capital city
     */
    @Override
    public String toString() {
        return "CapitalCity{" +
                "name='" + name +
                ", country='" + country +
                ", population=" + population +
                '}';
    }
}
