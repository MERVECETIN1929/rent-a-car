package kodlama.io.rentacar.api.controller;

import kodlama.io.rentacar.business.abstracts.RentalService;

import kodlama.io.rentacar.business.dto.request.create.CreateRentalRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateRentalRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateRentalResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllRentalResponse;
import kodlama.io.rentacar.business.dto.response.get.GetRentalResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateRentalResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rented")
public class RentedController {
    private final RentalService service;
    @GetMapping
    public List<GetAllRentalResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public GetRentalResponse getById(@PathVariable int id){
        return service.getById(id);
    }
    @PostMapping
    public CreateRentalResponse add(@RequestBody CreateRentalRequest request){
        return service.add(request);
    }
    @PutMapping("{id}")
    public UpdateRentalResponse update(@PathVariable int id, @RequestBody UpdateRentalRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
