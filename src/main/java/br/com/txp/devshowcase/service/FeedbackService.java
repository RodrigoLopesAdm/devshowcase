package br.com.txp.devshowcase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.txp.devshowcase.dto.FeedbackRequestDTO;
import br.com.txp.devshowcase.dto.FeedbackResponseDTO;
import br.com.txp.devshowcase.exception.ResourceNotFoundException;
import br.com.txp.devshowcase.model.Feedback;
import br.com.txp.devshowcase.model.Project;
import br.com.txp.devshowcase.repository.FeedbackRepository;
import br.com.txp.devshowcase.repository.ProjectRepository;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public FeedbackResponseDTO cadastrar(Long projectId, FeedbackRequestDTO dto) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Projeto não encontrado"));

        Feedback feedback = new Feedback(
                dto.rating(),
                dto.comment(),
                project
        );

        feedback = feedbackRepository.save(feedback);

        Double media = feedbackRepository.calcularMediaPorProjeto(projectId);

        project.setAverageRating(media);

        projectRepository.save(project);

        return FeedbackResponseDTO.fromEntity(feedback);
    }
}