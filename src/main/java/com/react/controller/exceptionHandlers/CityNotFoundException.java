package com.react.controller.exceptionHandlers;

public class CityNotFoundException extends Exception
{
    public CityNotFoundException() {}
    public CityNotFoundException(String message)
    {
        super(message);
    }
}
