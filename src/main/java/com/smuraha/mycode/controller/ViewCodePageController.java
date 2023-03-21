package com.smuraha.mycode.controller;

import com.smuraha.mycode.service.CodeSampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewCodePageController {

    private final CodeSampleService codeSampleService;

    @GetMapping("/view")
    public String getViewPage(Model model){
        model.addAttribute("html",codeSampleService.findForViewById(1L).getInnerHtml());
        return "viewCode";
    }
}
