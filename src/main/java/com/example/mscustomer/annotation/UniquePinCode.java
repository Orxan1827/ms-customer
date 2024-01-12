package com.example.mscustomer.annotation;

import com.example.mscustomer.validation.UniquePinCodeValidator;
import net.bytebuddy.dynamic.scaffold.RecordComponentRegistry;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {UniquePinCodeValidator.class})
@Target(value = FIELD)
@Retention(value =RUNTIME)
public @interface UniquePinCode {

    String message() default "Pin code must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
