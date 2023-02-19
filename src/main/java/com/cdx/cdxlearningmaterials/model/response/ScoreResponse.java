package com.cdx.cdxlearningmaterials.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreResponse {
    private Long lessonId;
    private String lessonName;
    private Long score;

}
