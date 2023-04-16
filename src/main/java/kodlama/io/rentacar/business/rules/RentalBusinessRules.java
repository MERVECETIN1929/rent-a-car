package kodlama.io.rentacar.business.rules;

import kodlama.io.rentacar.common.constants.Messages;
import kodlama.io.rentacar.core.exceptions.BusinessException;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class RentalBusinessRules {
    private final RentalRepository repository;

    public void checkIfCarRented(State state) {
        if (!state.equals(State.AVAILABLE)) {
            throw new BusinessException(Messages.Rental.CarNotAvailable);
        }
    }

    public void checkIfCarMaintenance(State state) {
        if (state == State.MAINTANCE) {
            throw new BusinessException(Messages.Rental.CarNotAvailable);
        }
    }

    public void checkIfRentedExists(int rentedId) {
        if (!repository.existsById(rentedId)) {
            throw new BusinessException(Messages.Rental.NotExists);
        }
    }
}
