/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pnhp.quizapp;

import com.pnhp.pojo.Category;
import com.pnhp.pojo.Level;
import com.pnhp.utils.Configs;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PracticeController implements Initializable {
    @FXML private ComboBox<Category> cbSearchCates;
    @FXML private ComboBox<Level> cbSearchLevels;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbSearchCates.setItems(FXCollections.observableList(Configs.cateService.getCates()));
            this.cbSearchLevels.setItems(FXCollections.observableList(Configs.levelService.getLevels()));
            //this.tvQuestions.setItems(FXCollections.observableList(Configs.questionService.getQuestions(kw, cate, lv)));
        } catch (SQLException ex) {
            
        } 
    }    
    
}
