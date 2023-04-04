package kodlama.io.rentacar.business.dto.request.update;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class UpdateCarRequest {
    private int modelYear;

    private String plate;

    private double dailyPrice;
    @Enumerated(EnumType.STRING)
    private State state;

    private int modelId;
}
