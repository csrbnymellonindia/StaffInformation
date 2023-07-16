package com.codeusingjava.service;

import com.codeusingjava.model.AuditLogModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AuditLogService {

    List<AuditLogModel> findAllAuditLogs();

    Optional<AuditLogModel> findAuditLogById(long auditLogIdentifier);

    ResponseEntity<String> addAuditLog(AuditLogModel auditLog);

    ResponseEntity<String> updateAuditLog(long auditLogIdentifier, AuditLogModel auditLog);

    ResponseEntity<String> deleteAuditLog(long auditLogIdentifier);

    void createAuditLogEntry(int userIdentifier, String recordUpdateTimestamp, String changeType);
}
