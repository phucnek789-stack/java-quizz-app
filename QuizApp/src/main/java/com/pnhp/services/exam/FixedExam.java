/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnhp.services.exam;

import com.pnhp.pojo.Question;
import com.pnhp.pojo.QuestionQueryBuilder;
import com.pnhp.services.question.QuestionFacade;
import com.pnhp.utils.Configs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Phupham
 */
public class FixedExam extends ExamStrategy{
    
    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        for(int i=0; i < Configs.RATED.length; i++){
            try {
                QuestionQueryBuilder q = new QuestionQueryBuilder().withLevel(i + 1)
                        .setOrderBy("rand()").setLimit((int)(Configs.RATED[i] * Configs.EXAM_NUM));
                questions.addAll(QuestionFacade.getLazyQuestion(q));
            } catch (SQLException ex) {
                System.getLogger(FixedExam.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        
        return questions;
    }
    
}
