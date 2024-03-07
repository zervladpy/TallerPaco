package com.zervladpy.tallerpaco.Core.Entities.Car;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Embeddable
public class CarDetails {
    @Column(name = "color")
    private String color;
    @Column(name = "license_plate")
    private String plate;
    @Column(name = "mileage")
    private int mileage;
}
