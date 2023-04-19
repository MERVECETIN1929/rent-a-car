package kodlama.io.rentacar.common.utils.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotFutureYearValidator.class)
public @interface NotFutureYear {
    // anatasyonlar:3 field
    String message() default "Model year value cannot be in the future";

    //? farklı kullanıcılar için farklı kurallar oluşturuyoruz
    Class<?>[] groups() default {};

    // özelleşmiş kişiye özgü mesaj bildirimi: hata hangi kullanıcı türü kaynaklı
    Class<? extends Payload>[] payload() default {};
}
