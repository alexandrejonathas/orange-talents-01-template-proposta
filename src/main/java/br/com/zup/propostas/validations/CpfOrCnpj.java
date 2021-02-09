package br.com.zup.propostas.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@CPF
@CNPJ
@Constraint(validatedBy = { })
@ConstraintComposition(CompositionType.OR)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface CpfOrCnpj {
	
    String message() default "Precisa ser um CPF ou CNPJ válido.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
