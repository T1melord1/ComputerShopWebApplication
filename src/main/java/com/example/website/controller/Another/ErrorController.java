package com.example.website.controller.Another;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/accessdenied")
    public String showError() {
        return "errorJSP/403";
    }
}
