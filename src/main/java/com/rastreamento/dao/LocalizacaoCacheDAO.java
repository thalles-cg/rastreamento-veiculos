package com.rastreamento.dao;

import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rastreamento.config.RedisConnection;
import com.rastreamento.model.Localizacao;
import com.rastreamento.util.LocalDateTimeTypeAdapter;

import redis.clients.jedis.Jedis;

public class LocalizacaoCacheDAO {
  private static final int TTL_SEGUNDOS = 300;

  private final Gson gson;

  public LocalizacaoCacheDAO() {
    gson = new GsonBuilder()
      .registerTypeAdapter(
        LocalDateTime.class,
        new LocalDateTimeTypeAdapter()
      )
      .create();
  }

  public void salvar(Localizacao localizacao) {
    String chave = "cache:veiculo:" + localizacao.getVeiculoId();

    String json = gson.toJson(localizacao);

    try (Jedis jedis = RedisConnection.getConnection()) {
      jedis.setex(chave, TTL_SEGUNDOS, json);
    }
  }

  public Localizacao buscar(String veiculoId) {
    String chave = "cache:veiculo:" + veiculoId;

    try (Jedis jedis = RedisConnection.getConnection()) {
      String json = jedis.get(chave);

      if (json == null) {
        return null;
      }

      return gson.fromJson(json, Localizacao.class);
    }
  }

  public void remover(String veiculoId) {
    String chave = "cache:veiculo:" + veiculoId;

    try (Jedis jedis = RedisConnection.getConnection()) {
      jedis.del(chave);
    }
  }

}