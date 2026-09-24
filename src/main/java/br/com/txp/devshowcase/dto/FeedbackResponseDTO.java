package br.com.txp.devshowcase.dto;

import br.com.txp.devshowcase.model.Feedback;

public record FeedbackResponseDTO(
    Long id,
    Integer rating,
    String comment,
    Long projectId
) {

    public static FeedbackResponseDTO fromEntity(Feedback feedback) {
        return new FeedbackResponseDTO(
            feedback.getId(),
            feedback.getRating(),
            feedback.getComment(),
            feedback.getProject().getId()
        );
    }
}
