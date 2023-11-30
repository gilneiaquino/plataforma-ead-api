package br.com.plataforma.ead.api.repositorios;

import br.com.plataforma.ead.api.colecoes.Cor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorRepository extends MongoRepository<Cor, String> {

}
