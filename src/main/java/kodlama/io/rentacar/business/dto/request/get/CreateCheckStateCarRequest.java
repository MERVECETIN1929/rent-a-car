package kodlama.io.rentacar.business.dto.request.get;

import jakarta.validation.constraints.AssertTrue;
import kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCheckStateCarRequest {
    List<State> stateList;
}
