package com.napier.sem;

import java.math.BigInteger;

/**
 * Represents a Population
 */
public class Population
{

    /**
     * Name of the continent/region/country
     */
    private String area;

    /**
     * Population of the continent/region/country
     */
    private BigInteger population;

    /**
     * Urban population of the continent/region/country
     */
    private BigInteger urban;

    /**
     * Urban population percentage of the continent/region/country
     */
    private float urbanPercentage;

    /**
     * Rural population of the continent/region/country
     */
    private BigInteger rural;

    /**
     * Rural population percentage of the continent/region/country
     */
    private float ruralPercentage;

    /**
     * Constructor for Population
     * @param area Area Name
     * @param population Total Population
     * @param urban Urban Population
     * @param urbanPercentage Urban Percentage
     * @param rural Rural Population
     * @param ruralPercentage Rural Percentage
     */
    public Population(String area, BigInteger population, BigInteger urban, float urbanPercentage, BigInteger rural, float ruralPercentage)
    {
        this.area = area;
        this.population = population;
        this.urban = urban;
        this.urbanPercentage = urbanPercentage;
        this.rural = rural;
        this.ruralPercentage = ruralPercentage;
    }

    /**
     * Constructor for Population, for language reports
     * @param name Language Name
     * @param population Total Population of speakers
     * @param percentage Percentage of world population
     */
    public Population(String name, BigInteger population, float percentage)
    {
        this.area = name;
        this.population = population;
        this.urbanPercentage = percentage;
    }

    /**
     * Constructor for Population, for population of an area report
     * @param name Area Name
     * @param population Total Population of area
     */
    public Population(String name, BigInteger population)
    {
        this.area = name;
        this.population = population;
    }

    /**
     * Get name of the continent/region/country
     */
    public String getArea() {
        return area;
    }

    /**
     * Get population of the continent/region/country
     */
    public BigInteger getPopulation() {
        return population;
    }

    /**
     * Get urban population of the continent/region/country
     */
    public BigInteger getUrban() {
        return urban;
    }

    /**
     * Get urban population percentage of the continent/region/country
     */
    public float getUrbanPercentage() {
        return urbanPercentage;
    }

    /**
     * Get rural population of the continent/region/country
     */
    public BigInteger getRural() {
        return rural;
    }

    /**
     * Get rural population percentage of the continent/region/country
     */
    public float getRuralPercentage() {
        return ruralPercentage;
    }

    /**
     * ToString for population
     */
    @Override
    public String toString()
    {
        return "Population{" +
                "area=" + area +
                ", population=" + population +
                ", urban=" + urban +
                ", urbanPercentage=" + urbanPercentage + "%" +
                ", rural=" + rural +
                ", ruralPercentage=" + ruralPercentage + "%" +
                '}';
    }
}
