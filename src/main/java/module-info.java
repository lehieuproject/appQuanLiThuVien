module com.slh.quanlythuvienapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.slh.quanlythuvienapp to javafx.fxml;
    exports com.slh.quanlythuvienapp;
    exports com.slh.services;
    exports com.slh.pojo;
    
}
