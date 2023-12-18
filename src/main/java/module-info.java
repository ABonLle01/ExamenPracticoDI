module com.example.examenpracticodi {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.example.examenpracticodi to javafx.fxml;
    exports com.example.examenpracticodi;
}