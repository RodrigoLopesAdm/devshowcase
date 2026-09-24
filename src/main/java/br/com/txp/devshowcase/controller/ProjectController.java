package br.com.txp.devshowcase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.txp.devshowcase.dto.FeedbackRequestDTO;
import br.com.txp.devshowcase.dto.FeedbackResponseDTO;
import br.com.txp.devshowcase.dto.ProjectRequestDTO;
import br.com.txp.devshowcase.dto.ProjectResponseDTO;
import br.com.txp.devshowcase.service.FeedbackService;
import br.com.txp.devshowcase.service.ProjectService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> cadastrar(
            @Valid @RequestBody ProjectRequestDTO dto) {

        return ResponseEntity.ok(service.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<Page<ProjectResponseDTO>> buscarTodos(
            @RequestParam(required = false) String technology,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(
                service.buscarTodos(technology, pageable)
        );
    }

    @PostMapping("/{id}/feedbacks")
    public ResponseEntity<FeedbackResponseDTO> cadastrarFeedback(
            @PathVariable Long id,
            @Valid @RequestBody FeedbackRequestDTO dto) {

        return ResponseEntity.ok(
                feedbackService.cadastrar(id, dto)
        );
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity<ProjectResponseDTO> darUpvote(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.darUpvote(id)
        );
    }
}