package br.com.plataforma.ead.api.servicos;

import br.com.plataforma.ead.api.colecoes.SumarioItem;
import br.com.plataforma.ead.api.repositorios.SumarioItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class SumarioItemService {

    private final SumarioItemRepository sumarioItemRepository;

    @Autowired
    public SumarioItemService(SumarioItemRepository sumarioItemRepository) {
        this.sumarioItemRepository = sumarioItemRepository;
    }


    public void deletarPorId(String id) {
        sumarioItemRepository.deleteById(id);
    }

    public SumarioItem salvar(SumarioItem sumarioItem) {
        return sumarioItemRepository.save(sumarioItem);
    }

    public List<SumarioItem> listarTodos() {
        return  sumarioItemRepository.findAll();
    }

    public Optional<SumarioItem> buscarPorId(String id) {
        return sumarioItemRepository.findById(id);
    }
}
