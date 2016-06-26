package com.react.controller.models;

import org.junit.Assert;
import org.junit.Test;

public class CityTest {
    @Test
    public void toString_ShouldReturnCorrectCityNameWhenPassedInConstructor() {

        //Arrange
        com.react.controller.models.City city;
        String id = "1";
        String name = "Leeds";

        //Act
        city = new com.react.controller.models.City(id, name);

        //Assert
        Assert.assertEquals("City[id=" + id + ", name='" + name + "']", city.toString());

    }

}