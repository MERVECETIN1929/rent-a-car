package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.abstracts.ModelService;
import kodlama.io.rentacar.business.dto.request.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.response.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateCarResponse;
import kodlama.io.rentacar.business.rules.CarBusinessRules;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/*arabalar bakıma (maintenance) gönderilebilmelidir.
Bakımdan gelen araba yeniden kiralanabilir duruma gelmelidir.
Zaten bakımda olan araba bakıma gönderilememez. Kirada olan araba bakıma gönderilemez.
Bakımda olan araba araba listesinde görüntülenip görüntülenmeyeceğine kullanıcıdan bir parametre alarak gelmelidir veya gelmemelidir.*/
@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarBusinessRules rules;
    private ModelMapper modelMapper;
    private CarRepository carRepository;
    private ModelService modelService;

    @Override
    public CreateCarResponse add(CreateCarRequest createCarRequest) {
        Car car = modelMapper.map(createCarRequest, Car.class);
        car.setId(0);
        Car savecar = carRepository.save(car);
        CreateCarResponse createCarResponse = modelMapper.map(savecar, CreateCarResponse.class);

        return createCarResponse;
    }

    @Override
    public void delete(int car_id) {
        rules.checkIfCarExists(car_id);
        carRepository.deleteById(car_id);
    }

    @Override
    public UpdateCarResponse update(int car_id, UpdateCarRequest updateCarRequest) {
        rules.checkIfCarExists(car_id);
        Car car = modelMapper.map(updateCarRequest, Car.class);
        car.setId(car_id);
        Car savecar = carRepository.save(car);
        UpdateCarResponse updateCarResponse = modelMapper.map(savecar, UpdateCarResponse.class);
        return updateCarResponse;
    }

    @Override
    public GetCarResponse getById(int car_id) {
        Car car = carRepository.findById(car_id).orElseThrow();
        GetCarResponse getCarResponse = modelMapper.map(car, GetCarResponse.class);
        return getCarResponse;
    }

    @Override
    public List<GetAllCarsResponse> getAll(boolean includeMaintenance) {

        List<Car> cars = filterCarsByMaintenanceState(includeMaintenance);
        List<GetAllCarsResponse> getAllCarsResponses = cars.stream().map(car -> modelMapper.map(car, GetAllCarsResponse.class)).toList();
        return getAllCarsResponses;
    }

    private List<Car> filterCarsByMaintenanceState(boolean includeMaintenance) {
        if (includeMaintenance) {
            return carRepository.findAll();
        }
        return carRepository.findAllByStateIsNot(State.MAINTANCE);
    }


    public void changeStateCar(int carId, State state) {
        GetCarResponse getCarResponse = getById(carId);
        Car car = modelMapper.map(getCarResponse, Car.class);
        car.setId(carId);
        car.setState(state);
        UpdateCarRequest updateCarRequest = modelMapper.map(car, UpdateCarRequest.class);
        update(car.getId(), updateCarRequest);

    }

    // arabanın stateini dönecek
    @Override
    public State getStateCar(int carId) {
        return carRepository.getCarState(carId);
    }

}
