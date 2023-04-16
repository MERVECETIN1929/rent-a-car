package kodlama.io.rentacar.business.rules;

import kodlama.io.rentacar.common.constants.Messages;
import kodlama.io.rentacar.core.exceptions.BusinessException;
import kodlama.io.rentacar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CarBusinessRules {
    private final CarRepository carRepository;

    public void checkIfCarExists(int car_id) {
        if (!carRepository.existsById(car_id)) throw new BusinessException(Messages.Car.NotExists);
    }
}
