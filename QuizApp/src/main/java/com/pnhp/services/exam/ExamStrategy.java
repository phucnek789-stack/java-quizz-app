/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnhp.services.exam;

import com.pnhp.pojo.Question;
import java.util.List;

/**
 *
 * @author Phupham
 */
public abstract class ExamStrategy {
    public abstract List<Question> getQuestions();
}
