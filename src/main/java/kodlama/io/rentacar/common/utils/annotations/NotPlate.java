package kodlama.io.rentacar.common.utils.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotPlateValidator.class)
public @interface NotPlate {
    String message() default "Plate value cannot succesed";

    //? farklı kullanıcılar için farklı kurallar oluşturuyoruz
    Class<?>[] groups() default {};

    // özelleşmiş kişiye özgü mesaj bildirimi: hata hangi kullanıcı türü kaynaklı
    Class<? extends Payload>[] payload() default {};
}
