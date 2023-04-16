package kodlama.io.rentacar.business.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentRequest {
    // günümüzün değerleri nasıl alınır
    @NotBlank(message = "Kart Numarası boş bırakılamaz")// String @NotNull ->sayısal mesajlar
    @Length(min = 16, max = 16, message = "Kart numarası 16 haneden oluşmalıdır.")
    private String cardNumber;

    @NotBlank
    @Length(min = 5)
    private String cardHolder;

    @NotNull
    @Min(value = 2023)
    private int cardExpirationYear;

    @NotNull
    @Max(value = 12)
    @Min(value = 1)
    private int cardExpirationMonth;

    @NotBlank
    @Length(min = 3, max = 3)
    private String cardCvv;
}
