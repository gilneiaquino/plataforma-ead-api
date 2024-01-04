package br.com.plataforma.ead.api.repositorios;

import br.com.plataforma.ead.api.colecoes.Sumario;
import br.com.plataforma.ead.api.colecoes.SumarioItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SumarioRepository extends MongoRepository<Sumario, String> {
}

