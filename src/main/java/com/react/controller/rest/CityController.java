package com.react.controller.rest;

import com.react.controller.converter.CityConverter;
import com.react.controller.model.City;
import com.react.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {

    private static final Logger log = LoggerFactory.getLogger(CityController.class);
    private final CityService service;
    private final CityConverter cityConverter;

    @Autowired
    CityController(CityService service, CityConverter converter) {
        this.service = service;
        this.cityConverter = converter;
    }

    @RequestMapping(value = "findAllStartsWith/{name}/{caseSensitive}", method = RequestMethod.GET)
    List<City> findAllStartsWith(@PathVariable("name") String name, @PathVariable("caseSensitive") Boolean caseSensitive) throws UnknownHostException {

        log.info("In CityController.findAllStartsWith with name:" + name + " caseSensitive:" + caseSensitive);

        return cityConverter.ConvertEntitiesToModels(service.findAllStartsWith(name, caseSensitive));
    }

    @RequestMapping(value = "findAllContains/{name}/{caseSensitive}", method = RequestMethod.GET)
    List<City> findAllContains(@PathVariable("name") String name, @PathVariable("caseSensitive") Boolean caseSensitive) throws UnknownHostException {

        log.info("In CityController.findAllContains with name:" + name + " caseSensitive:" + caseSensitive);

        return cityConverter.ConvertEntitiesToModels(service.findAllContains(name, caseSensitive));
    }
}
