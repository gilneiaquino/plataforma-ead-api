package br.com.plataforma.ead.api.servicos;

import br.com.plataforma.ead.api.colecoes.UsuarioCurso;
import br.com.plataforma.ead.api.repositorios.UsuarioCursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioCursoService {

    private final UsuarioCursoRepository usuarioCursoRepository;

    @Autowired
    public UsuarioCursoService(UsuarioCursoRepository usuarioCursoRepository) {
        this.usuarioCursoRepository = usuarioCursoRepository;
    }

    public List<UsuarioCurso> listarTodos() {
        return usuarioCursoRepository.findAll();
    }

    public Optional<UsuarioCurso> buscarPorId(String id) {
        return usuarioCursoRepository.findById(id);
    }

    public UsuarioCurso salvar(UsuarioCurso usuarioCurso) {
        return usuarioCursoRepository.save(usuarioCurso);
    }

    public void deletarPorId(String id) {
        usuarioCursoRepository.deleteById(id);
    }

    // Outros métodos conforme necessário
}
