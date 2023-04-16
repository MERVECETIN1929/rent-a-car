package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.PaymentService;
import kodlama.io.rentacar.business.abstracts.PosService;
import kodlama.io.rentacar.business.dto.request.create.CreatePaymentRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdatePaymentRequest;
import kodlama.io.rentacar.business.dto.response.create.CreatePaymentResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAlLPaymentsResponse;
import kodlama.io.rentacar.business.dto.response.get.GetPaymentResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdatePaymentResponse;
import kodlama.io.rentacar.business.rules.PaymentBusinessRules;
import kodlama.io.rentacar.common.dto.CreateRentedPaymentRequest;
import kodlama.io.rentacar.entities.Payment;
import kodlama.io.rentacar.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapper mapper;
    private final PosService posService;
    private final PaymentBusinessRules rules;

    @Override
    public List<GetAlLPaymentsResponse> getAll() {
        List<Payment> payments = repository.findAll();
        List<GetAlLPaymentsResponse> getAlLPaymentsResponses = payments.stream()
                .map(payment -> mapper.map(payment, GetAlLPaymentsResponse.class)).toList();
        return getAlLPaymentsResponses;
    }

    @Override
    public GetPaymentResponse getById(int id) {
        Payment payment = repository.findById(id).orElseThrow();
        GetPaymentResponse getPaymentResponse = mapper
                .map(payment, GetPaymentResponse.class);
        return getPaymentResponse;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        rules.checkIfCardExists(request.getCardNumber());
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(0);
        repository.save(payment);
        CreatePaymentResponse createPaymentResponse = mapper.map(payment, CreatePaymentResponse.class);
        return createPaymentResponse;
    }

    @Override
    public UpdatePaymentResponse update(int id, UpdatePaymentRequest request) {
        rules.checkIfPaymentExists(id);
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(id);
        repository.save(payment);
        UpdatePaymentResponse updatePaymentResponse = mapper
                .map(payment, UpdatePaymentResponse.class);
        return updatePaymentResponse;
    }

    @Override
    public void delete(int id) {
        rules.checkIfPaymentExists(id);
        repository.deleteById(id);
    }

    @Override
    public void processRentedPayment(CreateRentedPaymentRequest request) {
        // kart bilgileri kontrol ediliyor
        rules.checkIfPaymentIsValid(request);
        // paymenti uniqe olan sütuna göre paymenti çek
        Payment payment = repository.findByCardNumber(request.getCardNumber());
        // balance ve price kontrolü yapıldı
        rules.checkIfBalanceIdEnough(payment.getBalance(), request.getPrice());
        // fake pos service
        posService.pay();
        payment.setBalance(payment.getBalance() - request.getPrice());
        repository.save(payment);
    }


}
