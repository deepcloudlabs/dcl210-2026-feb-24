package com.example.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Schedule {
    String dayOfWeek() default "Mon";
    int hour() default 12;
}
