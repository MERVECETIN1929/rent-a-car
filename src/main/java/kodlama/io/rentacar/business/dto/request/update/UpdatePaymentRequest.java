package kodlama.io.rentacar.business.dto.request.update;

import kodlama.io.rentacar.business.dto.request.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdatePaymentRequest extends PaymentRequest {
    private double balance;// bakiye
}
