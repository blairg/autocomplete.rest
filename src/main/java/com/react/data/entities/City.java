package com.react.data.entities;

import org.springframework.data.annotation.Id;

/**
 * Created by bga11 on 22/05/2016.
 */
public class City {
    @Id
    private String id;

    private String name;

    public City() {}

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

    static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;

        private Builder() {}

        public Builder(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public City build() {
            return new City(this);
        }
    }
}
