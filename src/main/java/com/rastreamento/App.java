package com.rastreamento;

import java.time.LocalDateTime;

import com.rastreamento.config.MongoConnection;
import com.rastreamento.dao.LocalizacaoCacheDAO;
import com.rastreamento.dao.LocalizacaoDAO;
import com.rastreamento.model.Localizacao;
import com.rastreamento.service.TelemetriaService;

public class App {

    public static void main(String[] args) {

        MongoConnection mongo = new MongoConnection();

        LocalizacaoDAO localizacaoDAO =
                new LocalizacaoDAO(mongo);

        LocalizacaoCacheDAO cacheDAO =
                new LocalizacaoCacheDAO();

        TelemetriaService telemetriaService =
                new TelemetriaService(
                        localizacaoDAO,
                        cacheDAO
                );

        Localizacao localizacao =
                new Localizacao(
                        "VEICULO-004",
                        -32.040,
                        -52.100,
                        LocalDateTime.now()
                );

        telemetriaService.registrar(localizacao);

        System.out.println(
                "Telemetria registrada com sucesso."
        );

        mongo.close();
    }
}