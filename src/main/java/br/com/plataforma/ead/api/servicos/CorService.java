package br.com.plataforma.ead.api.servicos;

import br.com.plataforma.ead.api.colecoes.Cor;
import br.com.plataforma.ead.api.repositorios.CorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorService {

    private final CorRepository corRepository;

    @Autowired
    public CorService(CorRepository corRepository) {
        this.corRepository = corRepository;
    }

    public List<Cor> listarTodasCores() {
        return corRepository.findAll();
    }

    public Cor salvarCor(Cor cor) {
        return corRepository.save(cor);
    }

    public void excluirCorPorId(String id) {
        corRepository.deleteById(id);
    }

    // Outros métodos do serviço, se necessário
}
