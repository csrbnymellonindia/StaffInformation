package com.codeusingjava.repository;

import com.codeusingjava.model.AuditLogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuditLogRepository extends JpaRepository<AuditLogModel, Long> {

}