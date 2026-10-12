package com.rastreamento.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
  private static final String URI = "mongodb://localhost:27017";
  private static final String DATABASE_NAME = "rastreamento";

  private static final MongoClient mongoClient = MongoClients.create(URI);

  public static MongoDatabase getDatabase() {
    return mongoClient.getDatabase(DATABASE_NAME);
  }

  public static void close() {
    mongoClient.close();
  }
}