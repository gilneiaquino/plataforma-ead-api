package br.com.plataforma.ead.api.colecoes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cursos")
public class Curso {
    @Id
    private String id;

    @NotBlank(message = "O titulo não pode estar em branco.")
    @NotNull(message = "O titulo não pode ser nulo.")
    private String titulo;
    private String progresso;

    @NotBlank(message = "A descrição resumida não pode estar em branco.")
    @NotNull(message = "A descrição resumida não pode ser nulo.")
    private String descricaoResumida;
    private String descricaoCompleta;

    @NotBlank(message = "A cor do curso não pode estar em branco.")
    @NotNull(message = "A cor do curso não pode ser nulo.")
    private String cor;

    @NotBlank(message = "O agrupamento não pode estar em branco.")
    @NotNull(message = "O agrupamento não pode ser nulo.")
    private String agrupamento;

    public Curso() {
    }

    public Curso(String id, String titulo, String progresso, String descricaoResumida, String descricaoCompleta, String cor, String agrupamento) {
        this.id = id;
        this.titulo = titulo;
        this.progresso = progresso;
        this.descricaoResumida = descricaoResumida;
        this.descricaoCompleta = descricaoCompleta;
        this.cor = cor;
        this.agrupamento = agrupamento;
    }

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

    public String getProgresso() {
        return progresso;
    }

    public void setProgresso(String progresso) {
        this.progresso = progresso;
    }

    public String getDescricaoResumida() {
        return descricaoResumida;
    }

    public void setDescricaoResumida(String descricaoResumida) {
        this.descricaoResumida = descricaoResumida;
    }

    public String getDescricaoCompleta() {
        return descricaoCompleta;
    }

    public void setDescricaoCompleta(String descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getAgrupamento() {
        return agrupamento;
    }

    public void setAgrupamento(String agrupamento) {
        this.agrupamento = agrupamento;
    }
}
