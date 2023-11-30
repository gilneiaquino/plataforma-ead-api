package br.com.plataforma.ead.api.repositorios;


import br.com.plataforma.ead.api.colecoes.UsuarioCurso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioCursoRepository extends MongoRepository<UsuarioCurso, String> {
    // Métodos personalizados, se necessário
}
