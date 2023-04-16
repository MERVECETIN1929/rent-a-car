package kodlama.io.rentacar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RentacarApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentacarApplication.class, args);
    }


}/*
IOC de ki referansları görmek istiyorsan bunu kullan
ApplicationContext apc=SpringApplication.run(RentacarApplication.class, args);
for(String s: apc.getBeanDefinitionNames()){
    sout(s);
}
*/
