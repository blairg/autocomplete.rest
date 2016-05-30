package com.react.rest.models;

import org.springframework.data.annotation.Id;

/**
 * Created by bga11 on 22/05/2016.
 */
public class City {
    @Id
    private String id;

    private String name;

    public City() {}

    public City(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format(
                "City[id=%s, name='%s']",
                id, name);
    }
}
