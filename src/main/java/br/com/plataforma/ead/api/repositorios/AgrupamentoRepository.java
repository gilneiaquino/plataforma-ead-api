package br.com.plataforma.ead.api.repositorios;

import br.com.plataforma.ead.api.colecoes.Agrupamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgrupamentoRepository extends MongoRepository<Agrupamento, String> {
    // Aqui você pode adicionar métodos personalizados, se necessário
}
