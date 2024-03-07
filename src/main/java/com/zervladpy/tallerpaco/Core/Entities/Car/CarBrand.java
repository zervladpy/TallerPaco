package com.zervladpy.tallerpaco.Core.Entities.Car;

import com.zervladpy.tallerpaco.Core.Entities.ITEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity(name = "car_brand")
public class CarBrand implements ITEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String brand;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "foundation_year", nullable = false)
    private int foundationYear;
    @Lob
    @Column(name = "logo")
    private Blob logo;

    @Override
    public String toString() {
        return brand;
    }
}
