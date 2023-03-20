package com.cdx.cdxlearningmaterials.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreRequest {
    @NonNull
    private Long userId;
    @NonNull
    private Long score;
    @NotBlank
    private String GameEp;
}
