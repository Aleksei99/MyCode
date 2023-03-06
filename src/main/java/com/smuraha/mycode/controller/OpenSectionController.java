package com.smuraha.mycode.controller;

import com.smuraha.mycode.dto.TestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OpenSectionController {

    @GetMapping("/{section}")
    public String getSection(@PathVariable String section, Pageable pageable, Model model){
        final List<TestDto> testDtos = List.of(
                new TestDto("Alex", "jpa"),
                new TestDto("Alex", "jpa"),
                new TestDto("Alex", "jpa"),
                new TestDto("Alex", "jpa"),
                new TestDto("Alex", "jpa"),
                new TestDto("Alex", "jpa"),
                new TestDto("Alex", "jpa"),
                new TestDto("Alex", "jpa"),
                new TestDto("Alex", "jpa"),
                new TestDto("Alex", "jpa"),
                new TestDto("Alex", "jpa"),
                new TestDto("Alex", "jpa"),
                new TestDto("Alex", "jpa")
        );

        Page<TestDto> samples = toPage(testDtos, pageable);
        model.addAttribute("samples",samples);
        return "openSection";
    }

    private <T> Page<T> toPage(List<T> list, Pageable pageable) {

        int pageSize= pageable.getPageSize();
        int pageNo=pageable.getPageNumber();
        int totalPages = list.size() / pageSize;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);

        int max = pageNo>=totalPages? list.size():pageSize*(pageNo+1);
        int min = pageNo >totalPages? max:pageSize*pageNo;

        return new PageImpl<>(list.subList(min, max), pageRequest,
                list.size());
    }
}
