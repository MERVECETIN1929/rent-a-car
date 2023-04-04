package kodlama.io.rentacar.business.dto.response.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class GetAllMaintenancesResponse {
    private int id;

    private int carId;


    @CreationTimestamp
    private Date dateIn;

    @CreationTimestamp
    private Date dateOut;

    private double cost;
    private boolean isRepaired;
    private String description;
}
