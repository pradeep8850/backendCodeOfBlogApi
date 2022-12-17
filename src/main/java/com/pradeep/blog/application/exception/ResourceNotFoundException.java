package com.pradeep.blog.application.exception;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    long fieldValue;
    String name;
    boolean isSuccess;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not  found with %s : %s", resourceName, fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String name) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, name));
        this.fieldName = fieldName;
        this.name = name;
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException(String resourceName, boolean isSuccess) {
        super(resourceName);
        this.resourceName = resourceName;
        this.isSuccess = isSuccess;
    }

}