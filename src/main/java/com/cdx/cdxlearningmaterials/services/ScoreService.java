package com.cdx.cdxlearningmaterials.services;

import com.cdx.cdxlearningmaterials.model.response.GetAllScoreResponse;
import com.cdx.cdxlearningmaterials.model.response.GetScoreResponse;
import com.cdx.cdxlearningmaterials.model.response.ScoreResponse;
import com.cdx.cdxlearningmaterials.model.response.UserResponse;
import com.cdx.cdxlearningmaterials.repository.LessonRepository;
import com.cdx.cdxlearningmaterials.repository.ScoreRepository;
import com.cdx.cdxlearningmaterials.repository.UserRepository;
import com.cdx.cdxlearningmaterials.repository.dao.Lesson;
import com.cdx.cdxlearningmaterials.repository.dao.Score;
import com.cdx.cdxlearningmaterials.repository.dao.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> addScore(Score request) {
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

    public ResponseEntity<?> findAllScoreOfUserId(Long userId) {
        return new ResponseEntity<>(findScoreByUserId(userId), HttpStatus.OK);
    }

    public ResponseEntity<?> findScoreOfAllUser() {
        List<User> allUser = userRepository.findAll();
        List<GetScoreResponse> scoreResponses = allUser.stream()
                .map(user -> findScoreByUserId(user.getUserId()))
                .toList();
        return new ResponseEntity<>(GetAllScoreResponse.builder().usersScore(scoreResponses).build(), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteScoreById(Long scoreId) {
        scoreRepository.deleteById(scoreId);
        return new ResponseEntity<>("Delete Score Successfully", HttpStatus.OK);
    }


    private GetScoreResponse findScoreByUserId(Long userId) {
        User user = userRepository.findByUserId(userId);
        List<Score> scores = scoreRepository.findAllByUserId(userId);
        List<Long> ids = new ArrayList<>();
        scores.forEach(score -> ids.add(score.getLessonId()));
        var lessons = lessonRepository.findAllById(ids);
        UserResponse userResponse = UserResponse.builder().userId(user.getUserId()).firstname(user.getFirstname()).lastname(user.getLastname()).build();
        List<ScoreResponse> scoreResponses = new ArrayList<>();
        scores.forEach(score -> {
            Lesson lesson = lessons.stream()
                    .filter(lessonElement -> lessonElement.getLessonId().equals(score.getLessonId()))
                    .toList().get(0);
            ScoreResponse scoreResponse = ScoreResponse.builder()
                    .lessonId(lesson.getLessonId())
                    .lessonName(lesson.getLessonName())
                    .scoreId(score.getScoreId())
                    .score(score.getScore())
                    .build();
            scoreResponses.add(scoreResponse);
        });
        return GetScoreResponse.builder().user(userResponse).scores(scoreResponses).build();
    }
}
