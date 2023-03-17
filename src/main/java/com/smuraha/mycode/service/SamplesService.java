package com.smuraha.mycode.service;

import com.smuraha.mycode.dao.model.CodeSample;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SamplesService {
    Page<CodeSample> finAllByPage(Pageable pageable);
}
