package kodlama.io.rentacar.business.dto.response.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateMaintenanceResponse {
    private int id;
    private int carId;
    private boolean isRepaired;
    private String description;
}
