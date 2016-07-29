package com.react.controller.converter.impl;

import com.react.data.entity.City;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CityConverterImplTest {
    @Test
    public void convertEntitiesToModels() throws Exception {

        //given
        List<City> citiesFromService =  new ArrayList<>();
        citiesFromService.add(new City.Builder("12345", "Manchester").build());
        citiesFromService.add(new City.Builder("123456", "Mansfield").build());

        List<com.react.controller.model.City> citiesAsModel = new ArrayList<>();
        citiesAsModel.add(new com.react.controller.model.City(citiesFromService.get(0).getId(), citiesFromService.get(0).getName()));
        citiesAsModel.add(new com.react.controller.model.City(citiesFromService.get(1).getId(), citiesFromService.get(1).getName()));

        CityConverterImpl cityConverter = new CityConverterImpl();

        //when
        List<com.react.controller.model.City> citiesAsModelReturned = cityConverter.ConvertEntitiesToModels(citiesFromService);

        //then
        assertEquals(citiesAsModel.toString(), citiesAsModelReturned.toString());

    }

}