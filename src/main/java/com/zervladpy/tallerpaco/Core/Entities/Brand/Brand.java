package com.zervladpy.tallerpaco.Core.Entities.Brand;

import com.zervladpy.tallerpaco.Core.Entities.ITEntity;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Entity(name = "brand")
public class Brand implements ITEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "foundation_year", nullable = false)
    private int foundationYear;
}
