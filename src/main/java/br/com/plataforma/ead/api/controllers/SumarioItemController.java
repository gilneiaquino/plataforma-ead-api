package br.com.plataforma.ead.api.controllers;

import br.com.plataforma.ead.api.colecoes.SumarioItem;
import br.com.plataforma.ead.api.servicos.SumarioItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sumario-itens")
public class SumarioItemController {

    private final SumarioItemService sumarioItemService;


    @Autowired
    public SumarioItemController(SumarioItemService sumarioItemService) {
        this.sumarioItemService = sumarioItemService;
    }

    @GetMapping
    public ResponseEntity<List<SumarioItem>> listarTodos() {
        List<SumarioItem> sumarios = sumarioItemService.listarTodos();
        return new ResponseEntity<>(sumarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SumarioItem> buscarPorId(@PathVariable String id) {
        return sumarioItemService.buscarPorId(id)
                .map(sumario -> new ResponseEntity<>(sumario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/salvar")
    public ResponseEntity<SumarioItem> salvar(@RequestBody SumarioItem sumarioItem) {
        SumarioItem novoSumario = sumarioItemService.salvar(sumarioItem);
        return new ResponseEntity<>(novoSumario, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable String id) {
        sumarioItemService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // outros endpoints, se necessário
}
