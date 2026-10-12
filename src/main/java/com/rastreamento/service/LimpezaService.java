package com.rastreamento.service;

import java.time.LocalDateTime;
import java.util.List;
import com.rastreamento.dao.LocalizacaoCacheDAO;
import com.rastreamento.dao.LocalizacaoDAO;

public class LimpezaService {
  private final LocalizacaoDAO localizacaoDAO;
  private final LocalizacaoCacheDAO cacheDAO;

  public LimpezaService(LocalizacaoDAO localizacaoDAO, LocalizacaoCacheDAO cacheDAO) {
    this.localizacaoDAO = localizacaoDAO;
    this.cacheDAO = cacheDAO;
  }

  public long limparHistorico(int dias) {
    LocalDateTime dataLimite = LocalDateTime.now().minusDays(dias);

    List<String> veiculosAfetados = localizacaoDAO.buscarVeiculosComRegistrosAnterioresA(dataLimite);

    long quantidadeRemovida = localizacaoDAO.removerAnterioresA(dataLimite);

    for (String veiculoId : veiculosAfetados) {
      cacheDAO.remover(veiculoId);
    }

    return quantidadeRemovida;
  }
}