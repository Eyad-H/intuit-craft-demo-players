package com.playerApplication.utils;

import java.util.Set;

public class CsvValidation {
    private static final Set<String> setHeader = Set.of("playerID",
            "birthYear",
            "birthMonth",
            "birthDay",
            "birthCountry",
            "birthState",
            "birthCity",
            "deathYear",
            "deathMonth",
            "deathDay",
            "deathCountry",
            "deathState",
            "deathCity",
            "nameFirst",
            "nameLast",
            "nameGiven",
            "weight",
            "height",
            "bats",
            "throws",
            "debut",
            "finalGame",
            "retroID",
            "bbrefID");
    public static boolean isCSVDataValid(String[] line, int numOfFields)
    {
        if(line.length != numOfFields)
            return false;

        return true;
    }
    public static boolean isCSVHeaderValid(String[] line, int numOfFields)
    {
        if(!isCSVDataValid(line, numOfFields))
            return false;

        for(int i = 0; i < line.length; i++)
            if(!setHeader.contains(line[i]))
                return false;

        return true;
    }
}
