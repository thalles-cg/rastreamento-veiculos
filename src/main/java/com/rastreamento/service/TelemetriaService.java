package com.rastreamento.service;

import com.rastreamento.dao.LocalizacaoCacheDAO;
import com.rastreamento.dao.LocalizacaoDAO;
import com.rastreamento.model.Localizacao;

public class TelemetriaService {
  private final LocalizacaoDAO localizacaoDAO;
  private final LocalizacaoCacheDAO localizacaoCacheDAO;

  public TelemetriaService(LocalizacaoDAO localizacaoDAO, LocalizacaoCacheDAO localizacaoCacheDAO) {
    this.localizacaoDAO = localizacaoDAO;
    this.localizacaoCacheDAO = localizacaoCacheDAO;
  }

  public void registrar(Localizacao localizacao) {
    localizacaoDAO.inserir(localizacao);
    localizacaoCacheDAO.salvar(localizacao);
  }
}