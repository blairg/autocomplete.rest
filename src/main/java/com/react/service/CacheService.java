package com.react.service;

public interface CacheService {
    String get(String key);
    void add(String key, String value);
}
