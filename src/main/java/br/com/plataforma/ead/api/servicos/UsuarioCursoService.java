package br.com.plataforma.ead.api.servicos;

import br.com.plataforma.ead.api.colecoes.Curso;
import br.com.plataforma.ead.api.colecoes.Usuario;
import br.com.plataforma.ead.api.colecoes.UsuarioCurso;
import br.com.plataforma.ead.api.repositorios.CursoRepository;
import br.com.plataforma.ead.api.repositorios.UsuarioCursoRepository;
import br.com.plataforma.ead.api.repositorios.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class UsuarioCursoService {

    private final UsuarioCursoRepository usuarioCursoRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    public UsuarioCursoService(UsuarioCursoRepository usuarioCursoRepository,
                               CursoRepository cursoRepository,
                               UsuarioRepository usuarioRepository) {
        this.usuarioCursoRepository = usuarioCursoRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
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


    public Optional<UsuarioCurso> inscreverUsuario(String cursoId, String idUsuario) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UsuarioCurso usuarioCurso = new UsuarioCurso(usuario.getId(), curso.getId());

        return Optional.ofNullable(usuarioCursoRepository.save(usuarioCurso));
    }
}
