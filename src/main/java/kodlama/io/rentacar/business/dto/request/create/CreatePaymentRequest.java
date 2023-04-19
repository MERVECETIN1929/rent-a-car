package kodlama.io.rentacar.business.dto.request.create;

import jakarta.validation.constraints.Min;
import kodlama.io.rentacar.business.dto.request.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatePaymentRequest extends PaymentRequest {


    @Min(value = 1)
    private double balance;// bakiye
}
