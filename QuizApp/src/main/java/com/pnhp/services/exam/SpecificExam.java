/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnhp.services.exam;

import com.pnhp.pojo.Question;
import com.pnhp.pojo.QuestionQueryBuilder;
import com.pnhp.services.question.QuestionFacade;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Phupham
 */
public class SpecificExam extends ExamStrategy{
    private int num;

    public SpecificExam(int num) {
        this.num = num;
    }
    
    public SpecificExam(String num) {
        this(Integer.parseInt(num));
    }
    
    @Override
    public List<Question> getQuestions() {
        QuestionQueryBuilder q = new QuestionQueryBuilder().setLimit(this.num).setOrderBy("rand()");
        try {
            return QuestionFacade.getLazyQuestion(q);
        } catch (SQLException ex) {
            System.getLogger(SpecificExam.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
    
}
