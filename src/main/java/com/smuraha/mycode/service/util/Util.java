package com.smuraha.mycode.service.util;

import com.smuraha.mycode.dto.TestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;

public class Util {
    public static <T> Page<T>  wrapListToPage(List<T> list,Pageable pageable){
       list.stream().map()
    }
    private final static Function<Pageable, Page<TestDto>> pageFetcher = pageable -> {
        int pageSize = pageable.getPageSize();
        int pageNo = pageable.getPageNumber();
        int start = pageSize * pageNo;
        int end = Math.min(start + pageSize, testDtos.size());
        return new PageImpl<>(testDtos.subList(start, end), pageable, testDtos.size());
    };
}
