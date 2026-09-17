package br.com.txp.devshowcase.dto;

import br.com.txp.devshowcase.model.Tecnologia;

public record TechnologyResponseDTO(Long id, String name) {

    public static TechnologyResponseDTO fromEntity(Tecnologia tech) {
        return new TechnologyResponseDTO(tech.getId(), tech.getName());
    }
}