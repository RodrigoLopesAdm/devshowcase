package br.com.txp.devshowcase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.txp.devshowcase.dto.TechnologyRequestDTO;
import br.com.txp.devshowcase.dto.TechnologyResponseDTO;
import br.com.txp.devshowcase.service.TecnologiaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tecnologias")
public class TecnologiaController {

    @Autowired
    private TecnologiaService service;

    @GetMapping
    public ResponseEntity<List<TechnologyResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<TechnologyResponseDTO> cadastrar(
            @Valid @RequestBody TechnologyRequestDTO dto) {

        return ResponseEntity.ok(service.cadastrar(dto));
    }
}