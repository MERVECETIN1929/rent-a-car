package kodlama.io.rentacar.api.controller;

import jakarta.validation.Valid;
import kodlama.io.rentacar.business.abstracts.PaymentService;
import kodlama.io.rentacar.business.dto.request.create.CreatePaymentRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdatePaymentRequest;
import kodlama.io.rentacar.business.dto.response.create.CreatePaymentResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAlLPaymentsResponse;
import kodlama.io.rentacar.business.dto.response.get.GetPaymentResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdatePaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService service;

    @GetMapping
    public List<GetAlLPaymentsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@PathVariable int id,@Valid @RequestBody UpdatePaymentRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

}
