/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnhp.services.question;

import com.pnhp.pojo.Question;
import com.pnhp.pojo.QuestionQueryBuilder;
import com.pnhp.services.QueryServiceBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuestionServices extends QueryServiceBase<Question> implements QuestionServiceBase{
    private QuestionQueryBuilder query;

    public QuestionServices() {
    }

    public QuestionServices(QuestionQueryBuilder query) {
        this.query = query;
    }
    
     @Override
    public PreparedStatement geStm() throws SQLException {
        return this.query.build();
    }

    @Override
    public Question getObject(ResultSet rs) throws SQLException {
        return new Question.Builder().setId(rs.getInt("id")).setContent(rs.getString("content")).build();
    }
    
//    @Override
//    public List<Question> list() throws SQLException{
//        Connection conn = MyConnSingleton.getInstance().connect();
//
//        String sql = "SELECT * FROM Question WHERE 1=1"; //ORDER BY id DESC
//        
//        List<Object> params = new ArrayList<>();
//        if (kw != null && !kw.isEmpty()) {
//            sql += " AND content like concat('%', ?, '%')";
//            params.add(kw);
//        }
//        if (cate != null) {
//            sql += " AND category_id = ?";
//            params.add(cate.getId());
//        }
//        if (lv != null) {
//            sql += " AND level_id = ?";
//           params.add(lv.getId());
//       }
//        
//        PreparedStatement stm = this.query.build();
//        
//        ResultSet rs = stm.executeQuery();
//
//        List<Question> questions = new ArrayList<>();
//        while(rs.next()){
//            int id = rs.getInt("id");
//            String content = rs.getString("content");
//
//            questions.add(new Question.Builder().setId(id).setContent(content).build());
//        }
//        return questions;
//    }

    /**
     * @return the query
     */
    public QuestionQueryBuilder getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(QuestionQueryBuilder query) {
        this.query = query;
    }
}
