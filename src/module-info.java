
module DBPROJ {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens sample;
    opens sample.assets;
}

