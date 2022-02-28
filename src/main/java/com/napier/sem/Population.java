package com.napier.sem;

public class Population {

    /**
     * Name of the continent/region/country
     */
    private String area;

    /**
     * Population of the continent/region/country
     */
    private int population;

    /**
     * Urban population of the continent/region/country
     */
    private int urban;

    /**
     * Urban population percentage of the continent/region/country
     */
    private float urbanPercentage;

    /**
     * Rural population of the continent/region/country
     */
    private float rural;

    /**
     * Rural population percentage of the continent/region/country
     */
    private float ruralPercentage;

    /**
     * Constructor for population
     */
    public Population(String area, int population, int urban, float urbanPercentage, float rural, float ruralPercentage) {
        this.area = area;
        this.population = population;
        this.urban = urban;
        this.urbanPercentage = urbanPercentage;
        this.rural = rural;
        this.ruralPercentage = ruralPercentage;
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
    public int getPopulation() {
        return population;
    }

    /**
     * Get urban population of the continent/region/country
     */
    public float getUrban() {
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
    public float getRural() {
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
    public String toString() {
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
