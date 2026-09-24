package br.com.txp.devshowcase.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.txp.devshowcase.model.Project;

public record ProjectResponseDTO(
    Long id,
    String title,
    String description,
    String repositoryUrl,
    Long profileId,
    Double averageRating,
    Integer upvotes,
    Set<TechnologyResponseDTO> technologies
) {

    public static ProjectResponseDTO fromEntity(Project project) {
        return new ProjectResponseDTO(
            project.getId(),
            project.getTitle(),
            project.getDescription(),
            project.getRepositoryUrl(),
            project.getProfile().getId(),
            project.getAverageRating(),
            project.getUpvotes(),
            project.getTechnologies()
                    .stream()
                    .map(TechnologyResponseDTO::fromEntity)
                    .collect(Collectors.toSet())
        );
    }
}