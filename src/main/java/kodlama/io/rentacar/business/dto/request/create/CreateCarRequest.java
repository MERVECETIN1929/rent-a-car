package kodlama.io.rentacar.business.dto.request.create;


import kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateCarRequest {
    private int modelYear;

    private String plate;

    private double dailyPrice;

    private State state;

    private int modelId;

}
