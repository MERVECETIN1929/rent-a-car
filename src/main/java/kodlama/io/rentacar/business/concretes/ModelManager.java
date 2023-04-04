package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.BrandService;
import kodlama.io.rentacar.business.abstracts.ModelService;
import kodlama.io.rentacar.business.dto.request.create.CreateModelRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateModelRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateModelResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllModelsResponse;
import kodlama.io.rentacar.business.dto.response.get.GetModelResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateModelResponse;
import kodlama.io.rentacar.entities.Model;
import kodlama.io.rentacar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import org.modelmapper.internal.bytebuddy.matcher.StringMatcher;
import org.springframework.stereotype.Service;

import org.springframework.ui.ModelMap;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager  implements ModelService {
    private ModelMapper modelMapper;
    private ModelRepository modelRepository;
    private BrandService brandService;
    @Override
    public CreateModelResponse add(CreateModelRequest createModelRequest) {
        Model model=modelMapper.map(createModelRequest,Model.class);
        model.setId(0);
        Model saveModel=modelRepository.save(model);
        CreateModelResponse createModelResponse=modelMapper.map(model,CreateModelResponse.class);
        return createModelResponse;
    }

    @Override
    public void delete(int model_id) {
        checkIfModelExists(model_id);
        modelRepository.deleteById(model_id);
    }

    @Override
    public UpdateModelResponse update(int model_id, UpdateModelRequest model_request) {
        checkIfModelExists(model_id);
        brandService.checkIfBrandExists(model_request.getBrandId());
        Model model=modelMapper.map(model_request,Model.class);
        model.setId(model_id);
        Model saveModel=modelRepository.save(model);
        UpdateModelResponse updateModelResponse=modelMapper.map(saveModel,UpdateModelResponse.class);
        return updateModelResponse;
    }

    @Override
    public GetModelResponse getById(int model_id) {
        Model model=modelRepository.findById(model_id).orElseThrow();
        GetModelResponse getModelResponse=modelMapper.map(model,GetModelResponse.class);
        return getModelResponse;
    }

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models=modelRepository.findAll();
        List<GetAllModelsResponse> getAllModelsResponses=models.stream().map(model->modelMapper.map(model, GetAllModelsResponse.class)).toList();
        return getAllModelsResponses;
    }
    public void checkIfModelExists(int model_id){
        if(!modelRepository.existsById(model_id)) throw new RuntimeException("aranan model bulunamadı");
    }
}
