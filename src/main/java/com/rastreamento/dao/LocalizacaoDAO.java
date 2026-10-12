package com.rastreamento.dao;

import com.mongodb.client.MongoCollection;
import com.rastreamento.config.MongoConnection;
import com.rastreamento.model.Localizacao;

public class LocalizacaoDAO {

    private final MongoCollection<Localizacao> localizacoes;

    public LocalizacaoDAO(MongoConnection mongo) {
      localizacoes = mongo.getDatabase()
        .getCollection("localizacoes", Localizacao.class);
    }

    public void inserir(Localizacao localizacao) {
      localizacoes.insertOne(localizacao);
    }
}