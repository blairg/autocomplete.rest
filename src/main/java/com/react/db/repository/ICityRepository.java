package com.react.db.repository;

import com.react.db.entity.City;

import java.net.UnknownHostException;
import java.util.List;

public interface ICityRepository{
    List<City> findAllStartsWith(String name) throws UnknownHostException;
    List<City> findAllContains(String name);
}
