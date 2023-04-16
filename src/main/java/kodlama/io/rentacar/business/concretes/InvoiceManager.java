package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.InvoiceService;
import kodlama.io.rentacar.business.dto.request.create.CreateInvoiceRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateInvoiceRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateInvoiceResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllInvoicesResponse;
import kodlama.io.rentacar.business.dto.response.get.GetInvoiceResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateInvoiceResponse;
import kodlama.io.rentacar.entities.Invoice;
import kodlama.io.rentacar.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class InvoiceManager implements InvoiceService {
    private final ModelMapper mapper;
    private final InvoiceRepository repository;
    @Override
    public List<GetAllInvoicesResponse> getAll() {
        List<Invoice> invoices=repository.findAll();
        List<GetAllInvoicesResponse> getAllInvoicesResponses=invoices.stream()
                .map(invoice -> mapper.map(invoice,GetAllInvoicesResponse.class)).toList();

        return getAllInvoicesResponses;
    }

    @Override
    public GetInvoiceResponse getById(int invoiceId) {
        Invoice invoice=repository.findById(invoiceId).orElseThrow();
        GetInvoiceResponse getInvoiceResponse=mapper.map(invoice, GetInvoiceResponse.class);
        return getInvoiceResponse;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest) {
        Invoice invoice=mapper.map(createInvoiceRequest,Invoice.class);
        invoice.setId(0);
        invoice.setTotalPrice(getTotalPrice(invoice));
        repository.save(invoice);
        CreateInvoiceResponse createInvoiceResponse=mapper.map(invoice,CreateInvoiceResponse.class);
        return createInvoiceResponse;
    }

    @Override
    public UpdateInvoiceResponse update(int invoiceId, UpdateInvoiceRequest updateInvoiceRequest) {
        checkIfInvoiceExists(invoiceId);
        Invoice invoice=mapper.map(updateInvoiceRequest,Invoice.class);
        invoice.setId(invoiceId);
        invoice.setTotalPrice(getTotalPrice(invoice));
        repository.save(invoice);
        UpdateInvoiceResponse updateInvoiceResponse=mapper.map(invoice, UpdateInvoiceResponse.class);
        return updateInvoiceResponse;
    }

    @Override
    public void delete(int invoiceId) {
        checkIfInvoiceExists(invoiceId);
        repository.deleteById(invoiceId);
    }
    private double getTotalPrice(Invoice invoice) {
        return invoice.getDailyPrice() * invoice.getRentedForDays();
    }

    private void checkIfInvoiceExists(int id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Fatura bilgisi bulunamadı.");
        }
    }
}
