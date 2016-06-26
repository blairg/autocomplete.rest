package com.react.controller.rest;

import com.react.controller.exceptionHandlers.CityNotFoundException;
import com.react.controller.models.City;
import com.react.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/city")
public class CityController extends BaseController<com.react.db.entity.City, City> {

    private final CityService service;

    @Autowired
    CityController(CityService service) {
        this.service = service;
    }

    //todo: TEST ME
    @RequestMapping(value = "findAllStartsWith/{name}", method = RequestMethod.GET)
    List<City> findAllStartsWith(@PathVariable("name") String name) throws UnknownHostException {
        return ConvertEntitiesToModels(service.findAllStartsWith(name));
    }

    //todo: TEST ME
    @Override
    public List<City> ConvertEntitiesToModels(List<com.react.db.entity.City> cities){
        return cities.stream().map(city -> new City(city.getId(), city.getName())).collect(Collectors.toList());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleCityNotFound(CityNotFoundException ex) {
    }
}
