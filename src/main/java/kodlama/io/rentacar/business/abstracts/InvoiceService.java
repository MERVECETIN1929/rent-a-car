package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.request.create.CreateInvoiceRequest;
import kodlama.io.rentacar.business.dto.request.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateInvoiceRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateInvoiceResponse;
import kodlama.io.rentacar.business.dto.response.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllInvoicesResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllMaintenancesResponse;
import kodlama.io.rentacar.business.dto.response.get.GetInvoiceResponse;
import kodlama.io.rentacar.business.dto.response.get.GetMaintenanceResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateInvoiceResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateMaintenanceResponse;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();
    GetInvoiceResponse getById(int invoiceId);
    CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest);
    UpdateInvoiceResponse update(int invoinceId, UpdateInvoiceRequest updateInvoiceRequest);
    void delete(int invoiceId);

}
