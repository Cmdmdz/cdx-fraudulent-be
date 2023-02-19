package com.cdx.cdxlearningmaterials.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogInRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
