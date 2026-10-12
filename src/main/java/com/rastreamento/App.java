package com.rastreamento;

import java.time.LocalDateTime;
import com.rastreamento.config.MongoConnection;
import com.rastreamento.model.Localizacao;
import com.rastreamento.repository.LocalizacaoRepository;

public class App {
    public static void main(String[] args) {
        Localizacao localizacao = new Localizacao(
            "VEICULO-001",
            -32.035,
            -52.098,
            LocalDateTime.now()
        );

        LocalizacaoRepository repository = new LocalizacaoRepository();

        repository.salvar(localizacao);

        System.out.println("Localização salva no MongoDB.");

        MongoConnection.close();
    }
}