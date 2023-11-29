package br.com.plataforma.ead.api.repositorios;

import br.com.plataforma.ead.api.colecoes.InvalidToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvalidTokenRepository extends MongoRepository<InvalidToken, String> {
    InvalidToken findByToken(String token);
}
