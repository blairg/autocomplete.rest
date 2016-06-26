package com.react.service.impl;

import com.react.db.entity.City;
import com.react.db.repository.CityRepository;
import com.react.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository repository;

    @Autowired
    CityServiceImpl(CityRepository repository)
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
