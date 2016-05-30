package com.react.data.repositories.impl;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;

import java.net.UnknownHostException;

/**
 * Created by bga11 on 25/05/2016.
 */
public class BaseRepository {

    @Value("${spring.data.mongodb.username}")
    private String user;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Value("${spring.data.mongodb.database}")
    private String database;

    private MongoClientURI uri;
    private MongoClient client;
    private DB db;

    public DBCollection GetCollection(String collectionName) throws UnknownHostException {
        //this.uri = new MongoClientURI("mongodb://" + user + ":" + password + "@" + host + ":" + port + "/" + database);
        this.uri = new MongoClientURI("mongodb://" + host + ":" + port + "/" + database);
        this.client = new MongoClient(this.uri);
        this.db = client.getDB(uri.getDatabase());
        return  db.getCollection(collectionName);
    }
}
