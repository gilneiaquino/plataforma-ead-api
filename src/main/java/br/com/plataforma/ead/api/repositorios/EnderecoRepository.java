package br.com.plataforma.ead.api.repositorios;

import br.com.plataforma.ead.api.colecoes.Endereco;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnderecoRepository extends MongoRepository<Endereco, String> {
}

