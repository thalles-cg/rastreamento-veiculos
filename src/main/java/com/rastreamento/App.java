package com.rastreamento;

import java.time.LocalDateTime;

import com.rastreamento.config.MongoConnection;
import com.rastreamento.dao.LocalizacaoDAO;
import com.rastreamento.model.Localizacao;

public class App {
    public static void main(String[] args) {

        MongoConnection mongo = new MongoConnection();

        LocalizacaoDAO dao = new LocalizacaoDAO(mongo);

        Localizacao localizacao = new Localizacao(
                "VEICULO-002",
                -32.035,
                -52.098,
                LocalDateTime.now()
        );

        dao.inserir(localizacao);

        System.out.println("Localização inserida com sucesso.");

        mongo.close();
    }
}