package br.com.plataforma.ead.api.colecoes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "sumarios")
public class Sumario {
    @Id
    private String id;
    private String titulo;

    @DBRef
    private List<SumarioItem> itensSumario;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<SumarioItem> getItensSumario() {
        return itensSumario;
    }

    public void setItensSumario(List<SumarioItem> itensSumario) {
        this.itensSumario = itensSumario;
    }
}

