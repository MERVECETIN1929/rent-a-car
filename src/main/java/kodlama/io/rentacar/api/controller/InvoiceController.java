package kodlama.io.rentacar.api.controller;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.abstracts.InvoiceService;
import kodlama.io.rentacar.business.dto.request.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.request.create.CreateInvoiceRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateInvoiceRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.response.create.CreateInvoiceResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllInvoicesResponse;
import kodlama.io.rentacar.business.dto.response.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.response.get.GetInvoiceResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateCarResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateInvoiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/invoices")
@AllArgsConstructor
public class InvoiceController {
    private final InvoiceService service;

    @GetMapping
    List<GetAllInvoicesResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    GetInvoiceResponse getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping
    CreateInvoiceResponse add(@RequestBody CreateInvoiceRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    UpdateInvoiceResponse update(@PathVariable int id, @RequestBody UpdateInvoiceRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        service.delete(id);
    }
}
