package com.smuraha.mycode.service;

import com.smuraha.mycode.dao.model.CodeSample;
import com.smuraha.mycode.dao.model.User;
import com.smuraha.mycode.dao.repo.CodeSampleRepository;
import com.smuraha.mycode.dto.CodeSampleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeSampleServiceImpl implements CodeSampleService{

    private final CodeSampleRepository codeSampleRepository;

    @Override
    public void save(CodeSampleDto dto, User user) {
         codeSampleRepository.save(new CodeSample(dto.getContentArea(), dto.getSection(), user));
    }

    @Override
    public CodeSample findFullById(Long id) {
        return codeSampleRepository.getReferenceById(id);
    }

    @Override
    public CodeSample findForViewById(Long id) {
        CodeSample sample = codeSampleRepository.getReferenceById(id);
        String innerHtml = sample.getInnerHtml();
        String formattedHtml = innerHtml.replaceAll("contenteditable=\"true\"", "")
                .replaceAll("<div></div>", "")
                .replaceAll("<div\\s+class=\"space-between\">.*?</div>", "")
                .replaceAll("<textarea.*?></textarea>", "")
                .replaceAll("display: none", "display: block");
        sample.setInnerHtml(formattedHtml);
        return sample;
    }

    @Override
    public Page<CodeSample> finAllByPage(Pageable pageable) {
        return codeSampleRepository.findAll(pageable);
    }

    @Override
    public Page<CodeSample> findAllBySection_Id(Pageable pageable, Long id) {
        return codeSampleRepository.findAllBySection_Id(pageable, id);
    }
}
