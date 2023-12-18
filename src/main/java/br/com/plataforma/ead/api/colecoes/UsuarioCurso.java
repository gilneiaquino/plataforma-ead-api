package br.com.plataforma.ead.api.colecoes;

import br.com.plataforma.ead.api.enums.SituacaoCurso;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuario_curso")
public class UsuarioCurso {

    @Id
    private String id;
    private String usuarioId; // Chave estrangeira para Usuario (ou Aluno)
    private String cursoId; // Chave estrangeira para Curso
    private SituacaoCurso situacao;

    public UsuarioCurso(String usuarioId, String cursoId) {
        this.usuarioId = usuarioId;
        this.cursoId = cursoId;
    }
    public UsuarioCurso() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCursoId() {
        return cursoId;
    }

    public void setCursoId(String cursoId) {
        this.cursoId = cursoId;
    }

    public SituacaoCurso getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoCurso situacao) {
        this.situacao = situacao;
    }
}
