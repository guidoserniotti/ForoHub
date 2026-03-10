package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topic.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
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

        repository.save(new Topic(data));

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<DataListTopic>> list(@PageableDefault(size = 10, sort = "creationDate", direction = Sort.Direction.ASC) Pageable pagination) {
        var page = repository.findAll(pagination).map(DataListTopic::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var topicOptional = repository.findById(id);

        if (topicOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topic = topicOptional.get();
        return ResponseEntity.ok(new DataListTopic(topic));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid DataUpdateTopic data) {
        var topicOptional = repository.findById(id);

        if (topicOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topic = topicOptional.get();
        topic.updateData(data);

        return ResponseEntity.ok(new DataListTopic(topic));
    }
}
