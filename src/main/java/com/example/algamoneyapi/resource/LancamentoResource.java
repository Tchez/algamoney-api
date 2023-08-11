package com.example.algamoneyapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.service.LancamentoService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    public List<Lancamento> getLancamentos() {
        return lancamentoService.getList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> getLancamento(@PathVariable Long id) {
        return lancamentoService.get(id);
    }

    @PostMapping
    public ResponseEntity<Lancamento> createLancamento(@Valid @RequestBody Lancamento entity,
            HttpServletResponse response) {
        return lancamentoService.create(entity, response);
    }

}
