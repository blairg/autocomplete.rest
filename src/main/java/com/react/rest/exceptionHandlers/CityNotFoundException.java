package com.react.rest.exceptionHandlers;

/**
 * Created by bga11 on 25/05/2016.
 */

public class CityNotFoundException extends Exception
{
    public CityNotFoundException() {}
    public CityNotFoundException(String message)
    {
        super(message);
    }
}
