package br.com.plataforma.ead.api.repositorios;

import br.com.plataforma.ead.api.colecoes.SumarioItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SumarioItemRepository extends MongoRepository<SumarioItem, String> {

}
