package com.react.data.repository.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.react.Application;
import com.react.data.AbstractMongoDBTest;
import com.react.data.entity.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class CityRepositoryImplTest extends AbstractMongoDBTest {

    @Test
    public void findAllStartsWithShouldFindPartialMatchOfHuddersfield() throws Exception {
        //given
        DBCollection collection = super.getCollection();
        String huddersfield = "Huddersfield";
        BasicDBObject city = new BasicDBObject();
        city.put("name", huddersfield);
        collection.insert(city);
        String partialCityNameToFind = "Hudd";

        CityRepositoryImpl cityRepository = new CityRepositoryImpl(collection);

        //when
        List<City> allStartsWith = cityRepository.findAllStartsWith(partialCityNameToFind, true);

        //then
        assertEquals(1, allStartsWith.size());
        List<City> citiesFound = allStartsWith.stream().filter(x -> x.getName() == huddersfield)
                                 .collect(Collectors.toList());
        assertEquals(1, citiesFound.size());
    }

    @Test
    public void findAllStartsWithShouldFindPartialsMatchesOfHu() throws Exception {
        //given
        String partialCityNameToFind = "Hu";
        DBCollection collection = super.getCollection();
        String huddersfield = "Huddersfield";
        String hull = "Hull";

        List<DBObject> cities = new ArrayList<>();
        cities.add(new BasicDBObject("name", huddersfield));
        cities.add(new BasicDBObject("name", hull));
        collection.insert(cities);

        CityRepositoryImpl cityRepository = new CityRepositoryImpl(collection);

        //when
        List<City> allCitiesFound = cityRepository.findAllStartsWith(partialCityNameToFind, true);

        //then
        assertEquals(2, allCitiesFound.size());
        List<City> citiesFound = allCitiesFound.stream().filter(x -> x.getName() != huddersfield || x.getName() != hull)
                .collect(Collectors.toList());
        assertEquals(2, citiesFound.size());
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
        List<City> allStartsWith = cityRepository.findAllStartsWith(partialCityNameToFind, true);

        //then
        assertEquals(0, allStartsWith.size());
    }

    @Test
    public void findAllStartsWithShouldNotFindPartialMatchOfBarnsAsNotInDatabaseCaseInsensitive() throws Exception {
        //given
        DBCollection collection = super.getCollection();
        BasicDBObject city = new BasicDBObject();
        city.put("name", "Doncaster");
        collection.insert(city);
        String partialCityNameToFind = "Barns";

        CityRepositoryImpl cityRepository = new CityRepositoryImpl(collection);

        //when
        List<City> allStartsWith = cityRepository.findAllStartsWith(partialCityNameToFind, false);

        //then
        assertEquals(0, allStartsWith.size());
    }

    @Test
    public void findAllContainsShouldFindPartialMatchOfHuddersfield() throws Exception {
        //given
        DBCollection collection = super.getCollection();
        String huddersfield = "Huddersfield";
        BasicDBObject city = new BasicDBObject();
        city.put("name", huddersfield);
        collection.insert(city);
        String partialCityNameToFind = "udd";

        CityRepositoryImpl cityRepository = new CityRepositoryImpl(collection);

        //when
        List<City> allContains = cityRepository.findAllContains(partialCityNameToFind, true);

        //then
        assertEquals(1, allContains.size());
        List<City> citiesFound = allContains.stream().filter(x -> x.getName() == huddersfield)
                .collect(Collectors.toList());
        assertEquals(1, citiesFound.size());
    }

    @Test
    public void findAllContainsShouldFindPartialMatchOfHuddersfieldCaseInsensitive() throws Exception {
        //given
        DBCollection collection = super.getCollection();
        String huddersfield = "Huddersfield";
        BasicDBObject city = new BasicDBObject();
        city.put("name", huddersfield);
        collection.insert(city);
        String partialCityNameToFind = "udd";

        CityRepositoryImpl cityRepository = new CityRepositoryImpl(collection);

        //when
        List<City> allContains = cityRepository.findAllContains(partialCityNameToFind, false);

        //then
        assertEquals(1, allContains.size());
        List<City> citiesFound = allContains.stream().filter(x -> x.getName() == huddersfield)
                .collect(Collectors.toList());
        assertEquals(1, citiesFound.size());
    }

    @Test
    public void findAllContainsShouldFindPartialsMatchesOfHu() throws Exception {
        //given
        String partialCityNameToFind = "l";
        DBCollection collection = super.getCollection();
        String huddersfield = "Huddersfield";
        String hull = "Hull";

        List<DBObject> cities = new ArrayList<>();
        cities.add(new BasicDBObject("name", huddersfield));
        cities.add(new BasicDBObject("name", hull));
        collection.insert(cities);

        CityRepositoryImpl cityRepository = new CityRepositoryImpl(collection);

        //when
        List<City> allCitiesFound = cityRepository.findAllContains(partialCityNameToFind, true);

        //then
        assertEquals(2, allCitiesFound.size());
        List<City> citiesFound = allCitiesFound.stream().filter(x -> x.getName() != huddersfield || x.getName() != hull)
                .collect(Collectors.toList());
        assertEquals(2, citiesFound.size());
    }

    @Test
    public void findAllContainsShouldFindPartialsMatchesOfHuCaseInsensitive() throws Exception {
        //given
        String partialCityNameToFind = "l";
        DBCollection collection = super.getCollection();
        String huddersfield = "Huddersfield";
        String hull = "Hull";

        List<DBObject> cities = new ArrayList<>();
        cities.add(new BasicDBObject("name", huddersfield));
        cities.add(new BasicDBObject("name", hull));
        collection.insert(cities);

        CityRepositoryImpl cityRepository = new CityRepositoryImpl(collection);

        //when
        List<City> allCitiesFound = cityRepository.findAllContains(partialCityNameToFind, false);

        //then
        assertEquals(2, allCitiesFound.size());
        List<City> citiesFound = allCitiesFound.stream().filter(x -> x.getName() != huddersfield || x.getName() != hull)
                .collect(Collectors.toList());
        assertEquals(2, citiesFound.size());
    }

    @Test
    public void findAllContainsShouldNotFindPartialMatchOfBarnsAsNotInDatabase() throws Exception {
        //given
        DBCollection collection = super.getCollection();
        BasicDBObject city = new BasicDBObject();
        city.put("name", "Doncaster");
        collection.insert(city);
        String partialCityNameToFind = "rns";

        CityRepositoryImpl cityRepository = new CityRepositoryImpl(collection);

        //when
        List<City> allContains = cityRepository.findAllContains(partialCityNameToFind, true);

        //then
        assertEquals(0, allContains.size());
    }

}