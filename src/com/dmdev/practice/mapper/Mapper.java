package com.dmdev.practice.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
