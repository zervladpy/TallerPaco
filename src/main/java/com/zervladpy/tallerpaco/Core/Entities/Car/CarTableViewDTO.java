package com.zervladpy.tallerpaco.Core.Entities.Car;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class CarTableViewDTO {
    private int id;
    private String plate;
    private String color;
    private int mileage;
    private String carBrandName;
    private String ownerFullName;

}
