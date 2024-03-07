package com.zervladpy.tallerpaco.Core.Entities.Car;

import com.zervladpy.tallerpaco.Core.Entities.Customer.Customer;
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private CarBrand carBrand;

}
