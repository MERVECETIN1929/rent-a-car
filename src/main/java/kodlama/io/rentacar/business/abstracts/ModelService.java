package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.request.create.CreateModelRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateModelRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateModelResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllModelsResponse;
import kodlama.io.rentacar.business.dto.response.get.GetModelResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateModelResponse;

import java.util.List;

public interface ModelService {
    // ekleme silme güncelleme getirme toplugetirme
    CreateModelResponse add(CreateModelRequest brand);
    void delete(int brand_id);
    UpdateModelResponse update(int brand_id, UpdateModelRequest brand);
    GetModelResponse getById(int brand_id);
    List<GetAllModelsResponse> getAll();
    void checkIfModelExists(int model_id);
}
