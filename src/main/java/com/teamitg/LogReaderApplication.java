package com.teamitg;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class LogReaderApplication
{
    private static final String FILE_NAME = "logExample.log";
    private static final String COUNTRY = "GB";
    private static final int RESPONSE_TIME_LIMIT = 40;

    public static void main(String[] args)
    {
//      invoking methods as evidence of implementation
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        callFilterByCountry(classloader);
        callFilterByCountryWithResponseTimeAboveLimit(classloader);
        callFilterByResponseTimeAboveAverage(classloader);
    }

    private static void callFilterByCountry(ClassLoader classloader)
    {
        List<Log> logs = new ArrayList<>();
        try (Reader targetReader = new InputStreamReader(classloader.getResourceAsStream(FILE_NAME)))
        {
            logs = DataFilterer.filterByCountry(targetReader, COUNTRY);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("FilterByCountry country=" + COUNTRY + " :");
        for (Log log : logs)
        {
            System.out.println(log);
        }
    }

    private static void callFilterByCountryWithResponseTimeAboveLimit(ClassLoader classloader)
    {
        List<Log> logs = new ArrayList<>();
        try (Reader targetReader = new InputStreamReader(classloader.getResourceAsStream(FILE_NAME)))
        {
            logs = DataFilterer.filterByCountryWithResponseTimeAboveLimit(targetReader, COUNTRY, RESPONSE_TIME_LIMIT);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("FilterByCountryWithResponseTimeAboveLimit limit=" + RESPONSE_TIME_LIMIT + " :");
        for (Log log : logs)
        {
            System.out.println(log);
        }
    }

    private static void callFilterByResponseTimeAboveAverage(ClassLoader classloader)
    {
        List<Log> logs = new ArrayList<>();
        try (Reader targetReader = new InputStreamReader(classloader.getResourceAsStream(FILE_NAME)))
        {
            logs = DataFilterer.filterByResponseTimeAboveAverage(targetReader);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("FilterByCountryWithResponseTimeAboveLimit:");
        for (Log log : logs)
        {
            System.out.println(log);
        }
    }
}