package com.zervladpy.tallerpaco.Core.Entities.Car;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class CarPlateDTO {
    private String plate;

    @Override
    public String toString() {
        return plate;
    }
}
