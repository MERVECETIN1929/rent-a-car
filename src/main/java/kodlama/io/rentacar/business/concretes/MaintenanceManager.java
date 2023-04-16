package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.abstracts.MaintenanceService;
import kodlama.io.rentacar.business.dto.request.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllMaintenancesResponse;
import kodlama.io.rentacar.business.dto.response.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.response.get.GetMaintenanceResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateMaintenanceResponse;
import kodlama.io.rentacar.business.rules.MaintenanceBusinessRules;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.Maintenance;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class MaintenanceManager implements MaintenanceService {
    private final ModelMapper modelMapper;
    private final MaintenanceRepository maintenanceRepository;
    private final CarService carService;
    private final MaintenanceBusinessRules rules;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> listMaintenances = maintenanceRepository.findAll();
        List<GetAllMaintenancesResponse> maintenancesResponses = listMaintenances.
                stream().
                map(maintenance -> modelMapper.map(maintenance, GetAllMaintenancesResponse.class)).toList();
        return maintenancesResponses;
    }

    @Override
    public GetMaintenanceResponse getById(int maintenanceId) {

        Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElseThrow();
        GetMaintenanceResponse getMaintenanceResponse = modelMapper.map(maintenance, GetMaintenanceResponse.class);
        return getMaintenanceResponse;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest createMaintenanceRequest) {
        // araç bakım kontrolü
        rules.checkIfMaintenanceExists(createMaintenanceRequest.getCarId());
        // aracın kirada olup olmadığını kontrol et
        rules.checkCarAvailabilityForMaintenance(carService.getById(createMaintenanceRequest.getCarId()).getState());
        Maintenance maintenance = modelMapper.map(createMaintenanceRequest, Maintenance.class);
        maintenance.setId(0);
        maintenance.setDateIn(LocalDateTime.now());
        GetCarResponse getCarResponse = carService.getById(createMaintenanceRequest.getCarId());
        Car car = modelMapper.map(getCarResponse, Car.class);
        car.setId(createMaintenanceRequest.getCarId());
        car.setState(State.MAINTANCE);
        carService.update(car.getId(), modelMapper.map(car, UpdateCarRequest.class));
        Maintenance saveMaintenance = maintenanceRepository.save(maintenance);
        CreateMaintenanceResponse createMaintenanceResponse = modelMapper.map(saveMaintenance, CreateMaintenanceResponse.class);
        return createMaintenanceResponse;
    }


    @Override
    public UpdateMaintenanceResponse update(int maintenanceId, UpdateMaintenanceRequest updateMaintenanceRequest) {
        rules.checkIfMaintenanceExists(maintenanceId);

        Maintenance maintenance = modelMapper.map(updateMaintenanceRequest, Maintenance.class);
        maintenance.setId(maintenanceId);
        maintenanceRepository.save(maintenance);
        checkIfChangeIsRepaired(maintenanceId, updateMaintenanceRequest.getCarId());
        UpdateMaintenanceResponse updateMaintenanceResponse = modelMapper.map(maintenance, UpdateMaintenanceResponse.class);
        return updateMaintenanceResponse;
    }

    @Override
    public void delete(int maintenanceId) {
        makeCarAvailableIfIsRepaired(maintenanceId);
        maintenanceRepository.deleteById(maintenanceId);
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int carId) {
        rules.checkIfCarIsNotUnderMaintenance(carId);
        Maintenance maintenance = maintenanceRepository.findMaintenanceByCarIdAndIsRepairedFalse(carId);
        maintenance.setRepaired(true);
        maintenance.setDateOut(LocalDateTime.now());
        maintenanceRepository.save(maintenance);
        carService.changeStateCar(carId, State.AVAILABLE);
        GetMaintenanceResponse response = modelMapper.map(maintenance, GetMaintenanceResponse.class);

        return response;
    }


    private void makeCarAvailableIfIsRepaired(int maintenanceId) {
        int carId = maintenanceRepository.findById(maintenanceId).get().getCar().getId();
        if (maintenanceRepository.findById(maintenanceId).get().isRepaired()) {
            carService.changeStateCar(carId, State.AVAILABLE);
        }
    }

    public void checkIfChangeIsRepaired(int maintenanceId, int carId) {
        if (maintenanceRepository.getById(maintenanceId).isRepaired()) {
            carService.changeStateCar(carId, State.AVAILABLE);
        }
    }

}
/* private void checkIfMaintenance(int carId){
        if (!maintenanceRepository.existsByCarIdAndRepairedFalse(carId)) {
            throw new RuntimeException("Bakımda böyle bir araç bulunamadı!");
        }
    }*/