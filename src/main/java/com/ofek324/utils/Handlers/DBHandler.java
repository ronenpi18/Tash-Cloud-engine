package com.ofek324.utils.Handlers;

import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;
import com.ofek324.Entities.Objects.ActionOfService;
import com.ofek324.Entities.Objects.Flow;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static com.ofek324.utils.Consts.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class DBHandler {
    public static MongoClient mClient;
    private static Logger logger = LoggerFactory.getLogger("org.mongodb.driver");

    public MongoClient getMongoClient() {
        if (mClient == null) {
            ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
            CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
            CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
            MongoClientSettings clientSettings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .codecRegistry(codecRegistry)
                    .build();
            logger.info("init mClient");
            mClient = MongoClients.create(clientSettings);
        }
        return mClient;
    }

    public MongoDatabase getDB() {
        return getMongoClient().getDatabase(DB_NAME);
    }
    public String getDBName() {
        return getMongoClient().getDatabase(DB_NAME).getName();
    }

    public MongoCollection<ActionOfService> getServicesCollection() {
        return getDB().getCollection(SERVICES_COLLECTION_NAME, ActionOfService.class);
    }
    protected String getServicesCollectionData() {
        MongoCollection<Document> col = getDB().getCollection(SERVICES_COLLECTION_NAME);
        ArrayList<String> actions = new ArrayList<>();
//        ArrayList<ActionOfService> actionOfServiceArrayList = new ArrayList<>();
        col.find().forEach((Block<Document>) document -> {
            actions.add(document.toJson());
        });
        return actions.toString();
    }

    private MongoCollection<Flow> getFlowsCollection() {
        return getDB().getCollection(FLOW_COLLECTION_NAME, Flow.class);
    }
    protected ArrayList<Flow> getFlowsCollectionData() {
        MongoCollection<Document> col = getDB().getCollection(FLOW_COLLECTION_NAME);
        ArrayList<Flow> flowArrayList = new ArrayList<>();
        col.find().forEach((Block<Document>) document -> {
            flowArrayList.add(JsonParser.jsonToObject(document.toJson(), Flow.class));
        });
        return flowArrayList;
    }


//
//    // Read all documents from user collection
//    private MongoCollection<Document> getUserCollection() {
//        return getDB().getCollection("user");
//    }
//    private void queryUsers() {
//        getUserCollection().find().forEach(new Block<Document>() {
//            @Override
//            public void apply(Document t) {
//                System.out.println(t);
//            }
//        });
//    }
//
//    // Insert a document in user collection
//    private void insertUser() {
//        Document document = new Document("username","qpt")
//                .append("email", "testemail@example.com");
//        getUserCollection().insertOne(document);
//    }

    public String insertNewService(ActionOfService actionOfService) {
        try {
            getServicesCollection().insertOne(actionOfService);
            return MSG_RSP_SUCCESS;
        } catch (MongoWriteException e){
            if(e.getMessage().contains("E11000"))
                return MSG_RSP_SRV_EXISTS;
            return MSG_RSP_UNKNOWN_ERROR;
        }
    }

    public String insertNewFlow(Flow flow) {
        try {
            getFlowsCollection().insertOne(flow);
            return MSG_RSP_SUCCESS;
        } catch (MongoWriteException e){
            if(e.getMessage().contains("E11000"))
                return MSG_RSP_SRV_EXISTS;
            return MSG_RSP_UNKNOWN_ERROR;
        }
    }


    // close mongoClient connection
    public void dropConn() {
        mClient.close();
    }
}
