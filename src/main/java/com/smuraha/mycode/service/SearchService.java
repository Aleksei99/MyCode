package com.smuraha.mycode.service;

import com.smuraha.mycode.dao.model.CodeSample;

import java.util.Collection;
import java.util.List;

public interface SearchService {
    List<CodeSample> getSearchCodeSampleResult(String searchWords, Collection<CodeSample> collection);
}
