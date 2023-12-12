package com.teamitg;

public class Log
{
    private String requestTimestamp;
    private String countryCode;
    private int responseTime;


    public Log(String requestTimestamp, String countryCode, int responseTime)
    {
        this.requestTimestamp = requestTimestamp;
        this.countryCode = countryCode;
        this.responseTime = responseTime;
    }

    public String getRequestTimestamp()
    {
        return requestTimestamp;
    }

    public void setRequestTimestamp(String requestTimestamp)
    {
        this.requestTimestamp = requestTimestamp;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    public int getResponseTime()
    {
        return responseTime;
    }

    public void setResponseTime(int responseTime)
    {
        this.responseTime = responseTime;
    }

    public String toString()
    {
        return this.requestTimestamp + ',' + this.countryCode + ',' + this.responseTime;
    }
}
