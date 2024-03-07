package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Car.CarBrand;
import jakarta.persistence.EntityManagerFactory;

public class CarBrandDAO extends DAO<CarBrand> {
    public CarBrandDAO(EntityManagerFactory emf) {
        super(emf, CarBrand.class);
    }
}
