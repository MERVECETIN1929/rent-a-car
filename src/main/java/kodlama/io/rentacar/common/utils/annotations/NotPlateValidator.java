package kodlama.io.rentacar.common.utils.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotPlateValidator implements ConstraintValidator<NotPlate, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // value alıan değer ama ben kurala uyup uymadığını nasıl anlayacam
        if (value.matches("^(?i)(?:(01|[2-8]\\d|90)[a-z]{1,3}\\s?\\d{2,4}|[a-z]{2}\\s?\\d{2,4}\\s?(01|[2-8]\\d|90))$")) {

        }
        return false;
    }
}
