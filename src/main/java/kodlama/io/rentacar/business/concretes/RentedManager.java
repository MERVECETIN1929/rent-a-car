package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.abstracts.RentedService;
import kodlama.io.rentacar.business.dto.request.create.CreateRentedRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateRentedRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateRentedResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllRentedResponse;
import kodlama.io.rentacar.business.dto.response.get.GetRentedResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateRentedResponse;
import kodlama.io.rentacar.entities.Rented;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.RentedRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RentedManager implements RentedService {
    private final ModelMapper mapper;
    private final RentedRepository repository;
    private final CarService carService;
    @Override
    public List<GetAllRentedResponse> getAll() {
        List<Rented> rentedList=repository.findAll();
        List<GetAllRentedResponse> getAllRentedResponses=rentedList.stream()
                .map(rented -> mapper.map(rented, GetAllRentedResponse.class)).toList();
        return getAllRentedResponses;
    }

    @Override
    public GetRentedResponse getById(int id) {
        Rented rented=repository.findById(id).orElseThrow();

        return mapper.map(rented,GetRentedResponse.class);
    }

    @Override
    public CreateRentedResponse add(CreateRentedRequest request) {
       checkIfCarRented(request.getCarId());
       checkIfCarMaintenance(request.getCarId());
       Rented rented=mapper.map(request,Rented.class);
       rented.setId(0);
       rented.setStartDate(LocalDateTime.now());
       rented.setTotalPrice(getTotalPrice(rented));
       repository.save(rented);
       // burada bir hata oluşursa carı etkilemesin diye üstte yazılır
       carService.changeStateCar(request.getCarId(), State.RENTED);
       return mapper.map(rented,CreateRentedResponse.class);
    }



    @Override
    public UpdateRentedResponse update(int id, UpdateRentedRequest request) {
       checkIfCarRented(request.getCarId());
       Rented rented=mapper.map(request, Rented.class);
       rented.setId(id);
       rented.setTotalPrice(getTotalPrice(rented));
       repository.save(rented);
       return mapper.map(rented, UpdateRentedResponse.class);
    }


    @Override
    public void delete(int id) {
        checkIfRentedExists(id);
        int carId= repository.findById(id).get().getCar().getId();
        carService.changeStateCar(carId,State.AVAILABLE);
        repository.deleteById(id);
    }


   /* public GetRentedResponse returnFromRented(int carId) {
        Rented rented=repository.findById(id).orElseThrow();
        rented.setId(id);
        rented.setRented(true);
        carService.changeStateCar(rented.getCar().getId(),State.AVAILABLE);
        repository.save(rented);
        GetRentedResponse getRentedResponse=mapper.map(rented,GetRentedResponse.class);
        return getRentedResponse;
    }*/
    private void checkIfCarRented(int carId){
        if(repository.existsByCarIdAndIsRentedFalse(carId)){
            throw new RuntimeException("araç kiralik durumda");
        }
    }
    private void checkIfCarMaintenance(int carId){
        if(carService.getStateCar(carId)==State.MAINTANCE){
            throw new RuntimeException("Araç bakımda ");
        }
    }
    private void  checkIfRentedExists(int rentedId){
        if(!repository.existsById(rentedId)){
            throw new NotFoundException("Aracın rented tablosunda kaydı yok");
        }
    }
    // totalPrice değeri kullanım alanı fazla olduğu için ayrı metot halinde tanımlanması gerekir
    // alt+ctrl+m => seçtiğin satırları fonksiyon olarak dışarı çıkarır
    private  double getTotalPrice(Rented rented) {
        return rented.getDailyPrice() * rented.getRentedForDays();
    }

}
