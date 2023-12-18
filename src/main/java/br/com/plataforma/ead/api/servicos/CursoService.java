package br.com.plataforma.ead.api.servicos;

import br.com.plataforma.ead.api.colecoes.Curso;
import br.com.plataforma.ead.api.colecoes.Usuario;
import br.com.plataforma.ead.api.colecoes.UsuarioCurso;
import br.com.plataforma.ead.api.config.JwtTokenUtil;
import br.com.plataforma.ead.api.repositorios.CursoRepository;
import br.com.plataforma.ead.api.repositorios.UsuarioCursoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final UsuarioService usuarioService;

    private final JwtTokenUtil jwtTokenUtil;

    private final UsuarioCursoService usuarioCursoService;

    public CursoService(CursoRepository cursoRepository, UsuarioService usuarioService, JwtTokenUtil jwtTokenUtil, UsuarioCursoService usuarioCursoService) {
        this.cursoRepository = cursoRepository;
        this.usuarioService = usuarioService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.usuarioCursoService = usuarioCursoService;
    }

    public Curso salvar(Curso curso) {
        return this.cursoRepository.save(curso);
    }

    public List<Curso> buscarTodos() {
        return this.cursoRepository.findAll();
    }

    public Curso buscarCurso(String id) {
        Optional<Curso> cursoOptional = this.cursoRepository.findById(id);
        return cursoOptional.orElseGet(Curso::new);
    }

    public Curso deletarPorId(String id) {
        Optional<Curso> cursoOptional = this.cursoRepository.findById(id);
        return cursoOptional.orElseGet(Curso::new);
    }

    public ResponseEntity<String> inscreverCurso(String cursoId, String token) {
        String email = "";
        if (token == null || !jwtTokenUtil.validateToken(token, jwtTokenUtil.extractUsername(token))) {
            email = jwtTokenUtil.extractUsername(token);
        }

        // Verifica se o usuário existe com o e-mail fornecido
        Optional<Usuario> usuarioOptional = usuarioService.findByEmail(email);
        if (usuarioOptional.isPresent()) {
            // Inscrever o usuário no curso com o ID fornecido
            Optional<UsuarioCurso> usuarioCursoOptional = usuarioCursoService.inscreverUsuario(cursoId, usuarioOptional.get().getId());
            if (usuarioCursoOptional.isPresent()) {
                return ResponseEntity.ok("Inscrição realizada com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao se inscrever no curso");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
        }
    }


}
