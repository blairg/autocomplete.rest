package com.react.db.repository.impl;

import com.mongodb.*;
import com.react.db.entity.City;
import com.react.db.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Repository
public class CityRepositoryImpl implements CityRepository {

    @Autowired
    private final DBCollection cityCollection;

    @Autowired
    CityRepositoryImpl(DBCollection cityCollection) {
        this.cityCollection = cityCollection;
    }

    @Override
    public List<City> findAllStartsWith(String name) {

        List<City> cities = new ArrayList<>();

        BasicDBObject query = new BasicDBObject();
        query.put("name", Pattern.compile(Pattern.quote(name)));
        DBCursor cursor = cityCollection.find(query);

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

    @Override
    public List<City> findAllContains(String name) {
        return null;
    }

}
