package com.napier.sem;

public class Country {

    /**
     * Constructor for Country class
     * @param code country code
     * @param name name of the country
     * @param continent continent of the country
     * @param region region of the country
     * @param capital capital city of the country
     * @param population population of the country
     */
    public Country(String code, String name, String continent, String region, String capital, int population)
    {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.capital = capital;
        this.population = population;
    }

    /**
     * Country code
     */
    private String code;

    /**
     * Name of the country
     */
    private String name;

    /**
     * Continent of the country
     */
    private String continent;

    /**
     * Region of the country
     */
    private String region;

    /**
     * Population of the country
     */
    private int population;

    /**
     * Capital city of the country
     */
    private String capital;

    /**
     * Get country code for the country
     */
    public String getCode() {
        return code;
    }

    /**
     * Get name of the country
     */
    public String getName() {
        return name;
    }

    /**
     * Get continent of the country
     */
    public String getContinent() {
        return continent;
    }

    /**
     * Get region of the country
     */
    public String getRegion() {
        return region;
    }

    /**
     * Get population of the country
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Get capital city of the country
     */
    public String getCapital() {
        return capital;
    }

    /**
     * ToString for Country
     */
    @Override
    public String toString() {
        return "Country{" +
                "code=" + code +
                ", name=" + name +
                ", continent=" + continent +
                ", region=" + region +
                ", population=" + population +
                ", capital=" + capital +
                "}";
    }
}
