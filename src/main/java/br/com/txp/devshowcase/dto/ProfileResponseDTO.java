package br.com.txp.devshowcase.dto;

import br.com.txp.devshowcase.model.Profile;

public record ProfileResponseDTO(
    Long id,
    String name,
    String email,
    String bio
) {

    public static ProfileResponseDTO fromEntity(Profile profile) {
        return new ProfileResponseDTO(
            profile.getId(),
            profile.getName(),
            profile.getEmail(),
            profile.getBio()
        );
    }
}