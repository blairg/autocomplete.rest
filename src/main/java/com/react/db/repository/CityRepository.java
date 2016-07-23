package com.react.db.repository;

import com.react.db.entity.City;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CityRepository {
    @Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
    List<City> findAllStartsWith(String name, Boolean caseSensitive);

    @Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
    List<City> findAllContains(String name, Boolean caseSensitive);
}
