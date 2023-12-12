package com.teamitg;


import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class DataFiltererTest
{

    public static final String SINGLE_LINE_FILE = "src/test/resources/single-Line";
    public static final String MULTI_LINES_FILE = "src/test/resources/multi-Lines";
    public static final String EMPTY_FILE = "src/test/resources/empty";

    @Test
    void filterByCountry_ShouldReturnEmptyList_WhenLogFileIsEmpty() throws FileNotFoundException
    {
        assertTrue(DataFilterer.filterByCountry(openFile(EMPTY_FILE), "GB").isEmpty());
    }

    @Test
    void filterByCountry_ShouldReturnLog_WhenFileContainsMultiLinesAndCountryMatch() throws FileNotFoundException
    {
        assertFalse(DataFilterer.filterByCountry(openFile(MULTI_LINES_FILE), "GB").isEmpty());
    }

    @Test
    void filterByCountry_ShouldReturnEmptyList_WhenFileContainsMultiLinesAndCountryNotMatch() throws FileNotFoundException
    {
        assertTrue(DataFilterer.filterByCountry(openFile(MULTI_LINES_FILE), "ES").isEmpty());
    }

    @Test
    void filterByCountry_ShouldReturnLog_WhenFileContainsSingleLineAndCountryMatch() throws FileNotFoundException
    {
        assertFalse(DataFilterer.filterByCountry(openFile(SINGLE_LINE_FILE), "GB").isEmpty());
    }

    @Test
    void filterByCountry_ShouldReturnEmptyList_WhenFileContainsSingleLineAndCountryNotMatch() throws FileNotFoundException
    {
        assertTrue(DataFilterer.filterByCountry(openFile(SINGLE_LINE_FILE), "ES").isEmpty());
    }

    @Test
    void filterByCountryWithResponseTimeAboveLimit_ShouldReturnEmptyList_WhenLogFileIsEmpty() throws FileNotFoundException
    {
        assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile(EMPTY_FILE), "GB", 500).isEmpty());
    }

    @Test
    void filterByCountryWithResponseTimeAboveLimit_ShouldReturnLog_WhenFileContainsMultiLinesAndCountryMatch() throws FileNotFoundException
    {
        assertFalse(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile(MULTI_LINES_FILE), "DE", 200).isEmpty());
    }

    @Test
    void filterByCountryWithResponseTimeAboveLimit_ShouldReturnEmptyList_WhenFileContainsMultiLinesAndCountryNotMatch() throws FileNotFoundException
    {
        assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile(MULTI_LINES_FILE), "ES", 500).isEmpty());
    }

    @Test
    void filterByCountryWithResponseTimeAboveLimit_ShouldReturnLog_WhenFileContainsSingleLineAndCountryMatch() throws FileNotFoundException
    {
        assertFalse(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile(SINGLE_LINE_FILE), "GB", 199).isEmpty());
    }

    @Test
    void filterByCountryWithResponseTimeAboveLimit_ShouldReturnEmptyList_WhenFileContainsSingleLineAndCountryNotMatch() throws FileNotFoundException
    {
        assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile(SINGLE_LINE_FILE), "ES", 500).isEmpty());
    }

    @Test
    void filterByCountryWithResponseTimeAboveLimit_ShouldReturnEmptyList_WhenFileContainsMultiLinesAndCountryMatchButUnderLimit() throws FileNotFoundException
    {
        assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile(MULTI_LINES_FILE), "GB", 50).isEmpty());
    }

    @Test
    void filterByResponseTimeAboveAverage_ShouldReturnEmptyList_WhenLogFileIsEmpty() throws FileNotFoundException
    {
        assertTrue(DataFilterer.filterByResponseTimeAboveAverage(openFile(EMPTY_FILE)).isEmpty());
    }

    @Test
    void filterByResponseTimeAboveAverage_ShouldReturnLog_WhenFileContainsMultiLines() throws FileNotFoundException
    {
        assertFalse(DataFilterer.filterByResponseTimeAboveAverage(openFile(MULTI_LINES_FILE)).isEmpty());
    }

    @Test
    void filterByResponseTimeAboveAverage_ShouldReturnEmptyList_WhenFileContainsSingleLine() throws FileNotFoundException
    {
        assertTrue(DataFilterer.filterByResponseTimeAboveAverage(openFile(SINGLE_LINE_FILE)).isEmpty());
    }

    private FileReader openFile(String filename) throws FileNotFoundException
    {
        return new FileReader(filename);
    }
}
