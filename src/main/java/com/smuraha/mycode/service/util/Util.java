package com.smuraha.mycode.service.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static <T> Page<T>  wrapListToPage(List<T> list,Pageable pageable){
        int pageSize = pageable.getPageSize();
        int pageNo = pageable.getPageNumber();
        int start = pageSize * pageNo;
        int end = Math.min(start + pageSize, list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    public static String extractTitle(String html){
        Pattern patternH2 = Pattern.compile("<h2.*?>(.*?)</h2>");
        Matcher matcherH2 = patternH2.matcher(html);
        String title="";
        if(matcherH2.find()){
            title = matcherH2.group(1);
        }else {
            Pattern patternH3 = Pattern.compile("<h3.*?>(.*?)</h3>");
            Matcher matcherH3 = patternH3.matcher(html);
            if(matcherH3.find()){
                title = matcherH3.group(1);
            }else {
                Pattern patternP = Pattern.compile("<p.*?>(.*?)</p>");
                Matcher matcherP = patternP.matcher(html);
                if(matcherP.find()){
                    String innerText = matcherP.group(1);
                    title = innerText.length()>20?innerText.substring(0,20)+"..":innerText;
                }
            }
        }
        return title;
    }
}
