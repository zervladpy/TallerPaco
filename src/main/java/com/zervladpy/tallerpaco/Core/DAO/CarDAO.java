package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Car.Car;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarWithUserFullNameDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class CarDAO extends DAO<Car>{
    public CarDAO(EntityManagerFactory emf) {
        super(emf, Car.class);
    }

    public <T, S> List<CarWithUserFullNameDTO> getCarWithUserFullNameDTO(Class<T> ownerEntity, Class<S> brandEntity) {

        EntityManager session = super.getEmf().createEntityManager();

        Class<CarWithUserFullNameDTO> resultClass = CarWithUserFullNameDTO.class;

        String query = "SELECT new " + resultClass.getTypeName()
                + "(c.id, c.details.plate, c.details.color, c.details.mileage, cb.brand, o.name) FROM "
                + super.getEntityClass().getName() + " c, "
                + ownerEntity.getName() + " o, "
                + brandEntity.getName() + " cb"
                + " WHERE c.id = o.id"
                + " AND c.carBrand.id = cb.id";

        List<CarWithUserFullNameDTO> result = session.createQuery(query).getResultList();

        return result;
    }
}
