package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.request.create.CreateRentedRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateRentedRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateRentedResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllRentedResponse;
import kodlama.io.rentacar.business.dto.response.get.GetRentedResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateRentedResponse;

import java.util.List;

public interface RentedService {
    List<GetAllRentedResponse> getAll();
    GetRentedResponse getById(int id);
    CreateRentedResponse add(CreateRentedRequest request);
    UpdateRentedResponse update(int id,UpdateRentedRequest request);
    void delete(int id);
    //GetRentedResponse returnFromRented(int carId);
}
