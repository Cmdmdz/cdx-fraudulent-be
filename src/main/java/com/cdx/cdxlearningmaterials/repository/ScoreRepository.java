package com.cdx.cdxlearningmaterials.repository;

import com.cdx.cdxlearningmaterials.repository.dao.Score;
import com.cdx.cdxlearningmaterials.repository.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findAllByUserId(Long userId);
    Boolean existsByUserIdAndLessonId(Long userId, Long lessonId);
    Score findByUserIdAndLessonId(Long userId, Long lessonId);
}
