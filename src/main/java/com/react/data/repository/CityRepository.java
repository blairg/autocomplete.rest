package com.react.data.repository;

import com.react.data.entity.City;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository {
    @Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
    List<City> findAllStartsWith(String name, Boolean caseSensitive);

    @Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
    List<City> findAllContains(String name, Boolean caseSensitive);
}
