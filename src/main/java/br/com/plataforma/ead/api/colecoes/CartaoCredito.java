package br.com.plataforma.ead.api.colecoes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cartoesCredito")
public class CartaoCredito {

    @Id
    private String id;
    private String numero;
    private String nomeTitular;
    private String dataValidade;
    // Outros atributos

    // Construtores, getters e setters
}
