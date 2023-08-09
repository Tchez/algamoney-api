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
        Pessoa pessoa = findPessoaById(id);
        repository.delete(pessoa);
    }

    public Pessoa update(Long id, Pessoa entity) {
        Pessoa pessoa = findPessoaById(id);

        BeanUtils.copyProperties(entity, pessoa, "id");
        return repository.save(pessoa);
    }

    public void updateAtivo(Long id, Boolean ativo) {
        Pessoa pessoa = findPessoaById(id);
        pessoa.setAtivo(ativo);
        repository.save(pessoa);
    }

    private Pessoa findPessoaById(Long id) {
        Optional<Pessoa> pessoa = repository.findById(id);

        if (!pessoa.isPresent()) {
            throw new EmptyResultDataAccessException("Registro com ID " + id + " não encontrado.", 1);
        }

        return pessoa.get();
    }
}
