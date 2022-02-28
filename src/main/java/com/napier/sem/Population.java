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
     * Urban population percentage of the continent/region/country
     */
    private float urban;

    /**
     * Rural population percentage of the continent/region/country
     */
    private float rural;

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
     * Get urban population percentage of the continent/region/country
     */
    public float getUrban() {
        return urban;
    }

    /**
     * Get rural population percentage of the continent/region/country
     */
    public float getRural() {
        return rural;
    }

    /**
     * ToString for population
     */
    @Override
    public String toString() {
        return "Population{" +
                "area='" + area +
                ", population=" + population +
                ", urban=" + urban + "%" +
                ", rural=" + rural + "%" +
                '}';
    }
}
