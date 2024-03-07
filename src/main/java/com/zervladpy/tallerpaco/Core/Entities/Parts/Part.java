package com.zervladpy.tallerpaco.Core.Entities.Parts;

import com.zervladpy.tallerpaco.Core.Entities.ITEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
@Entity(name = "part")
public class Part implements ITEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String reference;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "stock", nullable = false)
    private int stock;

}
