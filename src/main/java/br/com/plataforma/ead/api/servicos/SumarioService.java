package br.com.plataforma.ead.api.servicos;

import br.com.plataforma.ead.api.colecoes.Sumario;
import br.com.plataforma.ead.api.repositorios.SumarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SumarioService {

    private final SumarioRepository sumarioRepository;

    @Autowired
    public SumarioService(SumarioRepository sumarioRepository) {
        this.sumarioRepository = sumarioRepository;
    }

    public List<Sumario> listarTodos() {
        return sumarioRepository.findAll();
    }

    public Optional<Sumario> buscarPorId(String id) {
        return sumarioRepository.findById(id);
    }

    public Sumario salvar(Sumario sumario) {
        return sumarioRepository.save(sumario);
    }

    public void deletarPorId(String id) {
        sumarioRepository.deleteById(id);
    }
 }
