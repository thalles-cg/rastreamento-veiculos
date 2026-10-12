package com.rastreamento.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoConnection {
  private final MongoClient client;
  private final MongoDatabase database;

  public MongoConnection() {
    var pojoCodecProvider =
      PojoCodecProvider.builder()
        .automatic(true)
        .build();

    var codecRegistry =
      fromRegistries(
        MongoClientSettings.getDefaultCodecRegistry(),
        fromProviders(pojoCodecProvider)
      );

    var settings =
      MongoClientSettings.builder()
        .applyConnectionString(
          new ConnectionString(
            "mongodb://localhost:27017"
          )
        )
        .codecRegistry(codecRegistry)
        .build();

    client = MongoClients.create(settings);

    database = client.getDatabase("rastreamento");
  }

  public MongoDatabase getDatabase() {
    return database;
  }

  public void close() {
    client.close();
  }
}