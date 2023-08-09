package com.example.algamoneyapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.event.RecursoCriadoEvent;
import com.example.algamoneyapi.model.Categoria;
import com.example.algamoneyapi.repository.CategoriaRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public List<Categoria> getList() {
        return repository.findAll();
    }

    public ResponseEntity<Categoria> get(Long id) {
        Optional<Categoria> categoria = repository.findById(id);
        return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
    }

    public ResponseEntity<Categoria> create(Categoria entity, HttpServletResponse response) {
        Categoria categoria = repository.save(entity);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoria.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }
}
