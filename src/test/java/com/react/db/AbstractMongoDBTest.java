package com.react.db;

import com.github.fakemongo.Fongo;
import com.mongodb.*;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

public class AbstractMongoDBTest extends TestCase {


    private MongoClient _mongo;
    private DBCollection _dbCollection;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        _mongo = new Fongo("localhost").getMongo();
        DB db = _mongo.getDB("test");
        _dbCollection = db.getCollection("cities");
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();

        _mongo = null;
        _dbCollection = null;
    }

    public Mongo getMongo() {
        return _mongo;
    }

    public DBCollection getCollection() {
        return _dbCollection;
    }
}
