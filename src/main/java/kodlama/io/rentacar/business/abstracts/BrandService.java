package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.request.create.CreateBrandRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateBrandRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateBrandResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllBrandsResponse;
import kodlama.io.rentacar.business.dto.response.get.GetBrandResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateBrandResponse;

import java.util.List;

public interface BrandService {
    // ekleme silme güncelleme getirme toplugetirme
    CreateBrandResponse add(CreateBrandRequest brand);

    void delete(int brand_id);

    UpdateBrandResponse update(int brand_id, UpdateBrandRequest brand);

    GetBrandResponse getById(int brand_id);

    List<GetAllBrandsResponse> getAll();
    //void checkIfBrandExists(int brand_id);
}
