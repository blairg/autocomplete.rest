package com.react.data.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CityTest {

    @Test
    public void ensureCityEntityCanBuild() {

        //given
        City city;
        String id = "1";
        String name = "Leeds";

        //when
        city = new City.Builder(id, name).build();

        //then
        assertEquals("City[id=" + id + ", name='" + name + "']", city.toString());

    }

}