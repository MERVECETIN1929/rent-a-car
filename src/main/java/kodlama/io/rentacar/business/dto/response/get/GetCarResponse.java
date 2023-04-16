package kodlama.io.rentacar.business.dto.response.get;

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
public class GetCarResponse {
    private int id;
    private int modelYear;

    private String plate;

    private double dailyPrice;
    @Enumerated(EnumType.STRING)// VTEDE KAYIT ALIRKEN STRİNGİ VTYE KAYDEDER
    private State state;

    private int modelId;
    private String modelName;
    private String modelBrandName;
}
