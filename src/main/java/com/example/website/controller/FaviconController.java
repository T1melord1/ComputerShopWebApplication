package com.example.website.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class FaviconController {

    @GetMapping("/favicon.ico")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public byte[] getFavicon() throws IOException {
        Resource resource = new ClassPathResource("static/favicon.ico");
        Path path = resource.getFile().toPath();
        return Files.readAllBytes(path);
    }
}
