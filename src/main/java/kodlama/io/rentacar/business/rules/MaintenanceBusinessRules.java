package kodlama.io.rentacar.business.rules;

import kodlama.io.rentacar.common.constants.Messages;
import kodlama.io.rentacar.core.exceptions.BusinessException;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MaintenanceBusinessRules {
    private final MaintenanceRepository maintenanceRepository;

    public void checkIfMaintenanceExists(int carId) {
        if (maintenanceRepository.existsByCarIdAndIsRepairedFalse(carId)) {
            throw new BusinessException(Messages.Maintenance.NotExists);
        }
    }

    public void checkIfCarIsNotUnderMaintenance(int maintenanceId) {
        if (!maintenanceRepository.existsById(maintenanceId)) {
            throw new BusinessException(Messages.Maintenance.CarNotExists);
        }

    }


    public void checkCarAvailabilityForMaintenance(State state) {
        if (state.equals(State.RENTED)) {
            throw new BusinessException(Messages.Maintenance.CarIsRented);
        }
    }
}
