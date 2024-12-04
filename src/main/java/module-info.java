module com.example.doctorappointments {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.doctorappointments to javafx.fxml;
    exports com.example.doctorappointments;
}