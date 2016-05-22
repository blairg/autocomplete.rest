package com.react.rest.controllers;

import com.react.rest.models.City;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bga11 on 22/05/2016.
 */
@RestController
public class CityController {

    @RequestMapping("/api/city/{name}")
    public List<City> get(@RequestParam(value = "name", defaultValue = "World") @PathVariable("name") String name) {
        /* model.addAttribute("name", name); */
        return Arrays.asList(new City("London"), new City("Manchester"));
    }

}
