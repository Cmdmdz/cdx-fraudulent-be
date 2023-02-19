package com.cdx.cdxlearningmaterials.controllers;

import com.cdx.cdxlearningmaterials.repository.dao.Lesson;
import com.cdx.cdxlearningmaterials.repository.dao.Score;
import com.cdx.cdxlearningmaterials.services.LessonService;
import com.cdx.cdxlearningmaterials.services.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("/score")
public class ScoreController {
    private final ScoreService scoreService;
    @GetMapping("/{userId}")
    public ResponseEntity<?> getScoreByUserId (@Valid @PathVariable Long userId ) {
        return scoreService.findAllScoreOfUserId(userId);
    }
    @GetMapping("/{all}")
    public ResponseEntity<?> getScoreOfAllUser () {
        return scoreService.findScoreOfAllUser();
    }
    @PostMapping("/add")
    public ResponseEntity<?> addScore (@Valid @RequestBody Score request) {
        return scoreService.addScore(request);
    }
    @DeleteMapping("/{scoreId}")
    public ResponseEntity<?> deleteScoreById (@Valid @PathVariable Long scoreId) {
        return scoreService.deleteScoreById(scoreId);
    }
}
