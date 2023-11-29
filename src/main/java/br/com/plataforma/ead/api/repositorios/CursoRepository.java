package br.com.plataforma.ead.api.repositorios;

import br.com.plataforma.ead.api.colecoes.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CursoRepository extends MongoRepository<Curso, String> {
    // Métodos de consulta personalizados, se necessário
}