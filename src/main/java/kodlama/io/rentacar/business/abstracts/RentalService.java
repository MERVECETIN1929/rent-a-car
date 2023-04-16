package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.request.create.CreateRentalRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateRentalRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateRentalResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllRentalResponse;
import kodlama.io.rentacar.business.dto.response.get.GetRentalResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateRentalResponse;

import java.util.List;

public interface RentalService {
    List<GetAllRentalResponse> getAll();

    GetRentalResponse getById(int id);

    CreateRentalResponse add(CreateRentalRequest request);

    UpdateRentalResponse update(int id, UpdateRentalRequest request);

    void delete(int id);
    //GetRentedResponse returnFromRented(int carId);
}
