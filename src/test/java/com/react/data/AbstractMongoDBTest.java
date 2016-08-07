package com.react.data;

import com.github.fakemongo.Fongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.react.Application;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class AbstractMongoDBTest extends TestCase {

    private MongoClient _mongo;
    private DBCollection _dbCollection;

    public Mongo getMongo() {
        return _mongo;
    }

    public DBCollection getCollection() {
        return _dbCollection;
    }

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

    @Test
    public void DummyTestToAllowThisClassToBeUsedForInheritance() {
        assertEquals(1, 1);
    }
}
