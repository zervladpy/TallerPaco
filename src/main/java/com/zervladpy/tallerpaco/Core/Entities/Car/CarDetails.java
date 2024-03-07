package com.zervladpy.tallerpaco.Core.Entities.Car;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
@Embeddable
public class CarDetails {
    @Column(name = "color")
    private String color;
    @Column(name = "license_plate")
    private String plate;
    @Column(name = "mileage")
    private int mileage;
}
