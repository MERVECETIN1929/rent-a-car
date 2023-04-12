package kodlama.io.rentacar.business.dto.request.create;

import kodlama.io.rentacar.business.dto.request.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRentalRequest {


    private int carId;

    private int rentedForDays;
    private double dailyPrice;
    // kart bilgileri gilmeli
    private PaymentRequest paymentRequest;

}
