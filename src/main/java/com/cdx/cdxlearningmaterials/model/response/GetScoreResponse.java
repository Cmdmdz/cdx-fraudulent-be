package com.cdx.cdxlearningmaterials.model.response;

import com.cdx.cdxlearningmaterials.repository.dao.Lesson;
import com.cdx.cdxlearningmaterials.repository.dao.Score;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetScoreResponse {
    private List<Score> scores;
}
