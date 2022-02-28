package com.napier.sem;

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

        a.populationRuralUrban("Continent","Asia");

        a.populationRuralUrban("Region","Caribbean");

        a.populationRuralUrban("Country","France");

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
     * Gets the population data of a given continent/region/country
     * @param areaType Area Type
     * @param area Area Name
     * @return The name of the area, its total population, urban population and percentage, and rural population and percentage
     */
    public void populationRuralUrban(String areaType, String area)
    {

        //String used for the area type in SQL Statement
        String areaTyped;

        //If the area type is country
        if(areaType == "Country")
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
                    "SELECT b.area, b.pop, a.urban, (a.urban/b.pop)*100 as urbanPercent,(b.pop-a.urban) AS rural, ((b.pop-a.urban)/b.pop)*100 as ruralPercent "
                            + "FROM (SELECT SUM(Population) AS urban, CountryCode FROM city GROUP BY CountryCode) AS a "
                            + "JOIN " +
                            "(SELECT Code, " + areaTyped + " AS area, Population AS pop From country) AS b " +
                            "ON a.CountryCode = b.Code " +
                            "WHERE b.area = '" + area + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Population> populations = new ArrayList<Population>();
            while (rset.next())
            {
                Population pop = new Population(rset.getString("b.area"),
                                                rset.getInt("b.pop"),
                                                rset.getInt("a.urban"),
                                                rset.getFloat("urbanPercent"),
                                                rset.getInt("rural"),
                                                rset.getFloat("ruralPercent"));
                populations.add(pop);
            }

            //Output the area population info
            System.out.println(populations.get(0).toString());
            //This is the temporary output for now
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

}
