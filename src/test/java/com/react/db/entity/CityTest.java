package com.react.db.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CityTest {

    @Test
    public void ensureCityEntityCanBuild() {

        //Arrange
        City city;
        String id = "1";
        String name = "Leeds";

        //Act
        city = new City.Builder(id, name).build();

        //Assert
        assertEquals("City[id=" + id + ", name='" + name + "']", city.toString());

    }

}