package br.com.plataforma.ead.api.controllers;

import br.com.plataforma.ead.api.colecoes.Curso;
import br.com.plataforma.ead.api.colecoes.Usuario;
import br.com.plataforma.ead.api.repositorios.CursoRepository;
import br.com.plataforma.ead.api.servicos.CursoService;
import br.com.plataforma.ead.api.servicos.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/cursos" , produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CursoController {

    private final CursoService cursoService;
    public CursoController(CursoService cursoService, UsuarioService usuarioService) {
        this.cursoService = cursoService;
    }

    @PostMapping("/salvar")
    public Curso salvar(@Valid @RequestBody Curso curso) {
        return this.cursoService.salvar(curso);
    }

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Curso buscarCurso(@PathVariable String id) {
        return cursoService.buscarCurso(id);
    }

    @PutMapping("/{id}")
    public Curso atualizarCurso(@PathVariable String id, @RequestBody Curso curso) {
        curso.setId(id);
        return cursoService.salvar(curso);
    }

    @DeleteMapping("/{id}")
    public void deletarCurso(@PathVariable String id) {
        cursoService.deletarPorId(id);
    }

    @PostMapping("/{cursoId}/inscricao")
    public ResponseEntity<String> inscreverUsuarioNoCurso(@PathVariable String cursoId, @RequestHeader("Authorization") String token) {
        return cursoService.inscreverCurso(cursoId,token);
    }
}
