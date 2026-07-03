module com.pnhp.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.pnhp.quizapp to javafx.fxml;
    exports com.pnhp.quizapp;
    exports com.pnhp.pojo;
}
