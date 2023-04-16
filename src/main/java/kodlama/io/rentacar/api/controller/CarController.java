package kodlama.io.rentacar.api.controller;

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
@RequestMapping("api/cars")
@AllArgsConstructor
public class CarController {
    private final CarService service;

    @GetMapping
    List<GetAllCarsResponse> getAll(@RequestParam(defaultValue = "true") boolean includeMaintenance) {
        return service.getAll(includeMaintenance);
    }

    @GetMapping("/{id}")
    GetCarResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    CreateCarResponse add(@RequestBody CreateCarRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    UpdateCarResponse update(@PathVariable int id, @RequestBody UpdateCarRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        service.delete(id);
    }


}
