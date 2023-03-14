package com.smuraha.mycode.controller;

import com.smuraha.mycode.service.SectionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final SectionService sectionService;

    @GetMapping("/")
    public String getMainPage(Model model, HttpServletRequest request){
        String header = request.getHeader("User-Agent");
        model.addAttribute("sections",sectionService.findAll());
        model.addAttribute("isMobile", header.toLowerCase().contains("mobile"));
        return "main";
    }
}
