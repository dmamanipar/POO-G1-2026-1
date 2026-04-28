module pe.edu.upeu {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.kordamp.bootstrapfx.core;

    requires org.xerial.sqlitejdbc;
    requires java.sql;
    opens pe.edu.upeu.controller to javafx.fxml;

    exports pe.edu.upeu;
}