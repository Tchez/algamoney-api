package com.example.algamoneyapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoneyapi.model.Categoria;
import com.example.algamoneyapi.service.CategoriaService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> getCategorias() {
        return categoriaService.getList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable Long id) {
        return categoriaService.get(id);
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@Valid @RequestBody Categoria entity,
            HttpServletResponse response) {
        return categoriaService.create(entity, response);
    }

}
