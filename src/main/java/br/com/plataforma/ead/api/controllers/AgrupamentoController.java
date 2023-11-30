package br.com.plataforma.ead.api.controllers;
import br.com.plataforma.ead.api.colecoes.Agrupamento;
import br.com.plataforma.ead.api.servicos.AgrupamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/agrupamentos", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AgrupamentoController {

    private final AgrupamentoService agrupamentoService;

    @Autowired
    public AgrupamentoController(AgrupamentoService agrupamentoService) {
        this.agrupamentoService = agrupamentoService;
    }

    @GetMapping
    public List<Agrupamento> listarAgrupamentos() {
        return agrupamentoService.listarTodosAgrupamentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agrupamento> buscarAgrupamentoPorId(@PathVariable String id) {
        return agrupamentoService.buscarAgrupamentoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Agrupamento> criarAgrupamento(@RequestBody Agrupamento agrupamento) {
        Agrupamento novoAgrupamento = agrupamentoService.salvarAgrupamento(agrupamento);
        return new ResponseEntity<>(novoAgrupamento, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAgrupamentoPorId(@PathVariable String id) {
        agrupamentoService.excluirAgrupamentoPorId(id);
        return ResponseEntity.noContent().build();
    }

    // Outros métodos do recurso Agrupamento, se necessário
}
