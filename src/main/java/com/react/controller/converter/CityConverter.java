package com.react.controller.converter;

import com.react.controller.model.City;

import java.util.List;

public interface CityConverter {
    List<City> ConvertEntitiesToModels(List<com.react.data.entity.City> cities);
}
