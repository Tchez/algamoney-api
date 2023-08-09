package com.example.algamoneyapi.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.event.RecursoCriadoEvent;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.PessoaRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public List<Pessoa> getList() {
        return repository.findAll();
    }

    public ResponseEntity<Pessoa> get(Long id) {
        Optional<Pessoa> pessoa = repository.findById(id);
        return pessoa.isPresent() ? ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();
    }

    public ResponseEntity<Pessoa> create(Pessoa entity, HttpServletResponse response) {
        Pessoa pessoa = repository.save(entity);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoa.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
    }

    public void delete(Long id) {
        Optional<Pessoa> pessoa = repository.findById(id);

        if (!pessoa.isPresent()) {
            throw new EmptyResultDataAccessException("Registro com ID " + id + " não encontrado.", 1);
        }
        repository.delete(pessoa.get());
    }

    public Pessoa update(Long id, Pessoa pessoa) {
        Optional<Pessoa> pessoaOptional = repository.findById(id);

        if (!pessoaOptional.isPresent()) {
            throw new EmptyResultDataAccessException("Registro com ID " + id + " não encontrado.", 1);
        }

        Pessoa savedPessoa = pessoaOptional.get();
        BeanUtils.copyProperties(pessoa, savedPessoa, "id");
        return repository.save(savedPessoa);
    }
}
