package com.react.config;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.io.IOException;
import java.net.UnknownHostException;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.username}")
    private String user;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Value("${spring.data.mongodb.collection}")
    private String cityCollection;

    @Bean(name="mongoClient")
    public MongoClient mongoClient() throws IOException {
        return new MongoClient(mongoHost, Integer.parseInt(mongoPort));
    }

    @Autowired
    @Bean(name="mongoDbFactory")
    public MongoDbFactory mongoDbFactory(MongoClient mongoClient) {
        return new SimpleMongoDbFactory(mongoClient, mongoDatabase);
    }

    @Autowired
    @Bean(name="mongoTemplate")
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, mongoDatabase);
    }

    @Autowired
    @Bean(name="cityCollection")
    public DBCollection cityCollection() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(mongoHost, Integer.parseInt(mongoPort));
        DB database = mongoClient.getDB(mongoDatabase);
        return database.getCollection(cityCollection);
    }


}
