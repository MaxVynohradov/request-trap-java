package com.vynohradov.requesttrap.dto;

import lombok.Data;

@Data
public class RequestDataDto {
    private Long id;
    private String trapId;
    private String params;
    private String headers;
    private String body;
    private String updatedAt;
    private String createdAt;
}
