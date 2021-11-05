package com.alimesa.ordenpedido.librerias.dominio.validacion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;



@Constraint(validatedBy = StrictlyPositiveNumberValidator.class)
@ReportAsSingleViolation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface StrictlyPositiveNumber
{

    String message() default "";

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};


}

