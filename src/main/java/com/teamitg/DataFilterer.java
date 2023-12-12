package com.teamitg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataFilterer
{
    protected static final String DELIMITER = ",";
    protected static final int TIMESTAMP_INDEX = 0;
    protected static final int COUNTRY_INDEX = 1;
    protected static final int TIMESTAMP_RESPONSE_TIME = 2;

    public static List<Log> filterByCountry(Reader source, String country)
    {
        return getLogsFromFile(source).stream()
                .filter(log -> log.getCountryCode().equals(country))
                .collect(Collectors.toList());
    }

    public static List<Log> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit)
    {
        return filterByCountry(source, country).stream()
                .filter(l -> l.getResponseTime() > limit)
                .collect(Collectors.toList());
    }

    public static List<Log> filterByResponseTimeAboveAverage(Reader source)
    {
        List<Log> logs = getLogsFromFile(source);
        if (!logs.isEmpty())
        {
            int responseTimeSum = 0;
            for (Log log : logs)
            {
                responseTimeSum = responseTimeSum + log.getResponseTime();
            }
            int responseTimeAverage = responseTimeSum / logs.size();
            return logs.stream()
                    .filter(log -> log.getResponseTime() > responseTimeAverage)
                    .collect(Collectors.toList());
        }

        return logs;
    }

    private static List<Log> getLogsFromFile(Reader source)
    {
        List<Log> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(source))
        {
            String fileLine;
            boolean isFileHeader = true;
            while ((fileLine = reader.readLine()) != null)
            {
                if (!isFileHeader)
                {
                    String[] fileLineSplatted = fileLine.split(DELIMITER);
                    if (fileLineSplatted.length == 3)
                    {
                        String logCountry = fileLineSplatted[COUNTRY_INDEX].trim();
                        String logTimestamp = fileLineSplatted[TIMESTAMP_INDEX].trim();
                        int logResponseTime = Integer.parseInt(fileLineSplatted[TIMESTAMP_RESPONSE_TIME].trim());
                        logs.add(new Log(logTimestamp, logCountry, logResponseTime));
                    }
                } else
                {
                    isFileHeader = false;
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return logs;
    }
}