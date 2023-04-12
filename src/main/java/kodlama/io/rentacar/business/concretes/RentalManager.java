package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.abstracts.PaymentService;
import kodlama.io.rentacar.business.abstracts.RentalService;
import kodlama.io.rentacar.business.dto.request.create.CreateRentalRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateRentalRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateRentalResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllRentalResponse;
import kodlama.io.rentacar.business.dto.response.get.GetRentalResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateRentalResponse;
import kodlama.io.rentacar.common.dto.CreateRentedPaymentRequest;
import kodlama.io.rentacar.entities.Rental;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final ModelMapper mapper;
    private final RentalRepository repository;
    private final CarService carService;
    private final PaymentService paymentService;
    @Override
    public List<GetAllRentalResponse> getAll() {
        List<Rental> rentalList =repository.findAll();
        List<GetAllRentalResponse> getAllRentalRespons = rentalList.stream()
                .map(rental -> mapper.map(rental, GetAllRentalResponse.class)).toList();
        return getAllRentalRespons;
    }

    @Override
    public GetRentalResponse getById(int id) {
        Rental rental =repository.findById(id).orElseThrow();

        return mapper.map(rental, GetRentalResponse.class);
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
       checkIfCarRented(request.getCarId());
       checkIfCarMaintenance(request.getCarId());
       Rental rental =mapper.map(request, Rental.class);
       rental.setId(0);
       rental.setStartDate(LocalDateTime.now());

       // kontrol
        CreateRentedPaymentRequest paymentRequest=new CreateRentedPaymentRequest();
        mapper.map(request.getPaymentRequest(),paymentRequest);
        paymentRequest.setPrice(getTotalPrice(rental));
        paymentService.processRentedPayment(paymentRequest);
        // kontrol
       rental.setTotalPrice(getTotalPrice(rental));

       repository.save(rental);
       // burada bir hata oluşursa carı etkilemesin diye üstte yazılır
       carService.changeStateCar(request.getCarId(), State.RENTED);
       return mapper.map(rental, CreateRentalResponse.class);
    }



    @Override
    public UpdateRentalResponse update(int id, UpdateRentalRequest request) {
       checkIfCarRented(request.getCarId());
       Rental rental =mapper.map(request, Rental.class);
       rental.setId(id);
       rental.setTotalPrice(getTotalPrice(rental));
       repository.save(rental);
       return mapper.map(rental, UpdateRentalResponse.class);
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
        if(!carService.getById(carId).getState().equals(State.AVAILABLE)){
            throw new RuntimeException("Araç müsait değil!");
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
    private  double getTotalPrice(Rental rental) {
        return rental.getDailyPrice() * rental.getRentedForDays();
    }

}
