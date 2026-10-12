package com.rastreamento;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Scanner;

import com.rastreamento.config.MongoConnection;
import com.rastreamento.dao.LocalizacaoCacheDAO;
import com.rastreamento.dao.LocalizacaoDAO;
import com.rastreamento.model.Localizacao;
import com.rastreamento.service.LimpezaService;
import com.rastreamento.service.LocalizacaoService;
import com.rastreamento.service.TelemetriaService;

public class App {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    scanner.useLocale(Locale.US);

    MongoConnection mongo = new MongoConnection();

    LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO(mongo);
    LocalizacaoCacheDAO cacheDAO = new LocalizacaoCacheDAO();

    TelemetriaService telemetriaService = new TelemetriaService(localizacaoDAO, cacheDAO);

    LocalizacaoService localizacaoService = new LocalizacaoService(localizacaoDAO, cacheDAO);

    LimpezaService limpezaService = new LimpezaService(localizacaoDAO, cacheDAO);

    int opcao;

    do {
      System.out.println();
      System.out.println("=== Sistema de Rastreamento de Veículos ===");
      System.out.println("1 - Registrar telemetria");
      System.out.println("2 - Consultar última localização");
      System.out.println("3 - Limpar histórico antigo");
      System.out.println("0 - Sair");
      System.out.print("Escolha uma opção: ");

      opcao = lerOpcao(scanner);

      switch (opcao) {

        case 1 -> {
          String veiculoId = lerVeiculoId(scanner);
          double latitude = lerLatitude(scanner);
          double longitude = lerLongitude(scanner);

          Localizacao localizacao = new Localizacao(
            veiculoId,
            latitude,
            longitude,
            LocalDateTime.now()
          );

          telemetriaService.registrar(localizacao);

          System.out.println("Telemetria registrada com sucesso.");
        }

        case 2 -> {
          String veiculoId = lerVeiculoId(scanner);

          Localizacao localizacao = localizacaoService.buscarUltimaLocalizacao(veiculoId);

          if (localizacao == null) {
            System.out.println("Nenhuma localização encontrada.");
          } else {
            System.out.println("Última localização:");
            System.out.println(localizacao);
          }
        }

        case 3 -> {
          int dias = lerDias(scanner);

          long removidos = limpezaService.limparHistorico(dias);

          System.out.println(
            removidos + " registro(s) removido(s)."
          );
        }

        case 0 -> {
          System.out.println("Encerrando aplicação.");
        }

        default -> {
          System.out.println("Opção inválida.");
        }
      }

    } while (opcao != 0);

    scanner.close();
    mongo.close();
  }

  private static String lerVeiculoId(Scanner scanner) {
    String veiculoId;

    do {
      System.out.print("Identificador do veículo: ");
      veiculoId = scanner.nextLine().trim();

      if (veiculoId.isEmpty()) {
        System.out.println(
          "O identificador do veículo não pode estar vazio."
        );
      }

    } while (veiculoId.isEmpty());

    return veiculoId;
  }

  private static double lerLatitude(Scanner scanner) {
    while (true) {
      System.out.print("Latitude: ");

      if (!scanner.hasNextDouble()) {
        System.out.println("Latitude inválida. Digite um número.");
        scanner.nextLine();
        continue;
      }

      double latitude = scanner.nextDouble();
      scanner.nextLine();

      if (latitude < -90 || latitude > 90) {
        System.out.println("A latitude deve estar entre -90 e 90.");
        continue;
      }

      return latitude;
    }
  }

  private static double lerLongitude(Scanner scanner) {
    while (true) {
      System.out.print("Longitude: ");

      if (!scanner.hasNextDouble()) {
        System.out.println("Longitude inválida. Digite um número.");
        scanner.nextLine();
        continue;
      }

      double longitude = scanner.nextDouble();
      scanner.nextLine();

      if (longitude < -180 || longitude > 180) {
        System.out.println("A longitude deve estar entre -180 e 180.");
        continue;
      }

      return longitude;
    }
  }

  private static int lerDias(Scanner scanner) {
    while (true) {
      System.out.print(
        "Remover registros mais antigos que quantos dias? "
      );

      if (!scanner.hasNextInt()) {
        System.out.println("Quantidade de dias inválida.");
        scanner.nextLine();
        continue;
      }

      int dias = scanner.nextInt();
      scanner.nextLine();

      if (dias <= 0) {
        System.out.println(
          "A quantidade de dias deve ser maior que zero."
        );
        continue;
      }

      return dias;
    }
  }

  private static int lerOpcao(Scanner scanner) {
    while (true) {
      System.out.print("Escolha uma opção: ");

      if (!scanner.hasNextInt()) {
        System.out.println("Opção inválida.");
        scanner.nextLine();
        continue;
      }

      int opcao = scanner.nextInt();
      scanner.nextLine();

      return opcao;
    }
  }
}