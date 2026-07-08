package com.pnhp.quizapp;

import com.pnhp.utils.MyAlertSingleton;
import com.pnhp.utils.MyStageSingleton;
import com.pnhp.utils.themes.ThemeTypes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class PrimaryController implements Initializable{
    @FXML private ComboBox<ThemeTypes> cbThemes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThemes.setItems(FXCollections.observableArrayList(ThemeTypes.values()));
    }
    
    public void ManageQuestion(ActionEvent e){
        MyStageSingleton.getInstance().showStage("questions");
    }
    
    public void Practice(ActionEvent e){
        MyStageSingleton.getInstance().showStage("practice");
    }
    
    public void Exam(ActionEvent e){
        MyAlertSingleton.getInstance().showMessage("[ManageQuestion] Coming soon... ");
    }

    public void changeTheme(ActionEvent e){
        this.cbThemes.getSelectionModel().getSelectedItem().updateTheme(this.cbThemes.getScene());
    }
}
