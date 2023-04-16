package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.request.create.CreatePaymentRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdatePaymentRequest;
import kodlama.io.rentacar.business.dto.response.create.CreatePaymentResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAlLPaymentsResponse;
import kodlama.io.rentacar.business.dto.response.get.GetPaymentResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdatePaymentResponse;
import kodlama.io.rentacar.common.dto.CreateRentedPaymentRequest;

import java.util.List;

public interface PaymentService {
    List<GetAlLPaymentsResponse> getAll();

    GetPaymentResponse getById(int id);

    CreatePaymentResponse add(CreatePaymentRequest request);

    UpdatePaymentResponse update(int id, UpdatePaymentRequest request);

    void delete(int id);

    void processRentedPayment(CreateRentedPaymentRequest request);
}
