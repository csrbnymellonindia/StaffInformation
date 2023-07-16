package com.codeusingjava.service.auditLogImpl;

import com.codeusingjava.model.AuditLogModel;
import com.codeusingjava.repository.AuditLogRepository;
import com.codeusingjava.service.AuditLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public List<AuditLogModel> findAllAuditLogs() {
        return auditLogRepository.findAll();
    }

    @Override
    public Optional<AuditLogModel> findAuditLogById(long auditLogIdentifier) {
        return auditLogRepository.findById(auditLogIdentifier);
    }

    @Override
    public ResponseEntity<String> addAuditLog(AuditLogModel auditLog) {
        auditLogRepository.save(auditLog);
        return ResponseEntity.ok().body("Audit log added successfully!");
    }

    @Override
    public ResponseEntity<String> updateAuditLog(long auditLogIdentifier, AuditLogModel auditLog) {
        if (validateAuditLog(auditLog)) {
            Optional<AuditLogModel> optionalAuditLog = auditLogRepository.findById(auditLogIdentifier);
            AuditLogModel existingAuditLog = optionalAuditLog.get();
            existingAuditLog.setAuditLogIdentifier(auditLog.getAuditLogIdentifier());
            existingAuditLog.setUserIdentifier(auditLog.getUserIdentifier());
            existingAuditLog.setChangeType(auditLog.getChangeType());
            auditLogRepository.save(existingAuditLog);
            return ResponseEntity.ok().body("Audit log updated successfully!");
        } else {
            return ResponseEntity.badRequest().body("Invalid audit log details");
        }
    }

    @Override
    public ResponseEntity<String> deleteAuditLog(long auditLogIdentifier) {
        auditLogRepository.deleteById(auditLogIdentifier);
        return ResponseEntity.ok().body("Audit log deleted successfully!");
    }

    private boolean validateAuditLog(AuditLogModel auditLog) {
        // Validate AUDIT_LOG_IDENTIFIER
        if (auditLog.getAuditLogIdentifier() <= 0) {
            return false;
        }

        // Validate USER_IDENTIFIER
        if (auditLog.getUserIdentifier() <= 0) {
            return false;
        }

        // Validate CHANGE_TYPE
        if (auditLog.getChangeType() != null && auditLog.getChangeType().isEmpty()) {
            return false;
        }

        return true;
    }
    @Override
    public void createAuditLogEntry(int userIdentifier, String recordUpdateTimestamp, String changeType) {
        AuditLogModel auditLog = new AuditLogModel();
        auditLog.setUserIdentifier(userIdentifier);
        auditLog.setRecordUpdateTimestamp(Timestamp.valueOf(recordUpdateTimestamp));
        auditLog.setChangeType(changeType);
        auditLogRepository.save(auditLog);
    }
}
