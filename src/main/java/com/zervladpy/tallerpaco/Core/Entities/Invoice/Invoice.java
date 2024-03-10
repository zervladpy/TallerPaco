package com.zervladpy.tallerpaco.Core.Entities.Invoice;

import com.zervladpy.tallerpaco.Core.Entities.Car.Car;
import com.zervladpy.tallerpaco.Core.Entities.ITEntity;
import com.zervladpy.tallerpaco.Core.Entities.Service.Service;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Entity(name = "invoice")
public class Invoice implements ITEntity {
    @EmbeddedId
    private InvoicePK receiptId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private List<Service> service = new ArrayList<>();

}
