package com.zervladpy.tallerpaco.Core.Entities.Car;

import com.zervladpy.tallerpaco.Core.Entities.Brand.Brand;
import com.zervladpy.tallerpaco.Core.Entities.Client.Client;
import com.zervladpy.tallerpaco.Core.Entities.ITEntity;
import com.zervladpy.tallerpaco.Core.Entities.Invoice.Invoice;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
@Entity(name = "car")
public class Car implements ITEntity {

    @Id
    private String plate;

    @Column(name = "mileage")
    private int mileage;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;

    @Embedded
    private CarDetails details;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car", referencedColumnName = "plate")
    private List<Invoice> invoices = new ArrayList<>();

    public Car(String plate, int mileage, Client client, CarDetails details) {
        this.plate = plate;
        this.mileage = mileage;
        this.client = client;
        this.details = details;
    }
}
