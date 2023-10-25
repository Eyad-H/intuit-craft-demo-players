package com.playerservice.utils;

import com.playerApplication.utils.CsvValidation;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {CsvValidationTest.class})
public class CsvValidationTest {
    final int NUM_OF_FIELDS = 24;


    @Test
    public void isCSVHeaderValidSuccess()
    {
        String[] header = new String[]{"playerID","birthYear", "birthMonth", "birthDay", "birthCountry", "birthState", "birthCity", "deathYear", "deathMonth", "deathDay", "deathCountry", "deathState", "deathCity", "nameFirst", "nameLast", "nameGiven", "weight", "height", "bats", "throws", "debut", "finalGame", "retroID", "bbrefID"};
        assertEquals(true, CsvValidation.isCSVHeaderValid(header, NUM_OF_FIELDS));
    }

    @Test
    public void isCSVHeaderValidFailure()
    {
        String[] header = new String[]{"playerID","birthYear", "birthMonth", "birthDay", "birthCountry", "birthState", "deathMonth", "deathDay", "deathCountry", "deathState", "deathCity", "nameFirst", "nameLast", "nameGiven", "weight", "height", "bats", "throws", "debut", "finalGame", "retroID", "bbrefID"};
        assertEquals(false, CsvValidation.isCSVHeaderValid(header, NUM_OF_FIELDS));
    }

    @Test
    public void isCSVDataValidSuccess()
    {
        String[] row = new String[]{"abbated01","1877","4","15","USA","PA","Latrobe","1957","1","6","USA","FL","Fort Lauderdale","Ed","Abbaticchio","Edward James","170","71","R","R","1897-09-04","1910-09-15","abbae101","abbated01"};
        assertEquals(true, CsvValidation.isCSVDataValid(row, NUM_OF_FIELDS));
    }

    @Test
    public void isCSVDataValidFailure()
    {
        String[] row = new String[]{"abbated01","1877","4","15","USA","PA","Latrobe","1957","1","6","USA","FL","Fort Lauderdale","170","71","R","R","1897-09-04","1910-09-15","abbae101","abbated01"};
        assertEquals(false, CsvValidation.isCSVDataValid(row, NUM_OF_FIELDS));
    }

}
