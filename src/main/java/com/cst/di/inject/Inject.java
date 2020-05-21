package com.cst.di.inject;

import java.lang.annotation.Documented;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.cst.di.scope.BeanScope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, CONSTRUCTOR, FIELD})
@Retention(RUNTIME)
@Documented
public @interface Inject {
    BeanScope value() default BeanScope.SINGLETON;
}
