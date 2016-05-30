package com.react.data.repositories.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.react.data.entities.City;
import com.react.data.repositories.ICityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by bga11 on 22/05/2016.
 */
@Repository
public class CityRepository extends BaseRepository implements ICityRepository  {

    @Value("${spring.data.mongodb.collection}")
    private String collectionName;

    public CityRepository() throws UnknownHostException {
    }

    //TODO: TEST ME
    @Override
    public List<City> findAllStartsWith(String name) throws UnknownHostException {

        List<City> cities = new ArrayList<>();

        BasicDBObject query = new BasicDBObject();
        query.put("name",  Pattern.compile(Pattern.quote(name)));
        DBCursor cursor = GetCollection(collectionName).find(query);

        try {
            while(cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                cities.add(new City.Builder(dbObject.get("_id").toString(), dbObject.get("name").toString()).build());
            }
        } finally {
            cursor.close();
        }

        return cities;
    }

    //TODO: TEST ME
    @Override
    public List<City> findAllContains(String name) {
        return null;
    }
}
