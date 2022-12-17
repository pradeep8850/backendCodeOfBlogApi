package com.pradeep.blog.application.exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ApiResponse {
    private String message;
    private boolean isSuccess;
}
