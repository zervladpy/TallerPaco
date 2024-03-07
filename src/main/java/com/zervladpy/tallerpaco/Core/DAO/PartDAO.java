package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Parts.Part;
import jakarta.persistence.EntityManagerFactory;

public class PartDAO extends DAO<Part> {
    public PartDAO(EntityManagerFactory emf) {
        super(emf, Part.class);
    }
}
