package com.rastreamento;

import java.time.LocalDateTime;
import com.rastreamento.dao.LocalizacaoCacheDAO;
import com.rastreamento.model.Localizacao;

public class App {
    public static void main(String[] args) {

        Localizacao localizacao = new Localizacao(
                "VEICULO-003",
                -32.035,
                -52.098,
                LocalDateTime.now()
        );

        LocalizacaoCacheDAO cacheDAO =new LocalizacaoCacheDAO();

        cacheDAO.salvar(localizacao);

        System.out.println("Localização salva no cache com sucesso.");
    }
}