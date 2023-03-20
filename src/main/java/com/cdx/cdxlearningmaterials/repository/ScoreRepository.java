package com.cdx.cdxlearningmaterials.repository;

import com.cdx.cdxlearningmaterials.repository.dao.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findAllByUserId(Long userId);
    Score findByUserId(Long userId);

    @Query(value = "SELECT SUM(score) FROM score where user_id = ?1", nativeQuery = true)
    Long sumScoreByUserId (Long userId);

    Score findByUserIdAndAndGameEP(Long userId, String gameEp);

}
