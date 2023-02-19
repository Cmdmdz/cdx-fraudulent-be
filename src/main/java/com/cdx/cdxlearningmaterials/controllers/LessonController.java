package com.cdx.cdxlearningmaterials.controllers;

import com.cdx.cdxlearningmaterials.model.request.LogInRequest;
import com.cdx.cdxlearningmaterials.repository.dao.Lesson;
import com.cdx.cdxlearningmaterials.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LessonController {
    private final LessonService lessonService;
    @GetMapping("/get-all-lesson")
    public ResponseEntity<?> getAllLesson () {
        return lessonService.findAllLesson();
    }
    @PostMapping("/add-lesson")
    public ResponseEntity<?> addLesson (@Valid @RequestBody Lesson request) {
        return lessonService.addLesson(request);
    }
}
