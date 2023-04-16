package com.cdx.cdxlearningmaterials.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PosResponse {
    private Long id;
    private String name;
    private String description;
    private String account;
    private String facebook;
    private String line;
    private String phone;
    private String image;
    private Boolean status;
}
