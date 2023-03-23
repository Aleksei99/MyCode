package com.smuraha.mycode.controller;

import com.smuraha.mycode.dao.model.CodeSample;
import com.smuraha.mycode.dao.model.MyAuthority;
import com.smuraha.mycode.dao.model.Section;
import com.smuraha.mycode.dao.model.User;
import com.smuraha.mycode.dto.CodeSampleDto;
import com.smuraha.mycode.service.CodeSampleService;
import com.smuraha.mycode.service.SectionService;
import com.smuraha.mycode.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

import static com.smuraha.mycode.service.util.Util.extractTitle;
import static com.smuraha.mycode.service.util.Util.wrapListToPage;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StorageController {

    private final UserService userService;
    private final SectionService sectionService;
    private final CodeSampleService codeSampleService;

    @ModelAttribute("sections")
    public List<Section> getAllSections(){
        return sectionService.findAll();
    }

    @ModelAttribute("sample")
    public CodeSample getNullHtml(){
        return new CodeSample();
    }

    @GetMapping("/storage")
    @Secured({"USER","ADMIN"})
    public String getUserStorage(Principal principal, Model model, Pageable pageable){
        User user = userService.findUserByEmail(principal.getName());
        Page<CodeSample> samples;
        if(user.getMyAuthority().equals(MyAuthority.ADMIN)){
            samples = codeSampleService.finAllByPage(pageable);
        }else{
            samples = wrapListToPage(user.getSamples(), pageable);
        }
        for (CodeSample codeSample: samples){
            String innerHtml = codeSample.getInnerHtml();
            codeSample.setInnerHtml(extractTitle(innerHtml));
        }
        model.addAttribute("samples", samples);
        return "storage";
    }

    @GetMapping("/newSample")
    @Secured({"USER","ADMIN"})
    public String getNewSamplePage(Model model){
        return "editCodeSample";
    }

    @PostMapping("/saveSample")
    @Secured({"USER","ADMIN"})
    public String saveSample(CodeSampleDto dto,Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        codeSampleService.save(dto,user);
        return "redirect:/storage";
    }

    @GetMapping("/editSample/{id}")
    @Secured({"USER","ADMIN"})
    public String getEditPageSample(@PathVariable Long id,Principal principal,Model model){
        User user = userService.findUserByEmail(principal.getName());
        CodeSample codeSample = codeSampleService.findFullById(id);
        if(!codeSample.getUser().equals(user)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        model.addAttribute("sample",codeSampleService.findFullById(id));
        return "editCodeSample";
    }
}
