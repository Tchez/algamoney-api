package com.example.algamoneyapi.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoneyapi.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.service.LancamentoService;
import com.example.algamoneyapi.service.exception.PessoaInativaException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private MessageSource messageSource;

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

    @ExceptionHandler({ PessoaInativaException.class })
    public ResponseEntity<Object> handlePessoaInativaException(PessoaInativaException ex) {
        String mensagemUsuario = messageSource.getMessage("pessoa.inativa", null,
                LocaleContextHolder.getLocale());
        String mensagemDev = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDev));

        return ResponseEntity.badRequest().body(erros);
    }

}
