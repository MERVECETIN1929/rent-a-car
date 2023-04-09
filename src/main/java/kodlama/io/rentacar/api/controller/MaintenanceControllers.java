package kodlama.io.rentacar.api.controller;

import kodlama.io.rentacar.business.abstracts.MaintenanceService;
import kodlama.io.rentacar.business.dto.request.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllMaintenancesResponse;
import kodlama.io.rentacar.business.dto.response.get.GetMaintenanceResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateMaintenanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.request.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.response.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/maintenances")
@AllArgsConstructor
public class MaintenanceControllers {
    private final MaintenanceService maintenanceService;
  /*  List<GetAllMaintenancesResponse> getAll();
    GetMaintenanceResponse getById(int maintenanceId);
    CreateMaintenanceResponse add(CreateMaintenanceRequest createMaintenanceRequest);
    UpdateMaintenanceResponse update(int maintenanceId, UpdateMaintenanceRequest updateMaintenanceRequest);
    void delete(int maintenanceId);*/
    @GetMapping
    public List<GetAllMaintenancesResponse> getAll(){
        return maintenanceService.getAll();
    }
    @GetMapping("/{maintenanceId}")
    public  GetMaintenanceResponse getById(@PathVariable int maintenanceId){
        return  maintenanceService.getById(maintenanceId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse add(@RequestBody  CreateMaintenanceRequest createMaintenanceRequest){
        return maintenanceService.add(createMaintenanceRequest);
    }
    @PutMapping("/{maintenanceId}")
    public UpdateMaintenanceResponse update(@PathVariable int maintenanceId,@RequestBody  UpdateMaintenanceRequest updateMaintenanceRequest){
        return maintenanceService.update(maintenanceId,updateMaintenanceRequest);
    }
    @PutMapping("/return")
    public GetMaintenanceResponse returnCarFromMaintenance(@RequestParam  int carId){
        return maintenanceService.returnCarFromMaintenance(carId);
    }
    @DeleteMapping("/{maintenanceId}")
    public void delete(@PathVariable int maintenanceId){
        maintenanceService.delete(maintenanceId);
    }
}
