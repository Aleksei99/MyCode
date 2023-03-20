package com.smuraha.mycode.dao.repo;

import com.smuraha.mycode.dao.model.CodeSample;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SamplesRepository extends JpaRepository<CodeSample,Long> {
    Page<CodeSample> findAll(Pageable pageable);
}
