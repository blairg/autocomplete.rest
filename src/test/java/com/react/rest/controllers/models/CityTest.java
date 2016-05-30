package com.react.rest.controllers.models;

import com.react.rest.models.City;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bga11 on 22/05/2016.
 */
public class CityTest {
    @Test
    public void toString_ShouldReturnCorrectCityNameWhenPassedInConstructor() {

        //Arrange
        City city;
        String id = "1";
        String name = "Leeds";

        //Act
        city = new City(id, name);

        //Assert
        Assert.assertEquals("City[id=" + id + ", name='" + name + "']", city.toString());

    }
}