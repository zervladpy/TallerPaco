package com.zervladpy.tallerpaco.Core.Session;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SessionManager {

    static private EntityManagerFactory instance;

    static public EntityManagerFactory getInstance() {

        if (instance == null) {
            synchronized (EntityManagerFactory.class) {
                if (instance == null) {
                    instance = Persistence.createEntityManagerFactory("tallerpaco");
                }
            }
        }

        return instance;

    }

}
