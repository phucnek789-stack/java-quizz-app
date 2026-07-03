/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pnhp.quizapp;

import com.pnhp.pojo.Category;
import com.pnhp.pojo.Choice;
import com.pnhp.pojo.Level;
import com.pnhp.pojo.Question;
import com.pnhp.services.CategoryServices;
import com.pnhp.services.LevelServices;
import com.pnhp.services.question.QuestionServices;
import com.pnhp.utils.Configs;
import com.pnhp.utils.MyAlertSingleton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuestionsController implements Initializable {
    @FXML private ComboBox<Category> cbCates;
    @FXML private ComboBox<Level> cbLevels;
    @FXML private ComboBox<Category> cbSearchCates;
    @FXML private ComboBox<Level> cbSearchLevels;
    @FXML private TableView<Question> tvQuestions;
    @FXML private VBox vChoices;
    @FXML private TextArea txtContent;
    @FXML private ToggleGroup toggle;
    @FXML private TextField txtKeywords;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadColumns();
        try {
            this.cbCates.setItems(FXCollections.observableList(Configs.cateService.getCates()));
            this.cbLevels.setItems(FXCollections.observableList(Configs.levelService.getLevels()));
            this.cbSearchCates.setItems(FXCollections.observableList(Configs.cateService.getCates()));
            this.cbSearchLevels.setItems(FXCollections.observableList(Configs.levelService.getLevels()));
            //this.tvQuestions.setItems(FXCollections.observableList(Configs.questionService.getQuestions(kw, cate, lv)));
        } catch (SQLException ex) {
            
        } 
        loadTabQuestion();
    }    
    
    public void loadColumns(){
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(100);
        
        TableColumn colContent = new TableColumn("Nội dung câu hỏi");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(300);
        
        this.tvQuestions.getColumns().addAll(colId, colContent);
    }
    
    public void addChoice(ActionEvent e){
        HBox h = new HBox();
        h.getStyleClass().add("Container");
        
        RadioButton r = new RadioButton();
        r.setToggleGroup(toggle);
        TextField txt = new TextField();
        
        h.getChildren().addAll(r, txt);
        
        this.vChoices.getChildren().add(h);
    }
    
    public void addQuestion(ActionEvent e) {
        Question q = new Question.Builder().setContent(txtContent.getText())
                .setCategory(cbCates.getSelectionModel().getSelectedItem())
                .setLevel(cbLevels.getSelectionModel().getSelectedItem()).build();
        
        List<Choice> choices = new ArrayList<>();
        
        for (var hbox: this.vChoices.getChildren()) {
            HBox h = (HBox) hbox;
            
            RadioButton rdo = (RadioButton)h.getChildren().get(0);
            TextField txt = (TextField)h.getChildren().get(1);
            
            choices.add(new Choice(txt.getText(), rdo.isSelected()));
        }
        
        try {
            Optional<ButtonType> b = MyAlertSingleton.getInstance().showMessage("Bạn chắc chắn thêm không?", Alert.AlertType.CONFIRMATION);
            if (b.isPresent() && b.get() == ButtonType.OK) {
                Configs.uQuestionService.addQuestion(q, choices);
                MyAlertSingleton.getInstance().showMessage("Thêm câu hỏi thành công!");
                loadTabQuestion();
            }
        } catch (SQLException ex) {
            MyAlertSingleton.getInstance().showMessage("Thêm câu hỏi thất bại, do: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void loadTabQuestion(){
        try {
           
            this.tvQuestions.setItems(FXCollections.observableList(Configs.questionService.getQuestions(
                    this.txtKeywords.getText(), 
                    this.cbSearchCates.getSelectionModel().getSelectedItem(), 
                    this.cbLevels.getSelectionModel().getSelectedItem())));
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
