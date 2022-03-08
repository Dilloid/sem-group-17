package com.napier.sem;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {

        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Create new ArrayList of Country objects and fill it with the requested report
        ArrayList<Country> countryList = a.topNCountriesByPopulation("Continent", "North America", 10);

        // For each Country in the list, print the details of the object using its custom toString method
        for (Country c : countryList) {
            System.out.println(c.toString());
        }

        // Generate report of the population of Asia
        a.populationRuralUrban("Continent");

        // Generate report of the population of the Caribbean
        a.populationRuralUrban("Region");

        // Generate report of the population of Asia
        a.populationRuralUrban("Country");

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
     * @param area Name of area
     * @param input Country
     * @return Arraylist of countries and their relevant info
     */
    public ArrayList<Country> countriesByPopulation(String area, String input) {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Capital, Population " +
                    "FROM country " +
                    "WHERE " + area + "='" + input + "' " +
                    "ORDER BY Population DESC";

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
                        rset.getInt("Population"),
                        rset.getInt("Capital")
                ));
            }

            // Return the ArrayList of Country objects
            return countryList;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate report.");
            return null;
        }
    }

    /**
     * Gets a list of N countries in an area in order of population
     * @param area Name of area
     * @param input Country
     * @param n Number to display
     * @return Arraylist of countries and their relevant info
     */
    public ArrayList<Country> topNCountriesByPopulation(String area, String input, int n) {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Capital, Population " +
                            "FROM country " +
                            "WHERE " + area + "='" + input + "' " +
                            "ORDER BY Population DESC " +
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
                        rset.getInt("Population"),
                        rset.getInt("Capital")
                ));
            }

            // Return the ArrayList of Country objects
            return countryList;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate report.");
            return null;
        }
    }
    /**
     * Gets the population data of a given continent/region/country
     * @param areaType Area Type
     * @return ArrayList of the name of the area, its total population, urban population and percentage, and rural population and percentage
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
            System.out.println(String.format("%-25s %-12s %-12s %-12s %-12s %-12s", areaType, "Population", "Urban pop", "Percentage","Rural pop","Percentage"));

            //Output population info for each area
            for(Population n : populations)
            {
                System.out.println(String.format("%-25s %-12s %-12s %-12s %-12s %-12s", n.getArea(), n.getPopulation(), n.getUrban(), n.getUrbanPercentage()+"%", n.getRural(), n.getRuralPercentage()+"%"));
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
