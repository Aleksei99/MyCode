package com.smuraha.mycode.controller;

import com.smuraha.mycode.dao.model.Image;
import com.smuraha.mycode.dao.repo.ImageRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ImageRepository imageRepository;

    @GetMapping("/admin")
    public String getAdminPage(){
        return "admin";
    }

    @PostMapping("/uploadImage")
    public void uploadImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + ".png";
        Image image = new Image(fileName,"image/png",((StandardMultipartHttpServletRequest) request).getFile("image").getBytes());
        imageRepository.save(image);
        String imageUrl = request.getContextPath()+"/getImage?name="+fileName;
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(imageUrl);
    }

    @GetMapping("/getImage")
    public void getImage(@RequestParam("name") String name,HttpServletResponse response) throws IOException {
        Image image = imageRepository.getImageByName(name);
        response.setContentType(image.getContentType());
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(image.getData());
//        FileOutputStream fileOutputStream = new FileOutputStream("image.png");
//        fileOutputStream.write(image.getData());

    }
}
