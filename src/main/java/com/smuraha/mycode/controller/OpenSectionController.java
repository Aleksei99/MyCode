package com.smuraha.mycode.controller;

import com.smuraha.mycode.dao.model.CodeSample;
import com.smuraha.mycode.service.CodeSampleService;
import com.smuraha.mycode.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.smuraha.mycode.service.util.Util.extractTitle;
import static com.smuraha.mycode.service.util.Util.wrapListToPage;

@Controller
@RequiredArgsConstructor
public class OpenSectionController {

    private final CodeSampleService codeSampleService;
    private final SearchService searchService;

    @GetMapping("/section/{id}")
    public String getSection(@PathVariable Long id, Pageable pageable, Model model) {
        Page<CodeSample> samples = codeSampleService.findAllBySection_Id(pageable, id);
        for (CodeSample codeSample : samples) {
            String innerHtml = codeSample.getInnerHtml();
            codeSample.setInnerHtml(extractTitle(innerHtml));
        }
        model.addAttribute("samples", samples);
        model.addAttribute("section", id);
        return "openSection";
    }

    @GetMapping("/view/{id}")
    public String getViewPage(Model model, @PathVariable Long id) {
        model.addAttribute("html", codeSampleService.findForViewById(id).getInnerHtml());
        return "viewCode";
    }

    @GetMapping("/section/{id}/search")
    public String search(@PathVariable("id") Long id, @RequestParam("searchWords") String searchWords, Pageable pageable, Model model) {
        Page<CodeSample> samples = codeSampleService.findAllBySection_Id(pageable, id);
        samples=wrapListToPage(searchService.getSearchCodeSampleResult(searchWords,samples.stream().toList()),pageable);
        for (CodeSample codeSample : samples) {
            String innerHtml = codeSample.getInnerHtml();
            codeSample.setInnerHtml(extractTitle(innerHtml));
        }
        model.addAttribute("samples", samples);
        model.addAttribute("section", id);
        return "openSection";
    }
}
