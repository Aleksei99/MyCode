package com.smuraha.mycode.controller;

import com.smuraha.mycode.dao.model.CodeSample;
import com.smuraha.mycode.service.CodeSampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.smuraha.mycode.service.util.Util.extractTitle;

@Controller
@RequiredArgsConstructor
public class OpenSectionController {

    private final CodeSampleService codeSampleService;

    @GetMapping("/section/{id}")
    public String getSection(@PathVariable Long id, Pageable pageable, Model model) {
        Page<CodeSample> samples = codeSampleService.findAllBySection_Id(pageable, id);
        for (CodeSample codeSample: samples){
            String innerHtml = codeSample.getInnerHtml();
            codeSample.setInnerHtml(extractTitle(innerHtml));
        }
        model.addAttribute("samples", samples);
        return "openSection";
    }

    @GetMapping("/view/{id}")
    public String getViewPage(Model model, @PathVariable Long id){
        model.addAttribute("html",codeSampleService.findForViewById(id).getInnerHtml());
        return "viewCode";
    }
}
