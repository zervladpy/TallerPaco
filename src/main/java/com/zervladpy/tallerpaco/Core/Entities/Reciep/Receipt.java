package com.zervladpy.tallerpaco.Core.Entities.Reciep;

import com.zervladpy.tallerpaco.Core.Entities.Client.Client;
import com.zervladpy.tallerpaco.Core.Entities.Parts.Part;
import com.zervladpy.tallerpaco.Core.Entities.ITEntity;
import com.zervladpy.tallerpaco.Core.Utils.Enums.ServiceType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Entity(name = "receipt")
public class Receipt implements ITEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date")
    private LocalDate receiptDate;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Client client;
    @Enumerated(EnumType.STRING)
    @Column(name = "service_type")
    private ServiceType serviceType;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Part> parts;
    @Column(name = "labor_price")
    private double laborPrice;

}
