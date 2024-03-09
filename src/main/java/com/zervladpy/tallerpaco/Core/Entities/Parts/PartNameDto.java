package com.zervladpy.tallerpaco.Core.Entities.Parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class PartNameDto {
    private int id;
    private String reference;
    private String description;

    @Override
    public String toString() {
        return reference + " - " + description;
    }
}
