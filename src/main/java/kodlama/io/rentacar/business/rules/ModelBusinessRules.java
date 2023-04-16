package kodlama.io.rentacar.business.rules;

import kodlama.io.rentacar.common.constants.Messages;
import kodlama.io.rentacar.core.exceptions.BusinessException;
import kodlama.io.rentacar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ModelBusinessRules {
    private final ModelRepository modelRepository;

    public void checkIfModelExists(int model_id) {
        if (!modelRepository.existsById(model_id)) throw new BusinessException(Messages.Model.NotExists);
    }
}
