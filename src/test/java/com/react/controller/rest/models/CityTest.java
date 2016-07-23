package com.react.controller.rest.models;

import org.junit.Assert;
import org.junit.Test;

public class CityTest {
    @Test
    public void toString_ShouldReturnCorrectCityNameWhenPassedInConstructor() {
        //given
        com.react.controller.models.City city;
        String id = "1";
        String name = "Leeds";

        //when
        city = new com.react.controller.models.City(id, name);

        //then
        Assert.assertEquals("City[id=" + id + ", name='" + name + "']", city.toString());
    }

}