package com.zervladpy.tallerpaco.Core.Entities.Reciep;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor
@Setter @Getter
public class ReceiptTableViewDTO {
    private int id;
    private LocalDate date;
    private String customerFullName;
    private String serviceType;
    private int partsCount;
    private double laborPrice;
    private double totalPrice;
}
