package com.cdx.cdxlearningmaterials.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogInResponse {
    private Long userId;
    private String username;
    private String token;
    private String role;
    private String dob;

}
