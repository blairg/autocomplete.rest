package com.react.service.services;

import com.react.data.entities.City;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by bga11 on 25/05/2016.
 */
public interface ICityService {
    List<City> findAllStartsWith(String name) throws UnknownHostException;
    List<City> findAllContains(String name);
}
