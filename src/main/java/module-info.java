module com.example.world_map {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.world_map to javafx.fxml;
    exports com.example.world_map;
}