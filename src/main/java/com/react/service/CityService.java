package com.react.service;

import com.react.data.entity.City;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by bga11 on 25/05/2016.
 */
@Service
public interface CityService {
    List<City> findAllStartsWith(String name, Boolean caseSensitive) throws UnknownHostException;
    List<City> findAllContains(String name, Boolean caseSensitive) throws UnknownHostException;
}
