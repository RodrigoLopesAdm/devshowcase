package br.com.txp.devshowcase.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProjectRequestDTO(

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 2, max = 100, message = "O título deve ter entre 2 e 100 caracteres")
    String title,

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    String description,

    @NotBlank(message = "A URL do repositório é obrigatória")
    @Pattern(
        regexp = "^https?://.+",
        message = "A URL do repositório deve ser válida"
    )
    String repositoryUrl,

    @NotNull(message = "O perfil é obrigatório")
    Long profileId,

    Set<Long> technologyIds

) {
}