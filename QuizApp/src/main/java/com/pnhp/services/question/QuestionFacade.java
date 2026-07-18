/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnhp.services.question;

import com.pnhp.pojo.Question;
import com.pnhp.pojo.QuestionQueryBuilder;
import com.pnhp.utils.Configs;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Phupham
 */
public class QuestionFacade {
    public static List<Question> getQuestion(QuestionQueryBuilder q) throws SQLException{
        Configs.questionService.setQuery(q);
        return Configs.questionService.list();
    }
    
    public static List<Question> getLazyQuestion(QuestionQueryBuilder q) throws SQLException{
        Configs.questionService.setQuery(q);
        return new QuestionServiceDecorator( Configs.questionService).list();
    }
}
