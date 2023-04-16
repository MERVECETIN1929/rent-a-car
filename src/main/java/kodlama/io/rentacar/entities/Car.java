package kodlama.io.rentacar.entities;

import jakarta.persistence.*;
import kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {
    @OneToMany(mappedBy = "car")
    List<Rental> rentalList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int modelYear;
    private String plate;
    private double dailyPrice;
    @Enumerated(EnumType.STRING)// VTEDE KAYIT ALIRKEN STRİNGİ VTYE KAYDEDER
    private State state;
    @ManyToOne
    @JoinColumn(name = "modelId")
    private Model model;
    @OneToMany(mappedBy = "car")
    private List<Maintenance> maintenances;

}
