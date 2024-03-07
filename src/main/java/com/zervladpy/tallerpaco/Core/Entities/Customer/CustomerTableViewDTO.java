package com.zervladpy.tallerpaco.Core.Entities.Customer;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class CustomerTableViewDTO {

    private int id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private int totalCars;
    private int totalReceipts;

}
