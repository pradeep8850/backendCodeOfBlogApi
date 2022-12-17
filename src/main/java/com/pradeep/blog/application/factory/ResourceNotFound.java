package com.pradeep.blog.application.factory;

import com.pradeep.blog.application.exception.ResourceNotFoundException;

public class ResourceNotFound {

    public static ResourceNotFoundException resourceNotFoundException(String name, String id, Integer resourceId) {
        return new ResourceNotFoundException(name, id, resourceId);
    }
}
