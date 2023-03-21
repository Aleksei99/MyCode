package com.smuraha.mycode.service;

import com.smuraha.mycode.dao.model.CodeSample;
import com.smuraha.mycode.dao.model.User;
import com.smuraha.mycode.dto.CodeSampleDto;

public interface CodeSampleService {
    void save(CodeSampleDto dto, User user);
    CodeSample findFullById(Long id);
    CodeSample findForViewById(Long id);
}
