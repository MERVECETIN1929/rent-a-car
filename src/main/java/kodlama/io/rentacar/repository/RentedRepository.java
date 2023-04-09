package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.Rented;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentedRepository extends JpaRepository<Rented,Integer> {
    boolean existsByCarIdAndIsRentedFalse(int carId);
}
