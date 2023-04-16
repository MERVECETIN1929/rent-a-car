package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.request.create.CreateInvoiceRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateInvoiceRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateInvoiceResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllInvoicesResponse;
import kodlama.io.rentacar.business.dto.response.get.GetInvoiceResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateInvoiceResponse;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();

    GetInvoiceResponse getById(int invoiceId);

    CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest);

    UpdateInvoiceResponse update(int invoinceId, UpdateInvoiceRequest updateInvoiceRequest);

    void delete(int invoiceId);

}
