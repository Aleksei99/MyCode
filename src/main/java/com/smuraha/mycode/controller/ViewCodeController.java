package com.smuraha.mycode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewCodeController {

    @GetMapping("/view")
    public String getViewPage(){
        return "viewCode";
    }
}
