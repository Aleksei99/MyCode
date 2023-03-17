package com.smuraha.mycode.controller;

import com.smuraha.mycode.service.RegistrationService;
import com.smuraha.mycode.service.UserService;
import com.smuraha.mycode.service.VerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Pattern;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final RegistrationService registrationService;
    private final VerificationService verificationService;
    private final UserService userService;

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("registered",false);
        model.addAttribute("reset",false);
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model){
        model.addAttribute("userExists",false);
        return "registerStepEmail";
    }

    @PostMapping("/registerStepEmail")
    public String sendEmail(@RequestParam("email") String email, Model model){
        if(userService.findUserByEmail(email)!=null){
            model.addAttribute("userExists",true);
            return "registerStepEmail";
        }
        if(!registrationService.registerEmail(email)){
            throw new RuntimeException("Error was occurred");
        }
        model.addAttribute("email",email);
        model.addAttribute("errorToken",false);
        return "registerStepVerification";
    }

    @PostMapping("/registerStepVerification")
    public String verification(@RequestParam("email") String email,@RequestParam("token") String token,Model model){
        if(!verificationService.verified(email, token)){
            model.addAttribute("email",email);
            model.addAttribute("errorToken",true);
            return "registerStepVerification";
        }
        model.addAttribute("email",email);
        model.addAttribute("incorrectPasswordPattern",false);
        return "registerStepNewPassword";
    }

    @PostMapping("/register")
    public String register(@RequestParam("email") String email,@RequestParam("password") String password,Model model){
        if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", password)) {
            model.addAttribute("incorrectPasswordPattern",true);
            model.addAttribute("email",email);
            return "registerStepNewPassword";
        }
        userService.saveNewUser(email, password);
        model.addAttribute("registered",true);
        return "login";
    }

    @GetMapping("/resetPassword")
    public String resetStepEmail(Model model){
        model.addAttribute("userNotExists",false);
        return "resetPasswordStepEmail";
    }
    @PostMapping("/resetStepEmail")
    public String resetStepEmail(@RequestParam("email") String email,Model model){
        if(userService.findUserByEmail(email)==null){
            model.addAttribute("userNotExists",true);
            return "resetPasswordStepEmail";
        }
        registrationService.resetPasswordEmail(email);
        model.addAttribute("email",email);
        model.addAttribute("errorToken",false);
        return "resetStepVerification";
    }

    @PostMapping("/resetStepVerification")
    public String resetVerification(@RequestParam("email") String email,@RequestParam("token") String token,Model model){
        if(!verificationService.verified(email, token)){
            model.addAttribute("email",email);
            model.addAttribute("errorToken",true);
            return "resetStepVerification";
        }
        model.addAttribute("email",email);
        model.addAttribute("incorrectPasswordPattern",false);
        return "resetStepNewPassword";
    }

    @PostMapping("/reset")
    public String reset(@RequestParam("email") String email,@RequestParam("password") String password,Model model){
        if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", password)) {
            model.addAttribute("incorrectPasswordPattern",true);
            model.addAttribute("email",email);
            return "resetStepNewPassword";
        }
        userService.resetPassword(email, password);
        model.addAttribute("registered",false);
        model.addAttribute("reset",true);
        return "login";
    }
}
