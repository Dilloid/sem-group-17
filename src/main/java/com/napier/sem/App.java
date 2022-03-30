package com.napier.sem;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.io.*;

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
        a.printCountries(worldCountries, "01_World_Countries_By_Population.md");

        // Generate report of the countries in Europe by population
        ArrayList<Country> europeCountries = a.countriesByPopulation("Continent", "Europe");
        a.printCountries(europeCountries, "02_European_Countries_By_Population.md");

        // Generate report of the countries in Eastern Africa by population
        ArrayList<Country> eastAfricaCountries = a.countriesByPopulation("Region", "Eastern Africa");
        a.printCountries(eastAfricaCountries, "03_African_Countries_By_Population.md");

        // ========================================================================================

        // Generate report of the top 10 most populous countries in the world
        ArrayList<Country> worldTopCountries = a.topNWorldCountriesByPopulation(10);
        a.printCountries(worldTopCountries, "04_Top_N_World_Countries_By_Population.md");

        // Generate report of the top 10 most populous countries in North America
        ArrayList<Country> northAmericaTopCountries = a.topNCountriesByPopulation("Continent", "North America", 10);
        a.printCountries(northAmericaTopCountries, "05_Top_N_American_Countries_By_Population.md");

        // Generate report of the top 10 most populous countries in Southeast Asia
        ArrayList<Country> southEastAsiaTopCountries = a.topNCountriesByPopulation("Region", "Southeast Asia", 10);
        a.printCountries(southEastAsiaTopCountries, "06_Top_N_Asian_Countries_By_Population.md");

        // ========================================================================================

        // Generate report of the cities in the world by population
        ArrayList<City> worldCities = a.worldCitiesByPopulation();
        a.printCities(worldCities, "07_World_Cities_By_Population.md");

        // Generate report of the cities in Oceania by population
        ArrayList<City> oceanicCities = a.citiesByPopulation("Continent", "Oceania");
        a.printCities(oceanicCities, "08_Oceanic_Cities_By_Population.md");

        // Generate report of the cities in British Islands by population
        ArrayList<City> britishCities =a.citiesByPopulation("Region", "British Islands");
        a.printCities(britishCities, "09_British_Cities_By_Population.md");

        // Generate report of the cities in France by population
        ArrayList<City> franceCities = a.citiesByPopulation("Country", "France");
        a.printCities(franceCities, "10_France_Cities_By_Population.md");

        // Generate report of the cities in Scotland by population
        ArrayList<City> scotlandCities = a.citiesByPopulation("District", "Scotland");
        a.printCities(scotlandCities, "11_Scotland_Cities_By_Population.md");

        // ========================================================================================

        // Generate report of the top 10 most populous cities in the world
        ArrayList<City> worldTopCities = a.topNWorldCitiesByPopulation(10);
        a.printCities(worldTopCities, "12_Top_N_World_Cities_By_Population.md");

        // Generate report of the top 10 most populous cities in Africa
        ArrayList<City> africaTopCities = a.topNCitiesByPopulation("Continent", "Africa", 10);
        a.printCities(africaTopCities, "13_Top_N_Africa_Cities_By_Population.md");

        // Generate report of the top 10 most populous cities in Western Europe
        ArrayList<City> westEUTopCities = a.topNCitiesByPopulation("Region", "Western Europe", 10);
        a.printCities(westEUTopCities, "14_Top_N_West_EU_Cities_By_Population.md");

        // Generate report of the top 10 most populous cities in Africa
        ArrayList<City> japanTopCities = a.topNCitiesByPopulation("Country", "Japan", 10);
        a.printCities(japanTopCities, "15_Top_N_Japan_Cities_By_Population.md");

        // Generate report of the top 10 most populous cities in Florida
        ArrayList<City> floridaTopCities = a.topNCitiesByPopulation("District", "Florida", 10);
        a.printCities(floridaTopCities, "16_Top_N_Florida_Cities_By_Population.md");

        // ========================================================================================

        // Generate report of the capital cities in the world by population
        ArrayList<CapitalCity> worldCapitals = a.worldCapitalCitiesByPopulation();
        a.printCapitalCities(worldCapitals, "17_World_Capital_Cities_By_Population");

        // Generate report of the capital cities in Europe by population
        ArrayList<CapitalCity> europeanCapitals = a.capitalCitiesByPopulation("Continent", "Europe");
        a.printCapitalCities(europeanCapitals, "18_European_Capital_Cities_By_Population");

        // Generate report of the capital cities in the Middle East by population
        ArrayList<CapitalCity> middleEasternCapitals = a.capitalCitiesByPopulation("Region", "Middle East");
        a.printCapitalCities(middleEasternCapitals, "19_Middle-Eastern_Capital_Cities_By_Population");

        // ========================================================================================

        // Generate report of the top 10 most populous capital cities in the world
        ArrayList<CapitalCity> worldTopCapitals = a.topNWorldCapitalCitiesByPopulation(10);
        a.printCapitalCities(worldTopCapitals, "20_Top_N_World_Capital_Cities_By_Population");

        // Generate report of the top 10 most populous capital cities in Asia
        ArrayList<CapitalCity> asiaTopCapitals = a.topNCapitalCitiesByPopulation("Continent", "Asia", 10);
        a.printCapitalCities(asiaTopCapitals, "21_Top_N_Asia_Capital_Cities_By_Population");

        // Generate report of the top 10 most populous capital cities in Central America
        ArrayList<CapitalCity> centralAmericaTopCapitals = a.topNCapitalCitiesByPopulation("Region", "Central America", 10);
        a.printCapitalCities(centralAmericaTopCapitals, "22_Top_N_Central-America_Capital_Cities_By_Population");

        // ========================================================================================

        // Generate report of the population of each continent
        ArrayList<Population> continentPops = a.populationRuralUrban("Continent");
        a.printPopulations(continentPops, "23_Continent_Populations.md");

        // Generate report of the population of each region
        ArrayList<Population> regionPops = a.populationRuralUrban("Region");
        a.printPopulations(regionPops, "24_Region_Populations.md");

        // Generate report of the population of each country
        ArrayList<Population> countryPops = a.populationRuralUrban("Country");
        a.printPopulations(countryPops, "25_Country_Populations.md");

        // ========================================================================================

        // Generate report of the population of the world
        Population worldPop = a.worldPopulation();
        a.printAreaPopulation(worldPop, "26_World_Population.md");

        // Generate report of the population of South america
        Population southAmericaPop = a.areaPopulation("Continent", "South America");
        a.printAreaPopulation(southAmericaPop, "27_South-America_Population.md");

        // Generate report of the population of South america
        Population eastAfricaPop = a.areaPopulation("Region", "Eastern Africa");
        a.printAreaPopulation(eastAfricaPop, "28_East-Africa_Population.md");

        // Generate report of the population of South america
        Population jamaicaPop = a.areaPopulation("Country", "Jamaica");
        a.printAreaPopulation(jamaicaPop, "29_Jamaica_Population.md");

        // Generate report of the population of South america
        Population walesPop = a.areaPopulation("District", "Wales");
        a.printAreaPopulation(walesPop, "30_Wales_Population.md");

        // Generate report of the population of South america
        Population edinburghPop = a.areaPopulation("City", "Edinburgh");
        a.printAreaPopulation(edinburghPop, "31_Edinburgh_Population.md");

        // ========================================================================================

        ArrayList<Population> languagePops = a.languagePops(new String[]{"Chinese", "English", "Hindi", "Spanish", "Arabic"});
        a.printLanguagePopulations(languagePops, "32_Populations_of_5_languages.md");

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
     * Check if name is valid for files
     * @param name proposed file name
     * @return boolean
     */
    private boolean fileNameValid(String name)
    {
        int check = 0;
        for(char n : name.toCharArray())
        {
            if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_ 1234567890.".indexOf(n) != -1)
            {
                check++;
            }
        }

        if(check == name.length())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Prints list of cities
     * @param cities cities to be printed
     */
    public void printCities(ArrayList<City> cities, String filename)
    {
        if (filename == null || filename == "")
        {
            System.out.println("No filename provided!");
        }
        else if(!fileNameValid(filename))
        {
            System.out.println("Filename invalid!");
        }
        else if (cities == null)
        {
            System.out.println("No cities list has been given");
        }
        else if (cities.size() < 1)
        {
            System.out.println("No cities to print");
        }
        else
        {
            StringBuilder sb = new StringBuilder();

            //city info headers
            sb.append("| City | Country | District | Population |\r\n");
            sb.append("| --- | --- | --- | --- |\r\n");

            //city info for areas
            for (City n : cities)
            {
                sb.append("| " + n.getName() + " | " + n.getCountry() + " | " + n.getDistrict() + " | " + n.getPopulation() + " |\r\n");
            }

            //Output cities
            System.out.println(sb.toString());

            try
            {
                new File("./reports/").mkdir();
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename)));
                writer.write(sb.toString());
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints list of countries
     * @param countries countries to be printed
     */
    public void printCountries(ArrayList<Country> countries, String filename)
    {
        if (filename == null || filename == "")
        {
            System.out.println("No filename provided!");
        }
        else if(!fileNameValid(filename))
        {
            System.out.println("Filename invalid!");
        }
        else if(countries == null)
        {
            System.out.println("No countries list has been given");
        }
        else if(countries.size() < 1)
        {
            System.out.println("No countries to print");
        }
        else
        {
            StringBuilder sb = new StringBuilder();

            //Country info headers
            sb.append("| Code | Name | Continent | Region | Population | Capital |\r\n");
            sb.append("| --- | --- | --- | --- | --- | --- |\r\n");

            //country info for areas
            for (Country c : countries)
            {
                sb.append("| " + c.getCode() + " | " + c.getName() + " | " + c.getContinent() + " | " + c.getRegion() + " | " + c.getPopulation() + " | " + c.getCapitalName() + " |\r\n");
            }

            //Output countries
            System.out.println(sb);

            try
            {
                new File("./reports/").mkdir();
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename)));
                writer.write(sb.toString());
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints list of populations
     * @param populations populations to be printed
     */
    public void printPopulations(ArrayList<Population> populations, String filename)
    {
        if (filename == null || filename == "")
        {
            System.out.println("No filename provided!");
        }
        else if(populations == null)
        {
            System.out.println("No populations list has been given");
        }
        else if(populations.size() < 1)
        {
            System.out.println("No populations to print");
        }
        else
        {
            StringBuilder sb = new StringBuilder();

            //population info headers
            sb.append("| Location | Population | Urban Population | Percentage | Rural Population | Percentage |\r\n");
            sb.append("| --- | --- | --- | --- | --- | --- |\r\n");

            //population info for areas
            for (Population n : populations)
            {
                sb.append("| " + n.getArea() + " | " + n.getPopulation() + " | " + n.getUrban() + " | " + n.getUrbanPercentage() + "% | " + n.getRural() + " | " + n.getRuralPercentage() + "% |\r\n");
            }

            //Output populations
            System.out.println(sb);

            try
            {
                new File("./reports/").mkdir();
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename)));
                writer.write(sb.toString());
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints list of capital cities
     * @param cities Capital cities to be printed
     */
    public void printCapitalCities(ArrayList<CapitalCity> cities, String filename)
    {
        if(filename == null || filename == "")
        {
            System.out.println("No filename provided!");
        }
        else if(!fileNameValid(filename))
        {
            System.out.println("Filename invalid!");
        }
        else if(cities == null)
        {
            System.out.println("No capital cities list has been given");
        }
        else if(cities.size() < 1)
        {
            System.out.println("No capital cities to print");
        }
        else
        {
            StringBuilder sb = new StringBuilder();

            //Output the area population info headers
            sb.append("| City | Country | Population |\r\n");
            sb.append("| --- | --- | --- |\r\n");

            //Output population info for each area
            for (CapitalCity n : cities)
            {
                sb.append("| " + n.getName() + " | " + n.getCountry() + " | " + n.getPopulation() + " |\r\n");
            }

            //Output capital cities
            System.out.println(sb);
            try
            {
                new File("./reports/").mkdir();
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename)));
                writer.write(sb.toString());
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints population info of an area
     * @param pop Population to be printed
     */
    public void printAreaPopulation(Population pop, String filename)
    {
        if (filename == null || filename == "")
        {
            System.out.println("No filename provided!");
        }
        else if(pop == null)
        {
            System.out.println("No population has been given");
        }
        else
        {
            StringBuilder sb = new StringBuilder();

            //population info headers
            sb.append("| Area | Population |\r\n");
            sb.append("| --- | --- |\r\n");

            //population info for area
            sb.append("| " + pop.getArea() + " | " + pop.getPopulation() + " |\r\n");

            //Output population
            System.out.println(sb);

            try
            {
                new File("./reports/").mkdir();
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename)));
                writer.write(sb.toString());
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints populations of languages
     * @param pops Populations to be printed
     */
    public void printLanguagePopulations(ArrayList<Population> pops, String filename)
    {
        if (filename == null || filename == "")
        {
            System.out.println("No filename provided!");
        }
        else if(!fileNameValid(filename))
        {
            System.out.println("Filename invalid!");
        }
        else if(pops == null)
        {
            System.out.println("No populations list has been given");
        }
        else if(pops.size() < 1)
        {
            System.out.println("No populations to print");
        }
        else
        {
            StringBuilder sb = new StringBuilder();

            //Output the language population info headers
            sb.append("| Language | Population | Percentage |\r\n");
            sb.append("| --- | --- | --- |\r\n");

            //Output population info for each language
            for (Population n : pops)
            {
                sb.append("| " + n.getArea() + " | " + n.getPopulation() + " | " + n.getUrbanPercentage() + "% |\r\n");
            }

            //Output populations
            System.out.println(sb);

            try
            {
                new File("./reports/").mkdir();
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename)));
                writer.write(sb.toString());
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
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
     * @param num Number to display
     * @return Countries and their relevant info
     */
    public ArrayList<Country> topNCountriesByPopulation(String areaType, String area, int num)
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
                if (num <= 0)
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
                                        "LIMIT " + num;

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
     * @param num Number to display
     * @return Countries and their relevant info
     */
    public ArrayList<Country> topNWorldCountriesByPopulation(int num)
    {
        // If provided N is less than or equal to zero
        if (num <= 0)
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
                                "ORDER BY a.Population DESC " +
                                "LIMIT " + num;

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

    /**
     * Gets the population data of a given continent/region/country
     * @param areaType Area Type
     * @return Populations and their relevant info
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
                // Extract population information
                ArrayList<Population> populations = new ArrayList<>();
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
                // Extract city information
                ArrayList<City> cities = new ArrayList<>();
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
            // Extract city information
            ArrayList<City> cities = new ArrayList<>();
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
                // Extract city information
                ArrayList<City> cities = new ArrayList<>();
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
                // Extract city information
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
     * Gets a list of all capital cities in an area in order of population
     * @param areaType Area Type
     * @param area Name of Area
     * @return Capital cities and their relevant info
     */
    public ArrayList<CapitalCity> capitalCitiesByPopulation(String areaType, String area)
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
        else if(!areaType.equals("Continent") && !areaType.equals("Region"))
        {
            System.out.println("Invalid Area type");
            return null;
        }
        //Area type is valid
        else
        {
            try
            {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT a.Name, b.Name, a.Population " +
                                "FROM (SELECT * FROM city ORDER BY Population DESC) AS a " +
                                "JOIN country as b " +
                                "ON a.ID = b.Capital " +
                                "WHERE b." + areaType + " = '" + area + "' " +
                                "ORDER BY a.Population DESC";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract capital city information
                ArrayList<CapitalCity> cities = new ArrayList<>();
                while (rset.next())
                {
                    CapitalCity city = new CapitalCity(rset.getString("a.Name"),
                            rset.getString("b.Name"),
                            rset.getInt("a.Population"));
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
     * Gets a list of all capital cities in the world in order of population
     * @return Capital cities and their relevant info
     */
    public ArrayList<CapitalCity> worldCapitalCitiesByPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT a.Name, b.Name, a.Population " +
                            "FROM (SELECT * FROM city ORDER BY Population DESC) AS a " +
                            "JOIN country as b " +
                            "ON a.ID = b.Capital " +
                            "ORDER BY a.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract capital city information
            ArrayList<CapitalCity> cities = new ArrayList<>();
            while (rset.next())
            {
                CapitalCity city = new CapitalCity(rset.getString("a.Name"),
                        rset.getString("b.Name"),
                        rset.getInt("a.Population"));
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
     *  Gets a list of the top N capital cities in an area in order of population
     * @param areaType Area Type
     * @param area Name of Area
     * @param num Number to display
     * @return Capital cities and their relevant info
     */
    public ArrayList<CapitalCity> topNCapitalCitiesByPopulation(String areaType, String area, int num)
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
        else if(!areaType.equals("Continent") && !areaType.equals("Region"))
        {
            System.out.println("Invalid Area type");
            return null;
        }
        // If provided N is less than or equal to zero
        else if (num <= 0)
        {
            System.out.println("N equal to or below 0");
            return null;
        }
        //Area type is valid
        else
        {
            try
            {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT a.Name, b.Name, a.Population " +
                                "FROM (SELECT * FROM city ORDER BY Population DESC) AS a " +
                                "JOIN country as b " +
                                "ON a.ID = b.Capital " +
                                "WHERE b." + areaType + " = '" + area + "' " +
                                "ORDER BY a.Population DESC " +
                                "LIMIT " + num;
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract capital city information
                ArrayList<CapitalCity> cities = new ArrayList<>();
                while (rset.next())
                {
                    CapitalCity city = new CapitalCity(rset.getString("a.Name"),
                            rset.getString("b.Name"),
                            rset.getInt("a.Population"));
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
     * Gets a list of the top N capital cities in the world in order of population
     * @param num Number to display
     * @return Capital cities and their relevant info
     */
    public ArrayList<CapitalCity> topNWorldCapitalCitiesByPopulation(int num)
    {
        // If provided N is less than or equal to zero
        if (num <= 0)
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
                        "SELECT a.Name, b.Name, a.Population " +
                                "FROM (SELECT * FROM city ORDER BY Population DESC) AS a " +
                                "JOIN country as b " +
                                "ON a.ID = b.Capital " +
                                "ORDER BY a.Population DESC " +
                                "LIMIT " + num;
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract capital city information
                ArrayList<CapitalCity> cities = new ArrayList<>();
                while (rset.next())
                {
                    CapitalCity city = new CapitalCity(rset.getString("a.Name"),
                            rset.getString("b.Name"),
                            rset.getInt("a.Population"));
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
     * Gets the population of a given area
     * @param areaType Area Type
     * @param area Name of Area
     * @return Population of area
     */
    public Population areaPopulation(String areaType, String area)
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
        else if(!areaType.equals("Continent") && !areaType.equals("Region") && !areaType.equals("Country") && !areaType.equals("District") && !areaType.equals("City"))
        {
            System.out.println("Invalid Area type");
            return null;
        }
        //Area type is valid
        else
        {
            // Create string for SQL statement
            String strSelect;

            //If the area type is district or city
            if (areaType.equals("District") || areaType.equals("City"))
            {
                String areaTyped;
                //If country use "Name" in statement
                if (areaType.equals("City"))
                {
                    areaTyped = "Name";
                }
                else
                {
                    areaTyped = areaType;
                }
                //The name of the area type is used
                strSelect = "SELECT SUM(Population) as pop "
                        + "FROM city "
                        + "WHERE " + areaTyped + "= '" + area + "' "
                        + "GROUP BY " + areaTyped;
            }
            //Otherwise
            else
            {
                String areaTyped;
                //If country use "Name" in statement
                if (areaType.equals("Country"))
                {
                    areaTyped = "Name";
                }
                else
                {
                    areaTyped = areaType;
                }

                //The name of the area type is used
                strSelect = "SELECT SUM(Population) as pop "
                        + "FROM country "
                        + "WHERE " + areaTyped + "= '" + area + "' "
                        + "GROUP BY " + areaTyped;
            }

            try
            {
                // Create an SQL statement
                Statement stmt = con.createStatement();

                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract population information
                ArrayList<Population> pops = new ArrayList<>();
                while (rset.next())
                {
                    Population pop = new Population(area,
                            new BigInteger(rset.getString("pop")));
                    pops.add(pop);
                }

                return pops.get(0);
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
     * Gets the population of the world
     * @return Population of the world
     */
    public Population worldPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(Population) as pop FROM country";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract population information
            ArrayList<Population> pops = new ArrayList<>();
            while (rset.next())
            {
                Population pop = new Population("World",
                        new BigInteger(rset.getString("pop")));
                pops.add(pop);
            }

            return pops.get(0);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate report.");
            return null;
        }
    }

    /**
     * Gets the population of speakers of certain languages
     * @param langs Names of Languages
     * @return Populations and their relevant info
     */
    public ArrayList<Population> languagePops(String[] langs)
    {
        if(langs.length < 1)
        {
            return null;
        }
        else
        {
            //Create string for WHERE statements
            StringBuilder sb = new StringBuilder();

            //For each argument add statement
            for (int i = 0; i < langs.length; i++)
            {
                if (i < langs.length-1)
                {
                    sb.append("Language = '" + langs[i] + "' OR ");
                }
                else
                {
                    sb.append("Language = '" + langs[i] + "' ");
                }
            }

            try
            {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT a.Language as Language, SUM(b.Population) as Population, (SUM(b.Population)/(SELECT SUM(Population) FROM country))*100 as Percentage "
                                + "FROM (SELECT * FROM countrylanguage WHERE IsOfficial = 'T') as a "
                                + "JOIN country as b "
                                + "ON a.CountryCode = b.Code "
                                + "WHERE " + sb
                                + "GROUP BY Language "
                                + "ORDER BY Population DESC";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract population information
                ArrayList<Population> populations = new ArrayList<>();
                while (rset.next())
                {
                    Population pop = new Population(rset.getString("Language"),
                            new BigInteger(rset.getString("Population")),
                            rset.getFloat("Percentage"));
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
}
