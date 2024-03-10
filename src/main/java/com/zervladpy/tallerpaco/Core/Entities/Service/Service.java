package com.zervladpy.tallerpaco.Core.Entities.Service;

import com.zervladpy.tallerpaco.Core.Entities.ITEntity;
import com.zervladpy.tallerpaco.Core.Entities.Invoice.Invoice;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity(name = "service")
public class Service implements ITEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idService")
    private int idService;

    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "service", cascade = CascadeType.DETACH)
    private List<Invoice> invoice;

    @Override
    public String toString() {
        return name;
    }
}
