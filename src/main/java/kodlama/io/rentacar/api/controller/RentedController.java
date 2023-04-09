package kodlama.io.rentacar.api.controller;

import kodlama.io.rentacar.business.abstracts.RentedService;
import kodlama.io.rentacar.business.dto.request.create.CreateRentedRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateRentedRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateRentedResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllRentedResponse;
import kodlama.io.rentacar.business.dto.response.get.GetRentedResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateRentedResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rented")
public class RentedController {
    private final RentedService service;
    @GetMapping
    public List<GetAllRentedResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public GetRentedResponse getById(@PathVariable int id){
        return service.getById(id);
    }
    @PostMapping
    public CreateRentedResponse add(@RequestBody CreateRentedRequest request){
        return add(request);
    }
    @PutMapping("{id}")
    public UpdateRentedResponse update(@PathVariable int id, @RequestBody UpdateRentedRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
