package kodlama.io.rentacar.core.utils.results;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResult<T extends Exception> {// t sadece exception alabilir demek
    private String type;
    private String message;
    private LocalDateTime timeStamp;


    public ExceptionResult(Class<T> type, String message) {
        this.timeStamp = LocalDateTime.now();
        this.type = type.getSimpleName();// type paketlerin yolunu verir
        this.message = message;
    }

    private String convertToUpperCaseWithUnderscores(String camelCaseString) {
        return camelCaseString.replaceAll("(.)(\\p{Upper})", "$1_$2").toUpperCase();
    }
}
