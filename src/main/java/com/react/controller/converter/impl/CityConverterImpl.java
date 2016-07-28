package com.react.controller.converter.impl;

import com.react.controller.converter.CityConverter;
import com.react.controller.model.City;

import java.util.List;
import java.util.stream.Collectors;

public class CityConverterImpl implements CityConverter {

    //todo: test me
    public List<City> ConvertEntitiesToModels(List<com.react.data.entity.City> cities){
        return cities.stream().map(city -> new City(city.getId(), city.getName())).collect(Collectors.toList());
    }
}
