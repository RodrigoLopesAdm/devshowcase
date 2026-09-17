package br.com.txp.devshowcase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.txp.devshowcase.dto.ProfileRequestDTO;
import br.com.txp.devshowcase.dto.ProfileResponseDTO;
import br.com.txp.devshowcase.service.ProfileService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService service;

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> cadastrar(
            @Valid @RequestBody ProfileRequestDTO dto) {

        return ResponseEntity.ok(service.cadastrar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }
}