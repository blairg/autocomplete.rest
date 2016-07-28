package com.react.data.entity;

import org.springframework.data.annotation.Id;

public class City {
    @Id
    private final String id;

    private final String name;

    private City(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("City[id=%s, name='%s']", id, name);
    }

    public static class Builder {
        private final String id;
        private final String name;

        public Builder(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public City build() {
            return new City(this);
        }
    }
}
