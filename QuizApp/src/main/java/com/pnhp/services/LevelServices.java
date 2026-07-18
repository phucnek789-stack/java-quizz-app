/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnhp.services;

import com.pnhp.pojo.Category;
import com.pnhp.pojo.Level;
import com.pnhp.utils.MyConnSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Phupham
 */
public class LevelServices extends QueryServiceBase<Level>{

    @Override
    public PreparedStatement geStm() throws SQLException {
        return MyConnSingleton.getInstance().connect().prepareCall("SELECT * FROM level");
    }

    @Override
    public Level getObject(ResultSet rs) throws SQLException {
        return new Level(rs.getInt("id"), rs.getString("name"));
    }
//    public List<Level> getLevels() throws SQLException{
//        Connection conn = MyConnSingleton.getInstance().connect();
//
//        String sql = "SELECT * FROM Level";
//        PreparedStatement stm = conn.prepareCall(sql);
//        ResultSet rs = stm.executeQuery();
//
//        List<Level> levels = new ArrayList<>();
//        while(rs.next()){
//            int id = rs.getInt("id");
//            String name = rs.getString("name");
//
//               levels.add(new Level(id, name));
//        }
//        return levels;
//    }
}
