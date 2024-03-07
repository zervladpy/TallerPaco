package com.zervladpy.tallerpaco.Core.Entities.Car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class CarWithUserFullNameDTO {
    private int id;
    private String plate;
    private String color;
    private int mileage;
    private String carBrandName;
    private String ownerFullName;
}
