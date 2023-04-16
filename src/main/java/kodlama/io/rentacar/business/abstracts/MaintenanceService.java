package kodlama.io.rentacar.business.abstracts;


import kodlama.io.rentacar.business.dto.request.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllMaintenancesResponse;
import kodlama.io.rentacar.business.dto.response.get.GetMaintenanceResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateMaintenanceResponse;

import java.util.List;

public interface MaintenanceService {
    List<GetAllMaintenancesResponse> getAll();

    GetMaintenanceResponse getById(int maintenanceId);

    CreateMaintenanceResponse add(CreateMaintenanceRequest createMaintenanceRequest);

    UpdateMaintenanceResponse update(int maintenanceId, UpdateMaintenanceRequest updateMaintenanceRequest);

    void delete(int maintenanceId);

    GetMaintenanceResponse returnCarFromMaintenance(int carId);
}
