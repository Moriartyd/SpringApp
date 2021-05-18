package ru.galeev.springapp.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//Аннотация для исключения поля из сериализации в JSON
public @Retention(RetentionPolicy.RUNTIME) @interface Hidden {}
