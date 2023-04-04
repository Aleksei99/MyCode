package com.smuraha.mycode.service;

import com.smuraha.mycode.dao.model.CodeSample;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService{
    @Override
    public List<CodeSample> getSearchCodeSampleResult(String searchWords, Collection<CodeSample> collection) {
        Map<CodeSample, Integer> sampleMatchedMap = new HashMap<>();
        Iterator<CodeSample> iterator = collection.iterator();
        List<String> words = Arrays.stream(searchWords.toLowerCase().split(" ")).distinct().collect(Collectors.toList());
        if (words.size() > 0) {
            while (iterator.hasNext()) {
                CodeSample sample = iterator.next();
                boolean matched = false;
                int matchedCount = 0;
                for (String word : words) {
                    if (sample.getInnerHtml().toLowerCase().contains(word)) {
                        matched = true;
                        matchedCount++;
                    }
                }
                if (matched) {
                    sampleMatchedMap.put(sample, matchedCount);
                }
            }
            return sampleMatchedMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).collect(Collectors.toList());
        }
        return new ArrayList<>(collection);
    }
}
