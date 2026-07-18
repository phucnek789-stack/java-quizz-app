/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnhp.services;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Phupham
 */
public class FlyweightFactory {
    public static final Map<String, List> cacheData = new HashMap<>();
    
    public static <E> List<E> getData(QueryServiceBase ser, String key){
        System.out.println(Math.random());
        if(cacheData.containsKey(key) == false){
            try {
                cacheData.put(key, ser.list());
            } catch (SQLException ex) {
                System.getLogger(FlyweightFactory.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        return cacheData.get(key);
    }
}
