/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnhp.utils;

import com.pnhp.services.CategoryServices;
import com.pnhp.services.LevelServices;
import com.pnhp.services.question.QuestionServices;
import com.pnhp.services.question.UpdateQuestionServices;

/**
 *
 * @author Phupham
 */
public class Configs {
    public static final CategoryServices cateService = new CategoryServices();
    public static final QuestionServices questionService = new QuestionServices();
    public static final LevelServices levelService = new LevelServices();
    public static final UpdateQuestionServices uQuestionService = new UpdateQuestionServices();
}
