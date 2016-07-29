package com.react.service.impl;

import com.react.data.entity.City;
import com.react.data.repository.CityRepository;
import com.react.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);
    private final CityRepository repository;

    @Autowired
    CityServiceImpl(CityRepository repository)
    {
        this.repository = repository;
    }

    @Cacheable("CityRepositoryImpl.findAllStartsWith")
    public List<City> findAllStartsWith(String name, Boolean caseSensitive) throws UnknownHostException {

        log.info("In CityServiceImpl.findAllStartsWith with name:" + name + " caseSensitive:" + caseSensitive);

        return repository.findAllStartsWith(name, caseSensitive);
    }

    @Cacheable("CityRepositoryImpl.findAllContains")
    public List<City> findAllContains(String name, Boolean caseSensitive) throws UnknownHostException {

        log.info("In CityServiceImpl.findAllContains with name:" + name + " caseSensitive:" + caseSensitive);

        return repository.findAllContains(name, caseSensitive);
    }
}
