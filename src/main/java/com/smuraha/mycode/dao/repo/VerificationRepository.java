package com.smuraha.mycode.dao.repo;

import com.smuraha.mycode.dao.model.Verification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface VerificationRepository extends CrudRepository<Verification,Long> {
    Verification findByTokenAndUsedIsFalseAndValidToAfterAndEmail(String token, LocalDateTime checkTime,String email);
}
