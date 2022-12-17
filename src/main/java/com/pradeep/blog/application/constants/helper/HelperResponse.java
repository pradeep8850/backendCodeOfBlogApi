package com.pradeep.blog.application.constants.helper;

import com.pradeep.blog.application.exception.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class HelperResponse {

    public ResponseEntity<ApiResponse> getApiResponse(String message, boolean isSuccess, HttpStatus httpStatus) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(message, isSuccess), httpStatus);
    }
}
