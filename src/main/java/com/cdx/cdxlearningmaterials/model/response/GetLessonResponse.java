package com.cdx.cdxlearningmaterials.model.response;

import com.cdx.cdxlearningmaterials.repository.dao.Lesson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetLessonResponse {
    private List<Lesson> lessons;
}
