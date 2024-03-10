module com.zervladpy.tallerpaco {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.sql;
    requires lombok;
    requires java.desktop;

    opens com.zervladpy.tallerpaco to javafx.fxml;
    opens com.zervladpy.tallerpaco.Core.Entities.Car to org.hibernate.orm.core, javafx.base;
    opens com.zervladpy.tallerpaco.Core.Entities.Client to org.hibernate.orm.core, javafx.base;
    opens com.zervladpy.tallerpaco.Core.Entities.Invoice to org.hibernate.orm.core, javafx.base;

    opens com.zervladpy.tallerpaco.Core.Session to org.hibernate.orm.core;

    exports com.zervladpy.tallerpaco;
    exports com.zervladpy.tallerpaco.Controllers;
    opens com.zervladpy.tallerpaco.Controllers to javafx.fxml;
    exports com.zervladpy.tallerpaco.Core.DAO;
    exports com.zervladpy.tallerpaco.Core.Entities;
    exports com.zervladpy.tallerpaco.Core.Entities.Car;
    exports com.zervladpy.tallerpaco.Core.Entities.Client;
    exports com.zervladpy.tallerpaco.Core.Entities.Invoice;
    exports com.zervladpy.tallerpaco.Core.Entities.Service;
    exports com.zervladpy.tallerpaco.Core.Utils.Managers;
    exports com.zervladpy.tallerpaco.Core.Session;
    opens com.zervladpy.tallerpaco.Core.Entities.Service to javafx.base, javafx.fxml, org.hibernate.orm.core;
    exports com.zervladpy.tallerpaco.Core.Entities.Brand;
    opens com.zervladpy.tallerpaco.Core.Entities.Brand to javafx.base, org.hibernate.orm.core;
}