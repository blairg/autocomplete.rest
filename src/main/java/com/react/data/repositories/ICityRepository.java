package com.react.data.repositories;

import com.react.data.entities.City;

import java.net.UnknownHostException;
import java.util.List;


/**
 * Created by bga11 on 23/05/2016.
 */
public interface ICityRepository{
    List<City> findAllStartsWith(String name) throws UnknownHostException;
    List<City> findAllContains(String name);
}
