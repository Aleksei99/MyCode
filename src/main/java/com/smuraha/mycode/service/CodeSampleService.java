package com.smuraha.mycode.service;

import com.smuraha.mycode.dao.model.CodeSample;
import com.smuraha.mycode.dao.model.User;
import com.smuraha.mycode.dto.CodeSampleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CodeSampleService {
    void save(CodeSampleDto dto, User user);
    CodeSample findFullById(Long id);
    CodeSample findForViewById(Long id);

    Page<CodeSample> finAllByPage(Pageable pageable);
    Page<CodeSample> findAllBySection_Id(Pageable pageable,Long id);

    void delete(Long id);
}
