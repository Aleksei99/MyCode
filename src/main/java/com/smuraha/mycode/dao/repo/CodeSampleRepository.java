package com.smuraha.mycode.dao.repo;

import com.smuraha.mycode.dao.model.CodeSample;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeSampleRepository extends JpaRepository<CodeSample, Long> {
    Page<CodeSample> findAll(Pageable pageable);
    Page<CodeSample> findAllBySection_Id(Pageable pageable,Long id);
}