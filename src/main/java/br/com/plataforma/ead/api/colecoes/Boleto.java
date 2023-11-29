package br.com.plataforma.ead.api.colecoes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "boletos")
public class Boleto {

    @Id
    private String id;
    private String codigoBarras;
    private String dataVencimento;
    private String dataEmissao;
    // Outros atributos

    // Construtores, getters e setters
}
