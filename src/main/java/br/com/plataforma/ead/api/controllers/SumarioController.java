package br.com.plataforma.ead.api.controllers;

import br.com.plataforma.ead.api.colecoes.Sumario;
import br.com.plataforma.ead.api.servicos.SumarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sumarios")
public class SumarioController {

    private final SumarioService sumarioService;

    @Autowired
    public SumarioController(SumarioService sumarioService) {
        this.sumarioService = sumarioService;
    }

    @GetMapping
    public ResponseEntity<List<Sumario>> listarTodos() {
        List<Sumario> sumarios = sumarioService.listarTodos();
        return new ResponseEntity<>(sumarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sumario> buscarPorId(@PathVariable String id) {
        return sumarioService.buscarPorId(id)
                .map(sumario -> new ResponseEntity<>(sumario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/salvar")
    public ResponseEntity<Sumario> salvar(@RequestBody Sumario sumario) {
        Sumario novoSumario = sumarioService.salvar(sumario);
        return new ResponseEntity<>(novoSumario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sumario> atualizarSumario(@PathVariable String id, @RequestBody Sumario sumario) {
        sumario.setId(id);
        Sumario sumarioAtualizado = sumarioService.salvar(sumario);
        return new ResponseEntity<>(sumarioAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSumario(@PathVariable String id) {
        sumarioService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
