package com.smuraha.mycode.dao.repo;

import com.smuraha.mycode.dao.model.CodeSample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeSampleRepository extends JpaRepository<CodeSample, Long> {
}