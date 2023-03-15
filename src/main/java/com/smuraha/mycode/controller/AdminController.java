package com.smuraha.mycode.controller;

import com.smuraha.mycode.dao.model.Image;
import com.smuraha.mycode.dao.model.Section;
import com.smuraha.mycode.dao.repo.ImageRepository;
import com.smuraha.mycode.dao.repo.SectionRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ImageRepository imageRepository;
    private final SectionRepository sectionRepository;

    @GetMapping("/admin")
    public String getAdminPage(Model model,HttpServletRequest request){
        model.addAttribute("sections",sectionRepository.findAll());
        model.addAttribute("path",request.getContextPath());
        return "admin";
    }

    @ModelAttribute("section")
    public Section getEmptySection(){
        return new Section();
    }

    @PostMapping("/saveSection")
    public String saveSection(@ModelAttribute Section section,@RequestParam(value = "image", required = false) MultipartFile file) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + ".png";
        Image image;
        if(file!=null) {
            image = new Image(fileName, "image/png", file.getBytes());
            imageRepository.saveAndFlush(image);
        }else {
            image = sectionRepository.findById(section.getId()).orElseGet(this::getEmptySection).getSectionImage();
        }
        section.setSectionImage(image);
        sectionRepository.save(section);
        return "redirect:/admin";
    }

    @DeleteMapping("/deleteSection")
    public String deleteSection(@RequestParam("id") Long id){
        sectionRepository.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/getImage")
    public void getImage(@RequestParam("id") Long id,HttpServletResponse response) throws IOException {
        Image image = imageRepository.getReferenceById(id);
        response.setContentType(image.getContentType());
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(image.getData());
    }
}
