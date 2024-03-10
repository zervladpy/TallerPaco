package com.zervladpy.tallerpaco.Core.Entities.Car;

import com.zervladpy.tallerpaco.Core.Entities.Brand.Brand;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Embeddable
public class CarDetails {
    @Column(name = "color")
    private String color;
    @JoinColumn(name = "idBrand")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.DETACH)
    private Brand brand;
}
