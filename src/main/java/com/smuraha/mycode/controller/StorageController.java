package com.smuraha.mycode.controller;

import com.smuraha.mycode.dao.model.MyAuthority;
import com.smuraha.mycode.dao.model.User;
import com.smuraha.mycode.dto.CodeSampleDto;
import com.smuraha.mycode.service.CodeSampleService;
import com.smuraha.mycode.service.SamplesService;
import com.smuraha.mycode.service.SectionService;
import com.smuraha.mycode.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

import static com.smuraha.mycode.service.util.Util.wrapListToPage;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StorageController {

    private final UserService userService;
    private final SamplesService samplesService;
    private final SectionService sectionService;
    private final CodeSampleService codeSampleService;

    @GetMapping("/storage")
    @Secured({"USER","ADMIN"})
    public String getUserStorage(Principal principal, Model model, Pageable pageable){
        final User user = userService.findUserByEmail(principal.getName());
        if(user.getMyAuthority().equals(MyAuthority.ADMIN)){
            model.addAttribute("samples",samplesService.finAllByPage(pageable));
        }else{
            model.addAttribute("samples",wrapListToPage(user.getSamples(),pageable));
        }
        return "storage";
    }

    @GetMapping("/newSample")
    @Secured({"USER","ADMIN"})
    public String getNewSamplePage(Model model){
        model.addAttribute("sections",sectionService.findAll());
        return "editCodeSample";
    }

    @PostMapping("/saveSample")
    @Secured({"USER","ADMIN"})
    public String saveSample(CodeSampleDto dto,Principal principal){
        final User user = userService.findUserByEmail(principal.getName());
        codeSampleService.save(dto,user);
        return "redirect:/storage";
    }
}
