package com.dmdev.practice.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
