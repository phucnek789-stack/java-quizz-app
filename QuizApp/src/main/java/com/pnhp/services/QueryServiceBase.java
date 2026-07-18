/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnhp.services;

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
public abstract class QueryServiceBase<T> {
    public List<T> list() throws SQLException{ //Template Method
        PreparedStatement stm = this.geStm();
        ResultSet rs = stm.executeQuery();

        List<T> results = new ArrayList<>();
        while(rs.next()){
            results.add(this.getObject(rs));
        }
        return results;
    }
    
    public abstract PreparedStatement geStm() throws SQLException;
    public abstract T getObject(ResultSet rs) throws SQLException;
}
