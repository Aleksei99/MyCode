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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String getAdminPage(Model model){
        model.addAttribute("sections",sectionRepository.findAll());
        return "admin";
    }

    @PostMapping("/addSection")
    public String uploadImage(HttpServletRequest request) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + ".png";
        Image image = new Image(fileName,"image/png",((StandardMultipartHttpServletRequest) request).getFile("image").getBytes());
        imageRepository.saveAndFlush(image);
        Section section = new Section(request.getParameter("name"),
                request.getParameter("sectionHeader"),
                image,
                Integer.parseInt(request.getParameter("width")),
                Integer.parseInt(request.getParameter("height")));
        sectionRepository.save(section);
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
