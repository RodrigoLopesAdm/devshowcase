package br.com.txp.devshowcase.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.txp.devshowcase.dto.ProjectRequestDTO;
import br.com.txp.devshowcase.dto.ProjectResponseDTO;
import br.com.txp.devshowcase.exception.ResourceNotFoundException;
import br.com.txp.devshowcase.model.Profile;
import br.com.txp.devshowcase.model.Project;
import br.com.txp.devshowcase.model.Tecnologia;
import br.com.txp.devshowcase.repository.ProfileRepository;
import br.com.txp.devshowcase.repository.ProjectRepository;
import br.com.txp.devshowcase.repository.TecnologiaRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    @Transactional
    public ProjectResponseDTO cadastrar(ProjectRequestDTO dto) {

        Profile profile = profileRepository.findById(dto.profileId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Perfil não encontrado"));

        Project project = new Project(
                dto.title(),
                dto.description(),
                dto.repositoryUrl(),
                profile
        );

        if (dto.technologyIds() != null && !dto.technologyIds().isEmpty()) {

            List<Tecnologia> tecnologias =
                    tecnologiaRepository.findAllById(dto.technologyIds());

            Set<Tecnologia> tecnologiasSet = new HashSet<>(tecnologias);

            project.setTechnologies(tecnologiasSet);
        }

        project = projectRepository.save(project);

        return ProjectResponseDTO.fromEntity(project);
    }

    @Transactional(readOnly = true)
    public Page<ProjectResponseDTO> buscarTodos(
            String technology,
            Pageable pageable) {

        Page<Project> projetos;

        if (technology != null && !technology.isBlank()) {
            projetos = projectRepository
                    .findByTechnologiesNameIgnoreCase(technology, pageable);
        } else {
            projetos = projectRepository.findAll(pageable);
        }

        return projetos.map(ProjectResponseDTO::fromEntity);
    }

    @Transactional
    public ProjectResponseDTO darUpvote(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Projeto não encontrado"));

        project.setUpvotes(project.getUpvotes() + 1);

        project = projectRepository.save(project);

        return ProjectResponseDTO.fromEntity(project);
    }
}