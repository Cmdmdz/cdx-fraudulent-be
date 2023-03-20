package com.cdx.cdxlearningmaterials.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetScoreResponse {
    private Long userId;
    private String fullName;
    private Long score;

}
