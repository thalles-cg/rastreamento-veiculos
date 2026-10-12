package com.rastreamento.service;

import com.rastreamento.dao.LocalizacaoCacheDAO;
import com.rastreamento.dao.LocalizacaoDAO;
import com.rastreamento.model.Localizacao;

public class LocalizacaoService {
  private final LocalizacaoDAO localizacaoDAO;
  private final LocalizacaoCacheDAO cacheDAO;

  public LocalizacaoService(LocalizacaoDAO localizacaoDAO, LocalizacaoCacheDAO cacheDAO) {
    this.localizacaoDAO = localizacaoDAO;
    this.cacheDAO = cacheDAO;
  }

  public Localizacao buscarUltimaLocalizacao(String veiculoId) {
    Localizacao localizacao = cacheDAO.buscar(veiculoId);

    if (localizacao != null) {
      System.out.println("Localização recuperada do Redis.");

      return localizacao;
    }

    System.out.println("Localização não encontrada no Redis. Consultando MongoDB...");

    localizacao = localizacaoDAO.buscarMaisRecente(veiculoId);

    if (localizacao != null) {
      cacheDAO.salvar(localizacao);
    }

    return localizacao;
  }
}