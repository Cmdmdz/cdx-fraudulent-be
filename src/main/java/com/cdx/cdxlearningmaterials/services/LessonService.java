package com.cdx.cdxlearningmaterials.services;

import com.cdx.cdxlearningmaterials.model.response.GetLessonResponse;
import com.cdx.cdxlearningmaterials.repository.LessonRepository;
import com.cdx.cdxlearningmaterials.repository.dao.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    public ResponseEntity<?> findAllLesson () {
        List<Lesson> lessons = lessonRepository.findAll();
        return new ResponseEntity<>(GetLessonResponse.builder().lessons(lessons).build(), HttpStatus.OK);
    }
    public ResponseEntity<?> addLesson (Lesson lesson) {
        Lesson lessons = lessonRepository.save(lesson);
        return new ResponseEntity<>( "Lesson is added successfully", HttpStatus.OK);
    }
}
