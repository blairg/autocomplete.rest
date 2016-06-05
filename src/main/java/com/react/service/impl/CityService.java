package com.react.service.impl;

import com.react.db.entity.City;
import com.react.db.repository.ICityRepository;
import com.react.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;

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
