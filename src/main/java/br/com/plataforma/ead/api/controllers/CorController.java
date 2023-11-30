
package br.com.plataforma.ead.api.controllers;

import br.com.plataforma.ead.api.colecoes.Cor;
import br.com.plataforma.ead.api.servicos.CorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cores", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CorController {

    private final CorService corService;

    @Autowired
    public CorController(CorService corService) {
        this.corService = corService;
    }

    @GetMapping
    public List<Cor> listarCores() {
        return corService.listarTodasCores();
    }

    @PostMapping
    public ResponseEntity<Cor> criarCor(@RequestBody Cor cor) {
        Cor novaCor = corService.salvarCor(cor);
        return new ResponseEntity<>(novaCor, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCorPorId(@PathVariable String id) {
        corService.excluirCorPorId(id);
        return ResponseEntity.noContent().build();
    }

    // Outros métodos do recurso Cor, se necessário
}
