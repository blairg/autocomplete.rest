package com.react.data.repository.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.react.data.entity.City;
import com.react.data.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Repository
class CityRepositoryImpl implements CityRepository {

    private static final Logger log = LoggerFactory.getLogger(CityRepositoryImpl.class);

    @Autowired
    private final DBCollection cityCollection;

    @Autowired
    CityRepositoryImpl(DBCollection cityCollection) {
        this.cityCollection = cityCollection;
    }

    @Override
    public List<City> findAllStartsWith(String name, Boolean caseSensitive) {

        log.info("In CityRepositoryImpl.findAllStartsWith with name:" + name + " caseSensitive:" + caseSensitive);

        List<City> cities;
        BasicDBObject query = new BasicDBObject();
        String searchString = ".*" + name;

        if(caseSensitive){
            query.put("name", Pattern.compile(searchString));
        }
        else{
            query.put("name", Pattern.compile(searchString, Pattern.CASE_INSENSITIVE));
        }

        DBCursor cursor = cityCollection.find(query);
        cities = ParseCities(cursor);

        log.info("After querying DB in CityRepositoryImpl.findAllStartsWith with name:" + name + " caseSensitive:" + caseSensitive);

        return cities;
    }

    @Override
    //@Cacheable(value = "cityCache", key = "#CityRepositoryImpl.findAllContains")
    public List<City> findAllContains(String name, Boolean caseSensitive) {

        log.info("In CityRepositoryImpl.findAllContains with name:" + name + " caseSensitive:" + caseSensitive);

        List<City> cities;
        BasicDBObject query = new BasicDBObject();
        String searchString = ".*" + name + ".*";

        if(caseSensitive){
            query.put("name", Pattern.compile(searchString));
        }
        else{
            query.put("name", Pattern.compile(searchString, Pattern.CASE_INSENSITIVE));
        }

        DBCursor cursor = cityCollection.find(query);
        cities = ParseCities(cursor);

        log.info("After querying DB in CityRepositoryImpl.findAllContains with name:" + name + " caseSensitive:" + caseSensitive);

        return cities;
    }

    private List<City> ParseCities(DBCursor cursor){
        List<City> cities = new ArrayList<>();

        try {
            while (cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                cities.add(new City.Builder(dbObject.get("_id").toString(), dbObject.get("name").toString()).build());
            }
        } finally {
            cursor.close();
        }

        return cities;
    }

}
