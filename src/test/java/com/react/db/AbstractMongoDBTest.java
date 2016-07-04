package com.react.db;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

public class AbstractMongoDBTest extends TestCase {

    /**
     * please store Starter or RuntimeConfig in a static final field
     * if you want to use artifact store caching (or else disable caching)
     */
    private static final MongodStarter starter = MongodStarter.getDefaultInstance();

    private MongodExecutable _mongodExe;
    private MongodProcess _mongod;

    private MongoClient _mongo;
    private DBCollection _dbCollection;

    @Override
    @Before
    public void setUp() throws Exception {

        Thread.sleep(200);
        int port = Network.getFreeServerPort();

        _mongodExe = starter.prepare(new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(port, Network.localhostIsIPv6()))
                .build());
        _mongod = _mongodExe.start();

        super.setUp();

        _mongo = new MongoClient("localhost", port);
        DB database = _mongo.getDB("test");
        _dbCollection = database.getCollection("City");
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();

        Thread.sleep(200);
        _mongod.stop();
        _mongodExe.stop();
    }

    public Mongo getMongo() {
        return _mongo;
    }

    public DBCollection getCollection() {
        return _dbCollection;
    }
}
