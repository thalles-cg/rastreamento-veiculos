package com.rastreamento;

import com.rastreamento.config.MongoConnection;
import com.rastreamento.config.RedisConnection;
import redis.clients.jedis.Jedis;

public class App {
  public static void main(String[] args) {

    try {
        MongoConnection.getDatabase().runCommand(
            new org.bson.Document("ping", 1)
        );

        System.out.println("MongoDB conectado com sucesso.");
    } catch (Exception e) {
        System.out.println("Erro ao conectar ao MongoDB: " + e.getMessage());
    }

    try (Jedis jedis = RedisConnection.getConnection()) {
        String resposta = jedis.ping();

        System.out.println("Redis conectado com sucesso: " + resposta);
    } catch (Exception e) {
        System.out.println("Erro ao conectar ao Redis: " + e.getMessage());
    }

    MongoConnection.close();
  }
}