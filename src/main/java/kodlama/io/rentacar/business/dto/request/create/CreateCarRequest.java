package kodlama.io.rentacar.business.dto.request.create;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import kodlama.io.rentacar.common.constants.Regex;
import kodlama.io.rentacar.common.utils.annotations.NotFutureYear;
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
    @Min(1998)
    @NotFutureYear
    private int modelYear;
    @Pattern(regexp = Regex.plate)
    private String plate;
    @Min(1)
    private double dailyPrice;

    private State state;

    private int modelId;

}
