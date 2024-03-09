package com.zervladpy.tallerpaco.Core.Entities.Client;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class ClientTableViewDTO {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private int totalCars;
    private int totalReceipts;

}
