package com.napier.sem;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;

/**
 * Where all the magic happens
 * @author Dillon Aitken
 * @author Jared Carr
 * @author Ross Muir
 * @author James Piper
 */
public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        if(args.length < 1)
        {a.connect("localhost:33060",0);}
        else
        {a.connect("world-db:3306", 30000);}

        // ========================================================================================

        // Generate report of the countries in the world by population
        ArrayList<Country> worldCountries = a.worldCountriesByPopulation();
        a.printCountries(worldCountries);

        // Generate report of the countries in Europe by population
        ArrayList<Country> europeCountries = a.countriesByPopulation("Continent", "Europe");
        a.printCountries(europeCountries);

        // Generate report of the countries in Eastern Africa by population
        ArrayList<Country> eastAfricaCountries = a.countriesByPopulation("Region", "Eastern Africa");
        a.printCountries(eastAfricaCountries);

        // ========================================================================================

        // Generate report of the top 10 most populous countries in the world
        ArrayList<Country> worldTopCountries = a.topNWorldCountriesByPopulation(10);
        a.printCountries(worldTopCountries);

        // Generate report of the top 10 most populous countries in North America
        ArrayList<Country> northAmericaTopCountries = a.topNCountriesByPopulation("Continent", "North America", 10);
        a.printCountries(northAmericaTopCountries);

        // Generate report of the top 10 most populous countries in Southeast Asia
        ArrayList<Country> southEastAsiaTopCountries = a.topNCountriesByPopulation("Region", "Southeast Asia", 10);
        a.printCountries(southEastAsiaTopCountries);

        // ========================================================================================

        // Generate report of the cities in the world by population
        ArrayList<City> worldCities = a.worldCitiesByPopulation();
        a.printCities(worldCities);

        // Generate report of the cities in Oceania by population
        ArrayList<City> oceanicCities = a.citiesByPopulation("Continent", "Oceania");
        a.printCities(oceanicCities);

        // Generate report of the cities in British Islands by population
        ArrayList<City> britishCities =a.citiesByPopulation("Region", "British Islands");
        a.printCities(britishCities);

        // Generate report of the cities in France by population
        ArrayList<City> franceCities = a.citiesByPopulation("Country", "France");
        a.printCities(franceCities);

        // Generate report of the cities in Scotland by population
        ArrayList<City> scotlandCities = a.citiesByPopulation("District", "Scotland");
        a.printCities(scotlandCities);

        // ========================================================================================

        // Generate report of the top 10 most populous cities in the world by population
        ArrayList<City> worldTopCities = a.topNWorldCitiesByPopulation(10);
        a.printCities(worldTopCities);

        // Generate report of the top 10 most populous cities in Africa by population
        ArrayList<City> africaTopCities = a.topNCitiesByPopulation("Continent", "Africa", 10);
        a.printCities(africaTopCities);

        // Generate report of the top 10 most populous cities in Western Europe by population
        ArrayList<City> westEUTopCities = a.topNCitiesByPopulation("Region", "Western Europe", 10);
        a.printCities(westEUTopCities);

        // Generate report of the top 10 most populous cities in Africa by population
        ArrayList<City> japanTopCities = a.topNCitiesByPopulation("Country", "Japan", 10);
        a.printCities(japanTopCities);

        // Generate report of the top 10 most populous cities in Florida by population
        ArrayList<City> floridaTopCities = a.topNCitiesByPopulation("District", "Florida", 10);
        a.printCities(floridaTopCities);

        // ========================================================================================

        // Generate report of the population of Asia
        ArrayList<Population> continentPops = a.populationRuralUrban("Continent");
        a.printPopulations(continentPops);

        // Generate report of the population of the Caribbean
        ArrayList<Population> regionPops = a.populationRuralUrban("Region");
        a.printPopulations(regionPops);

        // Generate report of the population of Asia
        ArrayList<Population> countryPops = a.populationRuralUrban("Country");
        a.printPopulations(countryPops);

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
    public void connect(String location, int delay)
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
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://"+ location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "password");
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
     * Prints list of cities
     * @param cities cities to be printed
     */
    public void printCities(ArrayList<City> cities)
    {
        if(cities == null)
        {
            System.out.println("No cities list has been given");
        }
        else if(cities.size() < 1)
        {
            System.out.println("No cities to print");
        }
        else
        {
            //Output the area population info headers
            System.out.println(String.format("%-25s %-30s %-25s %-12s", "City", "Country", "District", "Population"));

            //Output population info for each area
            for (City n : cities)
            {
                System.out.println(String.format("%-25s %-30s %-25s %-12s", n.getName(), n.getCountry(), n.getDistrict(), n.getPopulation()));
            }
            System.out.println("");
        }
    }

    /**
     * Prints list of countries
     * @param countries countries to be printed
     */
    public void printCountries(ArrayList<Country> countries)
    {
        if(countries == null)
        {
            System.out.println("No countries list has been given");
        }
        else if(countries.size() < 1)
        {
            System.out.println("No countries to print");
        }
        else
        {
            //Output the area population info headers
            System.out.println(String.format("%-8s %-30s %-15s %-25s %-12s %-25s", "Code", "Name", "Continent", "Region", "Population", "Capital"));

            //Output population info for each area
            for (Country c : countries)
            {
                System.out.println(String.format("%-8s %-30s %-15s %-25s %-12s %-25s", c.getCode(), c.getName(), c.getContinent(), c.getRegion(), c.getPopulation(), c.getCapitalName()));
            }
            System.out.println("");
        }
    }

    /**
     * Prints list of populations
     * @param populations populations to be printed
     */
    public void printPopulations(ArrayList<Population> populations)
    {
        if(populations == null)
        {
            System.out.println("No populations list has been given");
        }
        else if(populations.size() < 1)
        {
            System.out.println("No populations to print");
        }
        else
        {
            //Output the area population info headers
            System.out.println(String.format("%-30s %-12s %-12s %-12s %-12s %-12s", "Location", "Population", "Urban pop", "Percentage", "Rural pop", "Percentage"));

            //Output population info for each area
            for (Population n : populations)
            {
                System.out.println(String.format("%-30s %-12s %-12s %-12s %-12s %-12s", n.getArea(), n.getPopulation(), n.getUrban(), n.getUrbanPercentage() + "%", n.getRural(), n.getRuralPercentage() + "%"));
            }

            System.out.println("");
        }
    }

    /**
     * Prints list of capital cities
     * @param cities Capital cities to be printed
     */
    public void printCapitalCities(ArrayList<CapitalCity> cities)
    {
        if(cities == null)
        {
            System.out.println("No capital cities list has been given");
        }
        else if(cities.size() < 1)
        {
            System.out.println("No capital cities to print");
        }
        else
        {
            //Output the area population info headers
            System.out.println(String.format("%-25s %-30s %-25s %-12s", "City", "Country", "Population"));

            //Output population info for each area
            for (CapitalCity n : cities)
            {
                System.out.println(String.format("%-25s %-30s %-12s", n.getName(), n.getCountry(), n.getPopulation()));
            }
            System.out.println("");
        }
    }

    /**
     * Gets a list of all countries in an area in order of population
     * @param areaType Type of area
     * @param area Name of area
     * @return Arraylist of countries and their relevant info
     */
    public ArrayList<Country> countriesByPopulation(String areaType, String area)
    {
        // If no areaType provided
        if (areaType == null)
        {
            System.out.println("No area type specified");
            return null;
        }
        // If areaType is invalid
        else if (!areaType.equals("Continent") && !areaType.equals("Region") && !areaType.equals("Country"))
        {
            System.out.println("Area type is not valid");
            return null;
        }
        // If areaType is valid
        else
        {
            // If no area name provided
            if (area == null)
            {
                System.out.println("No area name specified");
                return null;
            }
            // If area is also valid
            else
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
                                    "WHERE a." + areaType + " = '" + area + "' " +
                                    "ORDER BY a.Population DESC ";

                    // Execute SQL statement and return rows as a ResultSet object
                    ResultSet rset = stmt.executeQuery(strSelect);

                    // Create a temporary ArrayList to store incoming results
                    ArrayList<Country> countryList = new ArrayList<>();

                    // While there is still a next row in the ResultSet
                    while (rset.next())
                    {
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

                    return countryList;
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                    System.out.println("Failed to generate report.");
                    return null;
                }
            }
        }
    }

    /**
     * Gets a list of all countries in the world in order of population
     * @return Countries and their relevant info
     */
    public ArrayList<Country> worldCountriesByPopulation()
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
            while (rset.next())
            {
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
     * Gets a list of the top N countries in an area in order of population
     * @param areaType Type of area
     * @param area Name of area
     * @param n Number to display
     * @return Countries and their relevant info
     */
    public ArrayList<Country> topNCountriesByPopulation(String areaType, String area, int n)
    {
        // If no areaType provided
        if (areaType == null)
        {
            System.out.println("No area type specified");
            return null;
        }
        // If areaType is invalid
        else if (!areaType.equals("Continent") && !areaType.equals("Region") && !areaType.equals("Country"))
        {
            System.out.println("Area type is not valid");
            return null;
        }
        // If areaType is valid
        else
        {
            // If no area name provided
            if (area == null)
            {
                System.out.println("No area name specified");
                return null;
            }
            // If area is also valid
            else
            {
                // If provided N is less than or equal to zero
                if (n <= 0)
                {
                    System.out.println("N equal to or below 0");
                    return null;
                }
                else
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
                                        "WHERE a." + areaType + "='" + area + "' " +
                                        "ORDER BY a.Population DESC " +
                                        "LIMIT " + n;

                        // Execute SQL statement and return rows as a ResultSet object
                        ResultSet rset = stmt.executeQuery(strSelect);

                        // Create a temporary ArrayList to store incoming results
                        ArrayList<Country> countryList = new ArrayList<>();

                        // While there is still a next row in the ResultSet
                        while (rset.next())
                        {
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

                        return countryList;
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                        System.out.println("Failed to generate report.");
                        return null;
                    }
                }
            }
        }
    }

    /**
     * Gets a list of the top N countries in the world in order of population
     * @param n Number to display
     * @return Countries and their relevant info
     */
    public ArrayList<Country> topNWorldCountriesByPopulation(int n)
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
            while (rset.next())
            {
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
     * @return Name of the area, its total population, urban population and percentage, and rural population and percentage
     */
    public ArrayList<Population> populationRuralUrban(String areaType)
    {
        // No argument
        if(areaType == null)
        {
            System.out.println("No area type specified");
            return null;
        }
        // Not correct area type
        else if(!areaType.equals("Continent") && !areaType.equals("Region") && !areaType.equals("Country"))
        {
            System.out.println("Area type is not valid");
            return null;
        }
        // Valid area type
        else
        {
            //String used for the area type in SQL Statement
            String areaTyped;

            //If the area type is country
            if (areaType.equals("Country"))
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

                return populations;
            }

            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("Failed to generate report.");
                return null;
            }
        }
    }

    /**
     * Gets a list of all cities in an area in order of population
     * @param areaType Area Type
     * @param area Name of Area
     * @return Cities and their relevant info
     */
    public ArrayList<City> citiesByPopulation(String areaType, String area)
    {
        //No argument
        if(areaType == null)
        {
            System.out.println("No area type has been specified");
            return null;
        }
        else if(area == null)
        {
            System.out.println("No area has been specified");
            return null;
        }
        //Incorrect area type
        else if(!areaType.equals("Continent") && !areaType.equals("Region") && !areaType.equals("Country") && !areaType.equals("District"))
        {
            System.out.println("Invalid Area type");
            return null;
        }
        //Area type is valid
        else
        {
            //String used for the area type in SQL Statement
            String areaTyped;

            //If the area type is country
            if (areaType.equals("Country"))
            {
                //Use the column called "Name"
                areaTyped = "a.Name";
            }
            //If the area type is district
            else if (areaType.equals("District"))
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

                return cities;

            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("Failed to generate report.");
                return null;
            }
        }
    }

    /**
     * Gets a list of all cities in the world in order of population
     * @return Cities and their relevant info
     */
    public ArrayList<City> worldCitiesByPopulation()
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

            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate report.");
            return null;
        }
    }

    /**
     * Gets a list of the top N cities in an area in order of population
     * @param areaType Area Type
     * @param area Name of Area
     * @param num Number to display
     * @return Cities and their relevant info
     */
    public ArrayList<City> topNCitiesByPopulation(String areaType, String area, int num)
    {
        if (areaType == null)
        {
            System.out.println("No area type specified");
            return null;
        }
        else if (!areaType.equals("Continent") && !areaType.equals("Region") && !areaType.equals("Country") && !areaType.equals("District"))
        {
            System.out.println("Area type is not valid");
            return null;
        }
        else if (area == null)
        {
            System.out.println("No area specified");
            return null;
        }
        else if (num <= 0)
        {
            System.out.println("Number must be greater than zero");
            return null;
        }
        else
        {


            //String used for the area type in SQL Statement
            String areaTyped;

            //If the area type is country
            if (areaType.equals("Country")) {
                //Use the column called "Name"
                areaTyped = "a.Name";
            }
            //If the area type is district
            else if (areaType.equals("District")) {
                areaTyped = "b.District";
            }
            //Otherwise
            else {
                //The name of the area type is used
                areaTyped = "a." + areaType;
            }

            try {
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
                while (rset.next()) {
                    City city = new City(rset.getString("b.Name"),
                            rset.getString("a.Name"),
                            rset.getString("b.District"),
                            rset.getInt("b.Population"));
                    cities.add(city);
                }

                return cities;
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("Failed to generate report.");
                return null;
            }
        }
    }

    /**
     * Gets a list of the top N cities in the world in order of population
     * @param num Number to display
     * @return Cities and their relevant info
     */
    public ArrayList<City> topNWorldCitiesByPopulation(int num)
    {
        if (num <= 0)
        {
            System.out.println("Number must be greater than zero");
            return null;
        }
        else
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
                while (rset.next()) {
                    City city = new City(rset.getString("b.Name"),
                            rset.getString("a.Name"),
                            rset.getString("b.District"),
                            rset.getInt("b.Population"));
                    cities.add(city);
                }

                return cities;
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("Failed to generate report.");
                return null;
            }
        }
    }
}
