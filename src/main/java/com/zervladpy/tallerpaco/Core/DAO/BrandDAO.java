package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Brand.Brand;
import com.zervladpy.tallerpaco.Core.Entities.Brand.BrandNameDTO;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BrandDAO extends DAO<Brand> {
    public BrandDAO(EntityManager session) {
        super(session, Brand.class);
    }

    public List<BrandNameDTO> getBrandNameDTOs() {

        String query = "SELECT new " + BrandNameDTO.class.getTypeName() + "(" +
                "b.id, b.name) FROM " + getEntityClass().getTypeName() + " b";

        return getSession().createQuery(query, BrandNameDTO.class).getResultList();
    }
}
