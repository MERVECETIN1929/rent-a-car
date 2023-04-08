package kodlama.io.rentacar.business.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateRentedRequest {

    private int carId;
    private int rentedForDays;
    private double totalPrice;
    private LocalDateTime startDate;
}
