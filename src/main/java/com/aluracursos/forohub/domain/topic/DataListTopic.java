package com.aluracursos.forohub.domain.topic;

import java.time.LocalDateTime;

public record DataListTopic(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        Status status,
        String author,
        String course
) {
    public DataListTopic(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus(), topic.getAuthor(), topic.getCourse());
    }
}

