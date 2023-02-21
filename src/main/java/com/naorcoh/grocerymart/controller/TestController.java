package com.naorcoh.grocerymart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TestController {
    @GetMapping("/test")
    public String displayTestPage(Model model) {

        model.addAttribute("title", "Test");

        return "test.html";
    }

}
