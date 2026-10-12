package com.rastreamento;

import java.util.Scanner;

import com.rastreamento.config.MongoConnection;
import com.rastreamento.dao.LocalizacaoCacheDAO;
import com.rastreamento.dao.LocalizacaoDAO;
import com.rastreamento.model.Localizacao;
import com.rastreamento.service.LocalizacaoService;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        MongoConnection mongo =
                new MongoConnection();

        LocalizacaoDAO localizacaoDAO =
                new LocalizacaoDAO(mongo);

        LocalizacaoCacheDAO cacheDAO =
                new LocalizacaoCacheDAO();

        LocalizacaoService localizacaoService =
                new LocalizacaoService(
                        localizacaoDAO,
                        cacheDAO
                );

        System.out.print(
                "Identificador do veículo: "
        );

        String veiculoId =
                scanner.nextLine();

        Localizacao localizacao =
                localizacaoService
                        .buscarUltimaLocalizacao(
                                veiculoId
                        );

        if (localizacao == null) {

            System.out.println(
                    "Nenhuma localização encontrada para o veículo."
            );

        } else {

            System.out.println();
            System.out.println(
                    "Última localização:"
            );

            System.out.println(localizacao);
        }

        scanner.close();
        mongo.close();
    }
}