package kodlama.io.rentacar.business.rules;

import kodlama.io.rentacar.common.constants.Messages;
import kodlama.io.rentacar.common.dto.CreateRentedPaymentRequest;
import kodlama.io.rentacar.core.exceptions.BusinessException;
import kodlama.io.rentacar.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PaymentBusinessRules {
    private final PaymentRepository repository;

    public void checkIfBalanceIdEnough(double balance, double price) {
        if (balance < price) {
            throw new BusinessException(Messages.Payment.NotEnoughMoney);
        }
    }

    public void checkIfPaymentIsValid(CreateRentedPaymentRequest request) {
        if (!repository.existsByCardNumberAndCardHolderAndCardExpirationMonthAndCardExpirationYearAndCardCvv(
                request.getCardNumber(), request.getCardHolder(), request.getCardExpirationMonth(), request.getCardExpirationYear(), request.getCardCvv()
        )) {
            throw new BusinessException(Messages.Payment.NotFound);
        }

    }

    public void checkIfCardExists(String cardNumber) {
        if (repository.existsByCardNumber(cardNumber)) {
            throw new BusinessException(Messages.Payment.CardNumberAlreadyExists);
        }
    }

    public void checkIfPaymentExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Payment.NotFound);
        }
    }
}
