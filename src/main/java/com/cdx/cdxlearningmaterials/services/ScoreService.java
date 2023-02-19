package com.cdx.cdxlearningmaterials.services;

import com.cdx.cdxlearningmaterials.model.response.GetScoreResponse;
import com.cdx.cdxlearningmaterials.repository.ScoreRepository;
import com.cdx.cdxlearningmaterials.repository.dao.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {
    private final ScoreRepository scoreRepository;
    public ResponseEntity<?> addScore (Score request) {
        if (Boolean.TRUE.equals(scoreRepository.existsByUserIdAndLessonId(request.getUserId(), request.getLessonId()))) {
            Score score = scoreRepository.findByUserIdAndLessonId(request.getUserId(), request.getLessonId());
            score.setScore(request.getScore());
            Score newScore = scoreRepository.save(score);
            return new ResponseEntity<>(newScore, HttpStatus.OK);
        } else {
            Score newScore = scoreRepository.save(request);
            return new ResponseEntity<>(newScore, HttpStatus.OK);
        }
    }
    public ResponseEntity<?> findScoreByUserId (Long userId) {
        List<Score> scores = scoreRepository.findAllByUserId(userId);
        return new ResponseEntity<>(GetScoreResponse.builder().scores(scores).build(),HttpStatus.OK);
    }
    public ResponseEntity<?> deleteScoreById (Long scoreId) {
        scoreRepository.deleteById(scoreId);
        return new ResponseEntity<>("Delete Score Successfully",HttpStatus.OK);
    }
}
