package com.smuraha.mycode.service;

import com.smuraha.mycode.dao.model.CodeSample;
import com.smuraha.mycode.dao.repo.SamplesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SamplesServiceImpl implements SamplesService {

    private final SamplesRepository samplesRepository;

    @Override
    public Page<CodeSample> finAllByPage(Pageable pageable) {
        return samplesRepository.findAllSamples(pageable);
    }
}
