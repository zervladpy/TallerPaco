package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Parts.Part;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class PartDAO extends DAO<Part> {
    public PartDAO(EntityManager session) {
        super(session, Part.class);
    }

}
