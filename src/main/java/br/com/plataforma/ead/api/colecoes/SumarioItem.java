package br.com.plataforma.ead.api.colecoes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sumarioItens")
public class SumarioItem {

    @Id
    private String nome;
    private String conteudo;

}
