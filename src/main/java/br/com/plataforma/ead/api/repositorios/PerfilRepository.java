package br.com.plataforma.ead.api.repositorios;

import br.com.plataforma.ead.api.colecoes.Perfil;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PerfilRepository extends MongoRepository<Perfil, Long> {


}
