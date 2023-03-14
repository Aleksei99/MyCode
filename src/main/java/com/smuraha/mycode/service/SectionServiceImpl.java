package com.smuraha.mycode.service;

import com.smuraha.mycode.dao.model.Section;
import com.smuraha.mycode.dao.repo.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    @Override
    public List<Section> findAll() {
        return sectionRepository.findAll();
    }
}
