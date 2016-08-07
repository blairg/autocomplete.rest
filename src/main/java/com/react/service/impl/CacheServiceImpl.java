package com.react.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.react.service.CacheService;

public class CacheServiceImpl implements CacheService {

    private static final long MAX_SIZE = 100;
    private final LoadingCache<String, String> cache;

    public CacheServiceImpl() {
        cache = CacheBuilder.newBuilder().maximumSize(MAX_SIZE).build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return "0";
            }
        });
    }

    @Override
    public String get(String key) {
        return cache.getUnchecked(key);
    }

    @Override
    public void add(String key, String value) {
        cache.put(key, value);
    }
}
