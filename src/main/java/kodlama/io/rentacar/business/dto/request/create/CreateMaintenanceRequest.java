package kodlama.io.rentacar.business.dto.request.create;

import jakarta.persistence.*;
import kodlama.io.rentacar.entities.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateMaintenanceRequest {

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
