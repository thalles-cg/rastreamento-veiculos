package com.rastreamento.repository;

import java.time.ZoneId;
import java.util.Date;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rastreamento.config.MongoConnection;
import com.rastreamento.model.Localizacao;

public class LocalizacaoRepository {
  private final MongoCollection<Document> collection;

  public LocalizacaoRepository() {
    MongoDatabase database = MongoConnection.getDatabase();
    this.collection = database.getCollection("localizacoes");
  }

  public void salvar(Localizacao localizacao) {
    Date dataHora = Date.from(
      localizacao.getDataHora()
        .atZone(ZoneId.systemDefault())
        .toInstant()
    );

    Document document = new Document()
      .append("veiculoId", localizacao.getVeiculoId())
      .append("latitude", localizacao.getLatitude())
      .append("longitude", localizacao.getLongitude())
      .append("dataHora", dataHora);

    collection.insertOne(document);
  }
}