package com.zervladpy.tallerpaco.Core.Entities.Invoice;

import com.zervladpy.tallerpaco.Core.Entities.Car.Car;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Embeddable
public class InvoicePK implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "car", nullable = false)
    private Car car;

    @Column(name = "date", nullable = false, updatable = false)
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoicePK invoicePK = (InvoicePK) o;
        return Objects.equals(car, invoicePK.car) && Objects.equals(date, invoicePK.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car, date);
    }
}
