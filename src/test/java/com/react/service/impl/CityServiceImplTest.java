package com.react.service.impl;

import com.react.data.entity.City;
import com.react.data.repository.CityRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CityServiceImplTest {

    private CityRepository cityRepository;

    @Before
    public void setUp(){
        cityRepository = mock(CityRepository.class);
    }

    @Test
    public void findAllStartsWithShouldReturnAllCitiesStartingWithMan() throws Exception {
        //given
        List<City> citiesToReturn =  new ArrayList<>();
        citiesToReturn.add(new City.Builder("12345", "Manchester").build());
        citiesToReturn.add(new City.Builder("123456", "Mansfield").build());

        String nameToFind = "Man";
        Boolean caseSensitive = true;

        //when
        when(cityRepository.findAllStartsWith(nameToFind, caseSensitive)).thenReturn(citiesToReturn);
        CityServiceImpl cityServiceImpl = new CityServiceImpl(cityRepository);
        List<City> citiesReturned = cityServiceImpl.findAllStartsWith(nameToFind, caseSensitive);

        //then
        assertEquals(citiesReturned.size(), citiesToReturn.size());
        List<City> citiesFound = citiesReturned.stream().filter(x -> x.getName() == "Manchester" || x.getName() ==  "Mansfield")
                                 .collect(Collectors.toList());
        assertEquals(2, citiesFound.size());
    }

    @Test
    public void findAllContainsShouldReturnAllCitiesContainingfield() throws Exception {
        //given
        List<City> citiesToReturn =  new ArrayList<>();
        citiesToReturn.add(new City.Builder("12", "Huddersfield").build());
        citiesToReturn.add(new City.Builder("123", "Mansfield").build());
        citiesToReturn.add(new City.Builder("1234", "Sheffield").build());

        String nameToFind = "field";
        Boolean caseSensitive = false;

        //when
        when(cityRepository.findAllContains(nameToFind, caseSensitive)).thenReturn(citiesToReturn);
        CityServiceImpl cityServiceImpl = new CityServiceImpl(cityRepository);
        List<City> citiesReturned = cityServiceImpl.findAllContains(nameToFind, caseSensitive);

        //then
        assertEquals(citiesReturned.size(), citiesToReturn.size());
        List<City> citiesFound = citiesReturned.stream().filter(x -> x.getName() == "Huddersfield" || x.getName() ==  "Mansfield" ||
                                                                     x.getName() == "Sheffield")
                                               .collect(Collectors.toList());
        assertEquals(3, citiesFound.size());
    }
}