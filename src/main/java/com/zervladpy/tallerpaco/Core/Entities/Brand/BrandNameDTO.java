package com.zervladpy.tallerpaco.Core.Entities.Brand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BrandNameDTO {
    private int id;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
