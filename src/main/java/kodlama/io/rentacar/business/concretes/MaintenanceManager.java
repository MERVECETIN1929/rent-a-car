package kodlama.io.rentacar.business.concretes;

import com.sun.tools.javac.Main;
import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.abstracts.MaintenanceService;
import kodlama.io.rentacar.business.dto.request.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllMaintenancesResponse;
import kodlama.io.rentacar.business.dto.response.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.response.get.GetMaintenanceResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateCarResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateMaintenanceResponse;

import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.Maintenance;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MaintenanceManager implements MaintenanceService {
    private final ModelMapper modelMapper;
    private final MaintenanceRepository maintenanceRepository;
    private final CarService carService;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> listMaintenances=maintenanceRepository.findAll();
        List<GetAllMaintenancesResponse> maintenancesResponses=listMaintenances.
                stream().
                map(maintenance -> modelMapper.map(maintenance,GetAllMaintenancesResponse.class)).toList();
        return maintenancesResponses;
    }

    @Override
    public GetMaintenanceResponse getById(int maintenanceId) {
        Maintenance maintenance=maintenanceRepository.findById(maintenanceId).orElseThrow();
        GetMaintenanceResponse getMaintenanceResponse=modelMapper.map(maintenance,GetMaintenanceResponse.class);
        return getMaintenanceResponse;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest createMaintenanceRequest) {
        Maintenance maintenance=modelMapper.map(createMaintenanceRequest,Maintenance.class);
        maintenance.setId(0);
        GetCarResponse getCarResponse=carService.getById(createMaintenanceRequest.getCarId());
        Car car=modelMapper.map(getCarResponse,Car.class);
        car.setId(createMaintenanceRequest.getCarId());
        car.setState(State.MAINTANCE);
        carService.update(car.getId(),modelMapper.map(car, UpdateCarRequest.class));
        Maintenance saveMaintenance=maintenanceRepository.save(maintenance);
        CreateMaintenanceResponse createMaintenanceResponse=modelMapper.map(saveMaintenance,CreateMaintenanceResponse.class);
        return createMaintenanceResponse;
    }

    @Override
    public UpdateMaintenanceResponse update(int maintenanceId, UpdateMaintenanceRequest updateMaintenanceRequest) {
        checkIfMaintenanceExists(maintenanceId);
        carService.checkIfCarExists(updateMaintenanceRequest.getCarId());
        Maintenance maintenance=modelMapper.map(updateMaintenanceRequest,Maintenance.class);
        maintenance.setId(maintenanceId);
        maintenanceRepository.save(maintenance);
        if(updateMaintenanceRequest.isRepaired()){
            carService.changeStateCar(updateMaintenanceRequest.getCarId(),State.AVAILABLE);
        }
       UpdateMaintenanceResponse updateMaintenanceResponse=modelMapper.map(maintenance,UpdateMaintenanceResponse.class);
        return updateMaintenanceResponse;
    }

    @Override
    public void delete(int maintenanceId) {
        GetMaintenanceResponse maintenance=getById(maintenanceId);
        maintenanceRepository.deleteById(maintenanceId);
        carService.changeStateCar(maintenance.getCarId(),State.AVAILABLE);

    }
    public void checkIfMaintenanceExists(int maintenanceId){
        if(!maintenanceRepository.existsById(maintenanceId)) throw new RuntimeException("Aranan kayıt  bulunamadı");
    }

}
