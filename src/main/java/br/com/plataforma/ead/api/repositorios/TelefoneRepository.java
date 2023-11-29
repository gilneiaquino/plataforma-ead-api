package br.com.plataforma.ead.api.repositorios;

import br.com.plataforma.ead.api.colecoes.Telefone;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TelefoneRepository extends MongoRepository<Telefone, String> {
}
