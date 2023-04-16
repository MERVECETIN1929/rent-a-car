package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
    boolean existsByCarIdAndIsRepairedFalse(int carId);

    Maintenance findMaintenanceByCarIdAndIsRepairedFalse(int carId);
}
