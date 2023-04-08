package kodlama.io.rentacar.business.dto.response.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetMaintenanceResponse {
    private int id;
    private int carId;
    private Date dateIn;
    private Date dateOut;
    private boolean isRepaired;
    private String description;
}
