package com.zervladpy.tallerpaco.Core.Entities.Car;

import com.zervladpy.tallerpaco.Core.Entities.Brand.Brand;
import com.zervladpy.tallerpaco.Core.Entities.Client.Client;
import com.zervladpy.tallerpaco.Core.Entities.ITEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
@Entity(name = "car")
public class Car implements ITEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Embedded
    private CarDetails details;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client", referencedColumnName = "id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "brand", referencedColumnName = "id")
    private Brand brand;

}
