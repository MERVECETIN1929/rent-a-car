package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.BrandService;
import kodlama.io.rentacar.business.dto.request.create.CreateBrandRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateBrandRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateBrandResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllBrandsResponse;
import kodlama.io.rentacar.business.dto.response.get.GetBrandResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateBrandResponse;
import kodlama.io.rentacar.entities.Brand;
import kodlama.io.rentacar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private ModelMapper modelMapper;
    private BrandRepository repository;
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse add(CreateBrandRequest createBrandRequest) {
        Brand brand=modelMapper.map(createBrandRequest,Brand.class);
        brand.setId(0);
        repository.save(brand);
        CreateBrandResponse createBrandResponse=modelMapper.map(brand,CreateBrandResponse.class);
        return createBrandResponse;
    }

    @Override
    public void delete(int brand_id) {
        repository.deleteById(brand_id);
    }

    @Override
    public UpdateBrandResponse update(int brand_id, UpdateBrandRequest updateBrandRequest) {
        checkIfBrandExists(brand_id);
        Brand brand=modelMapper.map(updateBrandRequest,Brand.class);
        brand.setId(brand_id);
        repository.save(brand);
        UpdateBrandResponse updateBrandResponse=modelMapper.map(brand,UpdateBrandResponse.class);
        return updateBrandResponse;
    }

    @Override
    public GetBrandResponse getById(int brand_id) {
        Brand brand=repository.findById(brand_id).orElseThrow();
        GetBrandResponse getBrandResponse=modelMapper.map(brand,GetBrandResponse.class);
        return getBrandResponse;
    }

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands=repository.findAll();
        List<GetAllBrandsResponse> getAllBrandsResponses=brands.stream().map(brand->modelMapper.map(brand,GetAllBrandsResponse.class)).toList();
        return getAllBrandsResponses;
    }
    public void checkIfBrandExists(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("Marka bulunamadı");
    }
}
