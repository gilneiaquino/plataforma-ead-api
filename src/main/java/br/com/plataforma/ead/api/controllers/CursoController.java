package br.com.plataforma.ead.api.controllers;

import br.com.plataforma.ead.api.colecoes.Curso;
import br.com.plataforma.ead.api.repositorios.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/cursos" , produces = MediaType.APPLICATION_JSON_VALUE)
public class CursoController {
    private final CursoRepository cursoRepository;

    @Autowired
    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @PostMapping
    public Curso criarCurso(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Curso> buscarCurso(@PathVariable String id) {
        return cursoRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Curso atualizarCurso(@PathVariable String id, @RequestBody Curso curso) {
        curso.setId(id);
        return cursoRepository.save(curso);
    }

    @DeleteMapping("/{id}")
    public void deletarCurso(@PathVariable String id) {
        cursoRepository.deleteById(id);
    }
}
