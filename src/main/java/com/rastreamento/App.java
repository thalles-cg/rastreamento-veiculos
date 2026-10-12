package com.rastreamento;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.rastreamento.config.MongoConnection;
import com.rastreamento.dao.LocalizacaoCacheDAO;
import com.rastreamento.dao.LocalizacaoDAO;
import com.rastreamento.model.Localizacao;
import com.rastreamento.service.TelemetriaService;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

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

        System.out.print("Identificador do veículo: ");
        String veiculoId = scanner.nextLine();

        System.out.print("Latitude: ");
        double latitude = scanner.nextDouble();

        System.out.print("Longitude: ");
        double longitude = scanner.nextDouble();

        Localizacao localizacao =
                new Localizacao(
                        veiculoId,
                        latitude,
                        longitude,
                        LocalDateTime.now()
                );

        telemetriaService.registrar(localizacao);

        System.out.println(
                "Telemetria registrada com sucesso."
        );

        scanner.close();
        mongo.close();
    }
}