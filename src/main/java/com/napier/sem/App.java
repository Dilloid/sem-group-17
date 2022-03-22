package com.napier.sem;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;

/**
 * Where all the magic happens
 */
public class App
{
    public static void main(String[] args)
    {

        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // ========================================================================================

        // Generate report of the countries in the world by population
        a.worldCountriesByPopulation();

        // Generate report of the countries in Europe by population
        a.countriesByPopulation("Continent", "Europe");

        // Generate report of the countries in Eastern Africa by population
        a.countriesByPopulation("Region", "Eastern Africa");

        // ========================================================================================

        // Generate report of the top 10 most populous countries in the world
        a.topNWorldCountriesByPopulation(10);

        // Generate report of the top 10 most populous countries in North America
        a.topNCountriesByPopulation("Continent", "North America", 10);

        // Generate report of the top 10 most populous countries in Southeast Asia
        a.topNCountriesByPopulation("Region", "Southeast Asia", 10);

        // ========================================================================================

        // Generate report of the cities in the world by population
        a.worldCitiesByPopulation();

        // Generate report of the cities in France by population
        a.citiesByPopulation("Continent", "Oceania");

        // Generate report of the cities in Scotland by population
        a.citiesByPopulation("Region", "British Islands");

        // Generate report of the cities in France by population
        a.citiesByPopulation("Country", "France");

        // Generate report of the cities in Scotland by population
        a.citiesByPopulation("District", "Scotland");

        // ========================================================================================

        // Generate report of the top 10 most populous cities in the world by population
        a.topNWorldCitiesByPopulation(10);

        // Generate report of the top 10 most populous cities in Africa by population
        a.topNCitiesByPopulation("Continent", "Africa", 10);

        // Generate report of the top 10 most populous cities in Western Europe by population
        a.topNCitiesByPopulation("Region", "Western Europe", 10);

        // Generate report of the top 10 most populous cities in Africa by population
        a.topNCitiesByPopulation("Country", "Japan", 10);

        // Generate report of the top 10 most populous cities in Western Europe by population
        a.topNCitiesByPopulation("District", "Florida", 10);

        // ========================================================================================

        // Generate report of the population of Asia
        a.populationRuralUrban("Continent");

        // Generate report of the population of the Caribbean
        a.populationRuralUrban("Region");

        // Generate report of the population of Asia
        a.populationRuralUrban("Country");

        // ========================================================================================

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for world-db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://world-db:3306/world?useSSL=false", "root", "password");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Gets a list of all countries in an area in order of population
     * @param area Type of area
     * @param input Name of area
     * @return Arraylist of countries and their relevant info
     */
    public void countriesByPopulation(String area, String input)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT a.Code as Code, a.Name as Name, a.Continent as Continent, a.Region as Region, b.Name as Capital, a.Population as Population " +
                            "FROM country AS a " +
                            "JOIN city AS b " +
                            "ON a.Capital = b.ID " +
                            "WHERE a." + area + " = '" + input + "' " +
                            "ORDER BY a.Population DESC ";

            // Execute SQL statement and return rows as a ResultSet object
            ResultSet rset = stmt.executeQuery(strSelect);

            // Create a temporary ArrayList to store incoming results
            ArrayList<Country> countryList = new ArrayList<>();

            // While there is still a next row in the ResultSet
            while (rset.next()) {
                // Create a new Country object and add it to the list
                countryList.add(new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        new BigInteger(rset.getString("Population")),
                        rset.getString("Capital")
                ));
            }

            //Output the area population info headers
            System.out.println(String.format("%-8s %-30s %-15s %-25s %-12s %-25s", "Code", "Name", "Continent", "Region","Population","Capital"));

