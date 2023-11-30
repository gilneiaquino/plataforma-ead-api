package br.com.plataforma.ead.api.servicos;

import br.com.plataforma.ead.api.colecoes.Agrupamento;
import br.com.plataforma.ead.api.repositorios.AgrupamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgrupamentoService {

    private final AgrupamentoRepository agrupamentoRepository;

    @Autowired
    public AgrupamentoService(AgrupamentoRepository agrupamentoRepository) {
        this.agrupamentoRepository = agrupamentoRepository;
    }

    public List<Agrupamento> listarTodosAgrupamentos() {
        return agrupamentoRepository.findAll();
    }

    public Optional<Agrupamento> buscarAgrupamentoPorId(String id) {
        return agrupamentoRepository.findById(id);
    }

    public Agrupamento salvarAgrupamento(Agrupamento agrupamento) {
        return agrupamentoRepository.save(agrupamento);
    }

    public void excluirAgrupamentoPorId(String id) {
        agrupamentoRepository.deleteById(id);
    }

    public void salvarTodos(List<Agrupamento> agrupamentos) {
        agrupamentoRepository.saveAll(agrupamentos);
    }
}
