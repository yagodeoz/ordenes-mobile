package com.alimesa.ordenpedido.librerias.dominio.validacion;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

 
public class InDateRangeValidator implements ConstraintValidator<InDateRange, Date> {


    
    @Override
    public void initialize(InDateRange constraintAnnotation) {}
    
    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
            return true;
    }    
}