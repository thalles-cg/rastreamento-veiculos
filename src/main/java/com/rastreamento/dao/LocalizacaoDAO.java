package com.rastreamento.dao;

import com.mongodb.client.MongoCollection;
import com.rastreamento.config.MongoConnection;
import com.rastreamento.model.Localizacao;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;

public class LocalizacaoDAO {
  private final MongoCollection<Localizacao> localizacoes;

  public LocalizacaoDAO(MongoConnection mongo) {
    localizacoes = mongo.getDatabase()
      .getCollection("localizacoes", Localizacao.class);
  }

  public void inserir(Localizacao localizacao) {
    localizacoes.insertOne(localizacao);
  }

  public Localizacao buscarMaisRecente(String veiculoId) {
    return localizacoes
      .find(eq("veiculoId", veiculoId))
      .sort(descending("dataHora"))
      .first();
  }
}