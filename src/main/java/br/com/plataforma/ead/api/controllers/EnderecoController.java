package br.com.plataforma.ead.api.controllers;

import br.com.plataforma.ead.api.colecoes.Endereco;
import br.com.plataforma.ead.api.repositorios.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoController(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @GetMapping
    public List<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Endereco getEnderecoById(@PathVariable String id) {
        return enderecoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Endereco createEndereco(@RequestBody Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @PutMapping("/{id}")
    public Endereco updateEndereco(@PathVariable String id, @RequestBody Endereco endereco) {
        endereco.setId(id);
        return enderecoRepository.save(endereco);
    }

    @DeleteMapping("/{id}")
    public void deleteEndereco(@PathVariable String id) {
        enderecoRepository.deleteById(id);
    }
}
