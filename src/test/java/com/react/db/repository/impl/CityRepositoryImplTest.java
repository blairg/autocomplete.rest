package com.react.db.repository.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.react.Application;
import com.react.db.AbstractMongoDBTest;
import com.react.db.entity.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class CityRepositoryImplTest extends AbstractMongoDBTest {

    @Test
    public void findAllStartsWithShouldFindPartialMatchOfHuddersfield() throws Exception {
        //given
        DBCollection collection = super.getCollection();
        BasicDBObject city = new BasicDBObject();
        city.put("name", "Huddersfield");
        collection.insert(city);
        String partialCityNameToFind = "Hudd";
        CityRepositoryImpl cityRepository = new CityRepositoryImpl(collection);

        //when
        List<City> allStartsWith = cityRepository.findAllStartsWith(partialCityNameToFind);

        //then
        assertEquals(1, allStartsWith.size());
    }

    @Test
    public void findAllStartsWithShouldNotFindPartialMatchOfBarnsAsNotInDatabase() throws Exception {
        //given
        DBCollection collection = super.getCollection();
        BasicDBObject city = new BasicDBObject();
        city.put("name", "Doncaster");
        collection.insert(city);
        String partialCityNameToFind = "Barns";
        CityRepositoryImpl cityRepository = new CityRepositoryImpl(collection);

        //when
        List<City> allStartsWith = cityRepository.findAllStartsWith(partialCityNameToFind);

        //then
        assertEquals(0, allStartsWith.size());
    }

}