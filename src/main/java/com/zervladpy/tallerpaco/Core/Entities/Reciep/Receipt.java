package com.zervladpy.tallerpaco.Core.Entities.Reciep;

import com.zervladpy.tallerpaco.Core.Entities.Customer.Customer;
import com.zervladpy.tallerpaco.Core.Entities.Parts.Part;
import com.zervladpy.tallerpaco.Core.Entities.ITEntity;
import com.zervladpy.tallerpaco.Core.Utils.Enums.ServiceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity(name = "receipt")
public class Receipt implements ITEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date")
    private LocalDate receiptDate;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @Enumerated(EnumType.STRING)
    @Column(name = "service_type")
    private ServiceType serviceType;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    private List<Part> parts;
    @Column(name = "labor_price")
    private double laborPrice;
    @Column(name = "total")
    private double total;

}