            //Output population info for each area
            for(Country c : countryList)
            {
                System.out.println(String.format("%-8s %-30s %-15s %-25s %-12s %-25s", c.getCode(), c.getName(), c.getContinent(), c.getRegion(), c.getPopulation(), c.getCapitalName()));
            }
            System.out.println("");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate report.");
        }
    }

    /**
     * Gets a list of all countries in the world in order of population
     * @return Countries and their relevant info
     */
    public void worldCountriesByPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT a.Code as Code, a.Name as Name, a.Continent as Continent, a.Region as Region, b.Name as Capital, a.Population as Population " +
                            "FROM country AS a " +
                            "JOIN city AS b " +
                            "ON a.Capital = b.ID " +
                            "ORDER BY a.Population DESC ";

            // Execute SQL statement and return rows as a ResultSet object
            ResultSet rset = stmt.executeQuery(strSelect);

            // Create a temporary ArrayList to store incoming results
            ArrayList<Country> countryList = new ArrayList<>();

            // While there is still a next row in the ResultSet
            while (rset.next()) {
                // Create a new Country object and add it to the list
                countryList.add(new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        new BigInteger(rset.getString("Population")),
                        rset.getString("Capital")
                ));
            }

            //Output the area population info headers
            System.out.println(String.format("%-8s %-30s %-15s %-25s %-12s %-25s", "Code", "Name", "Continent", "Region","Population","Capital"));

            //Output population info for each area
            for(Country c : countryList)
            {
                System.out.println(String.format("%-8s %-30s %-15s %-25s %-12s %-25s", c.getCode(), c.getName(), c.getContinent(), c.getRegion(), c.getPopulation(), c.getCapitalName()));
            }
            System.out.println("");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate report.");
        }
    }

    /**
     * Gets a list of the top N countries in an area in order of population
     * @param area Type of area
     * @param input Name of area
     * @param n Number to display
     * @return Countries and their relevant info
     */
    public void topNCountriesByPopulation(String area, String input, int n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT a.Code as Code, a.Name as Name, a.Continent as Continent, a.Region as Region, b.Name as Capital, a.Population as Population " +
                    "FROM country AS a " +
                    "JOIN city AS b " +
                    "ON a.Capital = b.ID " +
                    "WHERE a." + area + "='" + input + "' " +
                    "ORDER BY a.Population DESC " +
                    "LIMIT " + n;

            // Execute SQL statement and return rows as a ResultSet object
            ResultSet rset = stmt.executeQuery(strSelect);

            // Create a temporary ArrayList to store incoming results
            ArrayList<Country> countryList = new ArrayList<>();

            // While there is still a next row in the ResultSet
            while (rset.next()) {
                // Create a new Country object and add it to the list
                countryList.add(new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        new BigInteger(rset.getString("Population")),
                        rset.getString("Capital")
                ));
            }

            //Output the area population info headers
            System.out.println(String.format("%-8s %-30s %-15s %-25s %-12s %-25s", "Code", "Name", "Continent", "Region","Population","Capital"));

            //Output population info for each area
            for(Country c : countryList)
            {
                System.out.println(String.format("%-8s %-30s %-15s %-25s %-12s %-25s", c.getCode(), c.getName(), c.getContinent(), c.getRegion(), c.getPopulation(), c.getCapitalName()));
            }
            System.out.println("");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate report.");
        }
    }

    /**
     * Gets a list of the top N countries in the world in order of population
     * @param n Number to display
     * @return Countries and their relevant info
     */
    public void topNWorldCountriesByPopulation(int n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT a.Code as Code, a.Name as Name, a.Continent as Continent, a.Region as Region, b.Name as Capital, a.Population as Population " +
                            "FROM country AS a " +
                            "JOIN city AS b " +
                            "ON a.Capital = b.ID " +
                            "ORDER BY a.Population DESC " +
                            "LIMIT " + n;

            // Execute SQL statement and return rows as a ResultSet object
            ResultSet rset = stmt.executeQuery(strSelect);

            // Create a temporary ArrayList to store incoming results
            ArrayList<Country> countryList = new ArrayList<>();

            // While there is still a next row in the ResultSet
            while (rset.next()) {
                // Create a new Country object and add it to the list
                countryList.add(new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        new BigInteger(rset.getString("Population")),
                        rset.getString("Capital")
                ));
            }

            //Output the area population info headers
            System.out.println(String.format("%-8s %-30s %-15s %-25s %-12s %-25s", "Code", "Name", "Continent", "Region","Population","Capital"));

            //Output population info for each area
            for(Country c : countryList)
            {
                System.out.println(String.format("%-8s %-30s %-15s %-25s %-12s %-25s", c.getCode(), c.getName(), c.getContinent(), c.getRegion(), c.getPopulation(), c.getCapitalName()));
            }
            System.out.println("");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate report.");
        }
    }

    /**
     * Gets the population data of a given continent/region/country
     * @param areaType Area Type
     * @return Name of the area, its total population, urban population and percentage, and rural population and percentage
     */
    public void populationRuralUrban(String areaType)
    {

        //String used for the area type in SQL Statement
        String areaTyped;

        //If the area type is country
        if(areaType.equals("Country"))
        {
            //Use the column called "Name"
            areaTyped = "Name";
        }
        //Otherwise
        else
        {
            //The name of the area type is used
            areaTyped = areaType;
        }

        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT d.area, d.pop, d.urban, (d.urban/d.pop)*100 as urbanPercent,(d.pop-d.urban) AS rural, ((d.pop-d.urban)/d.pop)*100 as ruralPercent "
                            + "FROM (SELECT SUM(c.urban) AS urban, SUM(c.pop) AS pop, c.area AS area "
                            + "FROM (SELECT a.urban AS urban, b.pop AS pop, b.area AS area "
                            + "FROM (SELECT SUM(Population) AS urban, CountryCode FROM city GROUP BY CountryCode) AS a "
                            + "JOIN (SELECT Code, " + areaTyped + " AS area, Population AS pop From country) AS b "
                            + "ON a.CountryCode = b.Code) as c "
                            + "GROUP BY c.area) as d ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Population> populations = new ArrayList<Population>();
            while (rset.next())
            {
                Population pop = new Population(rset.getString("d.area"),
                                                new BigInteger(rset.getString("d.pop")),
                                                new BigInteger(rset.getString("d.urban")),
                                                rset.getFloat("urbanPercent"),
                                                new BigInteger(rset.getString("rural")),
                                                rset.getFloat("ruralPercent"));
                populations.add(pop);
            }

            //Output the area population info headers
            System.out.println(String.format("%-30s %-12s %-12s %-12s %-12s %-12s", areaType, "Population", "Urban pop", "Percentage","Rural pop","Percentage"));

            //Output population info for each area
            for(Population n : populations)
            {
                System.out.println(String.format("%-30s %-12s %-12s %-12s %-12s %-12s", n.getArea(), n.getPopulation(), n.getUrban(), n.getUrbanPercentage()+"%", n.getRural(), n.getRuralPercentage()+"%"));
            }
            System.out.println("");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate report.");
        }
    }

    /**
     * Gets a list of all cities in an area in order of population
     * @param areaType Area Type
     * @param area Name of Area
     * @return Cities and their relevant info
     */
    public void citiesByPopulation(String areaType, String area)
    {

        //String used for the area type in SQL Statement
        String areaTyped;

        //If the area type is country
        if(areaType.equals("Country"))
        {
            //Use the column called "Name"
            areaTyped = "a.Name";
        }
        //If the area type is district
        else if(areaType.equals("District"))
        {
            areaTyped = "b.District";
        }
        //Otherwise
        else
        {
            //The name of the area type is used
            areaTyped = "a." + areaType;
        }

        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT b.Name, a.Name, b.District, b.Population " +
                            "FROM (SELECT * FROM city ORDER BY Population DESC) AS b " +
                            "JOIN country AS a " +
                            "ON b.CountryCode = a.Code " +
                            "WHERE " + areaTyped + " = '" + area + "' " +
                            "ORDER BY b.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City(rset.getString("b.Name"),
                        rset.getString("a.Name"),
                        rset.getString("b.District"),
                        rset.getInt("b.Population"));
                cities.add(city);
            }

            //Output the area population info headers
            System.out.println(String.format("%-25s %-30s %-25s %-12s", "City", "Country", "District","Population"));

            //Output population info for each area
            for(City n : cities)
            {
                System.out.println(String.format("%-25s %-30s %-25s %-12s", n.getName(), n.getCountry(), n.getDistrict(), n.getPopulation()));
            }
            System.out.println("");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate report.");
        }
    }

    /**
     * Gets a list of all cities in the world in order of population
     * @return Cities and their relevant info
     */
    public void worldCitiesByPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT b.Name, a.Name, b.District, b.Population " +
                            "FROM (SELECT * FROM city ORDER BY Population DESC) AS b " +
                            "JOIN country AS a " +
                            "ON b.CountryCode = a.Code " +
                            "ORDER BY b.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City(rset.getString("b.Name"),
                        rset.getString("a.Name"),
                        rset.getString("b.District"),
                        rset.getInt("b.Population"));
                cities.add(city);
            }

            //Output the area population info headers
            System.out.println(String.format("%-25s %-30s %-25s %-12s", "City", "Country", "District","Population"));

            //Output population info for each area
            for(City n : cities)
            {
                System.out.println(String.format("%-25s %-30s %-25s %-12s", n.getName(), n.getCountry(), n.getDistrict(), n.getPopulation()));
            }
            System.out.println("");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate report.");
        }
    }

    /**
     * Gets a list of the top N cities in an area in order of population
     * @param areaType Area Type
     * @param area Name of Area
     * @param num Number to display
     * @return Cities and their relevant info
     */
    public void topNCitiesByPopulation(String areaType, String area, int num)
    {

        //String used for the area type in SQL Statement
        String areaTyped;

        //If the area type is country
        if(areaType.equals("Country"))
        {
            //Use the column called "Name"
            areaTyped = "a.Name";
        }
        //If the area type is district
        else if(areaType.equals("District"))
        {
            areaTyped = "b.District";
        }
        //Otherwise
        else
        {
            //The name of the area type is used
            areaTyped = "a." + areaType;
        }

        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT b.Name, a.Name, b.District, b.Population " +
                            "FROM (SELECT * FROM city ORDER BY Population DESC) AS b " +
                            "JOIN country AS a " +
                            "ON a.Code = b.CountryCode " +
                            "WHERE " + areaTyped + " = '" + area + "' " +
                            "ORDER BY b.Population DESC " +
                            "LIMIT " + num;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City(rset.getString("b.Name"),
                        rset.getString("a.Name"),
                        rset.getString("b.District"),
                        rset.getInt("b.Population"));
                cities.add(city);
            }

            //Output the area population info headers
            System.out.println(String.format("%-25s %-30s %-25s %-12s", "City", "Country", "District","Population"));

            //Output population info for each area
            for(City n : cities)
            {
                System.out.println(String.format("%-25s %-30s %-25s %-12s", n.getName(), n.getCountry(), n.getDistrict(), n.getPopulation()));
            }
            System.out.println("");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate report.");
        }
    }

    /**
     * Gets a list of the top N cities in the world in order of population
     * @param num Number to display
     * @return Cities and their relevant info
     */
    public void topNWorldCitiesByPopulation(int num)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT b.Name, a.Name, b.District, b.Population " +
                            "FROM (SELECT * FROM city ORDER BY Population DESC) AS b " +
                            "JOIN country AS a " +
                            "ON a.Code = b.CountryCode " +
                            "ORDER BY b.Population DESC " +
                            "LIMIT " + num;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City(rset.getString("b.Name"),
                        rset.getString("a.Name"),
                        rset.getString("b.District"),
                        rset.getInt("b.Population"));
                cities.add(city);
            }

            //Output the area population info headers
            System.out.println(String.format("%-25s %-30s %-25s %-12s", "City", "Country", "District","Population"));

            //Output population info for each area
            for(City n : cities)
            {
                System.out.println(String.format("%-25s %-30s %-25s %-12s", n.getName(), n.getCountry(), n.getDistrict(), n.getPopulation()));
            }
            System.out.println("");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate report.");
        }
    }

}
