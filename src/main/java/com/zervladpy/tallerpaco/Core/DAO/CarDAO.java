package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.Car.Car;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarBrand;
import com.zervladpy.tallerpaco.Core.Entities.Car.CarTableViewDTO;
import com.zervladpy.tallerpaco.Core.Entities.Customer.Customer;
import jakarta.persistence.*;

import java.util.List;

public class CarDAO extends DAO<Car>{
    public CarDAO(EntityManagerFactory emf) {
        super(emf, Car.class);
    }

    public  List<CarTableViewDTO> getCarTableViewDTO() {

        EntityManager session = super.getEmf().createEntityManager();

        Class<CarTableViewDTO> resultClass = CarTableViewDTO.class;

        String query = "SELECT new " + resultClass.getTypeName()
                + "(c.id, c.details.plate, c.details.color, c.details.mileage, cb.brand, o.name) FROM "
                + super.getEntityClass().getName() + " c, "
                + Customer.class.getName() + " o, "
                + CarBrand.class.getName() + " cb"
                + " WHERE c.id = o.id"
                + " AND c.carBrand.id = cb.id";

        var result = session.createQuery(query, resultClass).getResultList();
        session.close();
        return result;
    }
}
