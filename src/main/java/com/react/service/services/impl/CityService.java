package com.react.service.services.impl;

import com.react.data.entities.City;
import com.react.data.repositories.ICityRepository;
import com.react.service.services.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by bga11 on 22/05/2016.
 */
@Service
public class CityService implements ICityService {
    private final ICityRepository repository;

    @Autowired
    CityService(ICityRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public List<City> findAllStartsWith(String name) throws UnknownHostException {
        return repository.findAllStartsWith(name);
    }

    @Override
    public List<City> findAllContains(String name) {
        return null;
    }
}
