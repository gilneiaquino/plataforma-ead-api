package br.com.plataforma.ead.api.repositorios;

import br.com.plataforma.ead.api.colecoes.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByEmailIgnoreCase(String email);

    @Query("SELECT a FROM Usuario a " +
            "WHERE (:nome IS NULL OR a.nome = :nome) " +
            "AND (:cpf IS NULL OR a.cpf = :cpf) " +
            "AND (:matricula IS NULL OR a.matricula = :matricula)")
    List<Usuario> buscarUsuariosPorNomeCpfMatricula(String nome, String cpf, String matricula);

    Usuario findByEmailAndSenha(String email, String senha);

    Optional<Usuario> findByNome(String nomeUsuario);


}
