package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findByCardNumber(String cardNumber);//rentalda kullanılacak

    boolean existsByCardNumber(String cardNumber);

    boolean existsByCardNumberAndCardHolderAndCardExpirationMonthAndCardExpirationYearAndCardCvv(
            String cardNumber, String cardHolder, int cardExpirationMonth, int cardExpirationYear, String cardCvv
    );
    //SPel-> spring experssion language
 /*   @Query("SELECT CASE WHEN COUNT(p)>0 THEN true ELSE false END"+
    " FROM Payment p WHERE p.cardNumber= :#{#paymentRequest.cardNumber} "+
    "And p.cardExpirationMonth= :#{#paymentRequest.cardExpirationMonth} And"+
            " p.cardExpirationYear= :#{#paymentRequest.cardExpirationYear} AND"+
            " p.cardCvv= :#{#paymentRequest.cardCvv}")
    boolean existsByCardNumberAndCardHolderAndCardExpirationMonthAndCardExpirationYearAndCardCvv(
            @Param("paymentRequest") PaymentRequest paymentRequest
    );*/
}
