package com.rastreamento.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Sorts.descending;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoCollection;
import com.rastreamento.config.MongoConnection;
import com.rastreamento.model.Localizacao;

public class LocalizacaoDAO {
  private final MongoCollection<Localizacao> localizacoes;

  public LocalizacaoDAO(MongoConnection mongo) {
    localizacoes = mongo.getDatabase().getCollection("localizacoes", Localizacao.class);
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

  public List<String> buscarVeiculosComRegistrosAnterioresA(LocalDateTime dataLimite) {
    Bson filtro = lt("dataHora", dataLimite);

    return localizacoes
      .distinct("veiculoId", filtro, String.class)
      .into(new ArrayList<>());
  }

  public long removerAnterioresA(LocalDateTime dataLimite) {
    return localizacoes
      .deleteMany(lt("dataHora", dataLimite))
      .getDeletedCount();
  }
}