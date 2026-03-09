package com.aluracursos.forohub.controller;

import jakarta.validation.Valid;
import com.aluracursos.forohub.domain.topic.DataRegisterTopic;
import com.aluracursos.forohub.domain.topic.Status;
import com.aluracursos.forohub.domain.topic.Topic;
import com.aluracursos.forohub.domain.topic.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private TopicRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterTopic data) {
        // Validación de regla de negocio que no permite duplicados
        var isDuplicate = repository.existsByTitleAndMessage(data.title(), data.message());
        if (isDuplicate) {
            return ResponseEntity.badRequest().body("Topico con el mismo titulo y mensaje ya existe.");
        }

        var topic = new Topic(null, data.title(), data.message(), null, Status.OPEN, data.author(), data.course());
        repository.save(topic);

        return ResponseEntity.ok(topic);
    }
}
