package com.ngand.webtheme.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {
    
    @Value("${welcome.username}")
    private String username;

    private List<String> tasks = Arrays.asList("a", "b", "c", "e", "f", "g");

    @GetMapping("/home")
    public String main(Model model) {
        model.addAttribute("message", username);
        model.addAttribute("tasks", tasks);

        return "welcome"; //view
    }

    // /hello?name=kotlin
    @GetMapping("/hello")
    public String mainWithParam(@RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        model.addAttribute("message", name);
        return "welcome"; // View
    }
}

