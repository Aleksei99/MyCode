package com.smuraha.mycode.controller;

import com.google.common.collect.ImmutableList;
import com.smuraha.mycode.dto.TestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.function.Function;

@Controller
public class OpenSectionController {

    private final ImmutableList<TestDto> testDtos = ImmutableList.of(
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

    private final Function<Pageable, Page<TestDto>> pageFetcher = pageable -> {
        int pageSize = pageable.getPageSize();
        int pageNo = pageable.getPageNumber();
        int start = pageSize * pageNo;
        int end = Math.min(start + pageSize, testDtos.size());
        return new PageImpl<>(testDtos.subList(start, end), pageable, testDtos.size());
    };

    public OpenSectionController() {}

    @GetMapping("/section/{section}")
    public String getSection(@PathVariable String section, Pageable pageable, Model model) {
        Page<TestDto> samples = pageFetcher.apply(pageable);
        model.addAttribute("samples", samples);
        return "openSection";
    }
}
