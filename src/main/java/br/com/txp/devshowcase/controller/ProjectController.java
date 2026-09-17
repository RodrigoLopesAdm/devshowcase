package br.com.txp.devshowcase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.txp.devshowcase.dto.ProjectRequestDTO;
import br.com.txp.devshowcase.dto.ProjectResponseDTO;
import br.com.txp.devshowcase.service.ProjectService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> cadastrar(
            @Valid @RequestBody ProjectRequestDTO dto) {

        return ResponseEntity.ok(service.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> buscarTodos() {

        return ResponseEntity.ok(service.buscarTodos());
    }
}