module com.zervlad.tallerpaco {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.sql;
    requires lombok;

    opens com.zervladpy.tallerpaco to javafx.fxml;
    opens com.zervladpy.tallerpaco.Core.Entities.Car to org.hibernate.orm.core, javafx.base;
    opens com.zervladpy.tallerpaco.Core.Entities.Customer to org.hibernate.orm.core, javafx.base;
    opens com.zervladpy.tallerpaco.Core.Entities.Reciep to org.hibernate.orm.core, javafx.base;
    opens com.zervladpy.tallerpaco.Core.Entities.Parts to org.hibernate.orm.core, javafx.base;

    opens com.zervladpy.tallerpaco.Core.Session to org.hibernate.orm.core;

    exports com.zervladpy.tallerpaco;
    exports com.zervladpy.tallerpaco.Controllers;
    opens com.zervladpy.tallerpaco.Controllers to javafx.fxml;
    exports com.zervladpy.tallerpaco.Controllers.Car;
    opens com.zervladpy.tallerpaco.Controllers.Car to javafx.fxml;
    exports com.zervladpy.tallerpaco.Controllers.CarBrand;
    opens com.zervladpy.tallerpaco.Controllers.CarBrand to javafx.fxml;
}