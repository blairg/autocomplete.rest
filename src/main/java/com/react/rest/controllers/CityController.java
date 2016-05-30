package com.react.rest.controllers;

import com.react.rest.exceptionHandlers.CityNotFoundException;
import com.react.rest.models.City;
import com.react.service.services.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bga11 on 22/05/2016.
 */
@RestController
@RequestMapping("/api/city")
public class CityController extends BaseController<com.react.data.entities.City, City> {

    private final ICityService service;

    @Autowired
    CityController(ICityService service) {
        this.service = service;
    }

    //todo: TEST ME
    @RequestMapping(value = "findAllStartsWith/{name}", method = RequestMethod.GET)
    List<City> findAllStartsWith(@PathVariable("name") String name) throws UnknownHostException {
        return ConvertEntitiesToModels(service.findAllStartsWith(name));
    }

    //todo: TEST ME
    @Override
    public List<City> ConvertEntitiesToModels(List<com.react.data.entities.City> cities){
        return cities.stream().map(city -> new City(city.getId(), city.getName())).collect(Collectors.toList());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleCityNotFound(CityNotFoundException ex) {
    }
}
