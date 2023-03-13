package com.smuraha.mycode.dao.repo;

import com.smuraha.mycode.dao.model.Section;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends CrudRepository<Section,Long> {
    List<Section> findAll();
}
