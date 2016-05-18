package br.com.cadastrocliente.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CPFCNPJValidator.class)
public @interface CPFCNPJ 
{

	String message() default "CPF/CNPJ inválido.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}