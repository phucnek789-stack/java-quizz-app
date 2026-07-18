/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pnhp.quizapp;

import com.pnhp.pojo.Choice;
import com.pnhp.pojo.Question;
import com.pnhp.services.exam.ExamStrategy;
import com.pnhp.services.exam.ExamTypes;
import com.pnhp.services.exam.FixedExam;
import com.pnhp.services.exam.SpecificExam;
import com.pnhp.utils.MyAlertSingleton;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Phupham
 */
public class ExamController implements Initializable {

    @FXML
    private ComboBox<ExamTypes> cbExamTypes;
    @FXML
    private TextField txtNum;
    @FXML
    private ListView<Question> lvQuestions;
    private List<Question> questions;
    private Map<Integer, Choice> answer = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbExamTypes.setItems(FXCollections.observableArrayList(ExamTypes.values()));
        this.txtNum.setVisible(false);
        
        this.cbExamTypes.getSelectionModel().selectedItemProperty().addListener(e->{
            if(this.cbExamTypes.getSelectionModel().getSelectedItem() == ExamTypes.SPECIFIC)
                this.txtNum.setVisible(true);
            else
                this.txtNum.setVisible(false);
        });
        this.lvQuestions.setCellFactory(callback -> new ListCell<>(){
            @Override
            protected void updateItem(Question q, boolean empty) {
                super.updateItem(q, empty);
                if(q == null || empty == true)
                    setGraphic(null);
                else{
                    VBox v = new VBox(5);
                    
                    v.setStyle("-fx-border-width: 2; -fx-border-color: blue; -fx-padding: 5");
                    
                    Text t = new Text(q.getContent());
                    v.getChildren().add(t);
                    
                    ToggleGroup g = new ToggleGroup();
                    for(var c: q.getChoices()){
                        RadioButton r = new RadioButton(c.getContent());
                        r.setToggleGroup(g);
                        
                        //xu li giao dien
                        if(answer.get(q.getId()) == c)
                            r.setSelected(true);
                        
                        r.setOnAction(e->{
                            answer.put(q.getId(), c);
                        });
                        
                        v.getChildren().add(r);
                    }
                    setGraphic(v);
                }
            }
            
        });
    }

    public void start(ActionEvent e) {
        switch (this.cbExamTypes.getSelectionModel().getSelectedItem()) {
            case SPECIFIC:
                ExamStrategy s = new SpecificExam(this.txtNum.getText());
                this.questions = s.getQuestions();
                
                break;
            default:
                ExamStrategy s1 = new FixedExam();
                this.questions = s1.getQuestions();
        }
        this.lvQuestions.setItems(FXCollections.observableList(this.questions));
    }
    
    public void grading(ActionEvent e){
        int count = 0;
        
        for(var ans: answer.values())
            if(ans.isCorrect() == true)
                count++;
            
        MyAlertSingleton.getInstance().showMessage(String.format("Bạn đã làm đúng %d/%d", count, this.questions.size()), Alert.AlertType.INFORMATION);
    }
}
