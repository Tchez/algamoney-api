package com.example.algamoneyapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.event.RecursoCriadoEvent;
import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.repository.LancamentoRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public List<Lancamento> getList() {
        return repository.findAll();
    }

    public ResponseEntity<Lancamento> get(Long id) {
        Optional<Lancamento> Lancamento = repository.findById(id);
        return Lancamento.isPresent() ? ResponseEntity.ok(Lancamento.get()) : ResponseEntity.notFound().build();
    }

    public ResponseEntity<Lancamento> create(Lancamento entity, HttpServletResponse response) {
        Lancamento lancamento = repository.save(entity);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamento.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
    }

    private Lancamento findLancamentoById(Long id) {
        Optional<Lancamento> lancamento = repository.findById(id);

        if (!lancamento.isPresent()) {
            throw new EmptyResultDataAccessException("Registro com ID " + id + " não encontrado.", 1);
        }

        return lancamento.get();
    }

}
