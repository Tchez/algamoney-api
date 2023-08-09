package com.example.algamoneyapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.service.PessoaService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> getPessoas() {
        return pessoaService.getList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoa(@PathVariable Long id) {
        return pessoaService.get(id);
    }

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        return pessoaService.create(pessoa, response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePessoa(@PathVariable Long id) {
        pessoaService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> putPessoa(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
        Pessoa pessoaSalva = pessoaService.update(id, pessoa);
        return ResponseEntity.ok(pessoaSalva);
    }

}
