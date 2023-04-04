package kodlama.io.rentacar.api.controller;

import kodlama.io.rentacar.business.abstracts.ModelService;
import kodlama.io.rentacar.business.dto.request.create.CreateModelRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateModelRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateModelResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllModelsResponse;
import kodlama.io.rentacar.business.dto.response.get.GetModelResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/models")
public class ModelController {
    private final ModelService service;
    @GetMapping
    List<GetAllModelsResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    GetModelResponse getById(@PathVariable int id){
        return service.getById(id);
    }
    @PostMapping
    CreateModelResponse add(@RequestBody CreateModelRequest request){
        return service.add(request);
    }
    @PutMapping("/{id}")
    UpdateModelResponse update(@PathVariable int id, @RequestBody UpdateModelRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        service.delete(id);
    }
}
