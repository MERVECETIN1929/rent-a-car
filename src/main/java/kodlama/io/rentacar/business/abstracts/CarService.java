package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.request.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.request.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.response.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.response.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.response.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.response.update.UpdateCarResponse;
import kodlama.io.rentacar.entities.enums.State;

import java.util.List;
/*arabalar bakıma (maintenance) gönderilebilmelidir.
Bakımdan gelen araba yeniden kiralanabilir duruma gelmelidir.
Zaten bakımda olan araba bakıma gönderilememez. Kirada olan araba bakıma gönderilemez.
Bakımda olan araba araba listesinde görüntülenip görüntülenmeyeceğine kullanıcıdan bir parametre alarak gelmelidir veya gelmemelidir.*/
public interface CarService {
    // ekleme silme güncelleme getirme toplugetirme
    CreateCarResponse add(CreateCarRequest brand);
    void delete(int brand_id);
    UpdateCarResponse update(int brand_id, UpdateCarRequest brand);
    GetCarResponse getById(int brand_id);
    List<GetAllCarsResponse> getAll();
    void changeStateCar(int carId, State state);
    void checkIfCarExists(int car_id);


}
