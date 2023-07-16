package com.codeusingjava.controller;

import com.codeusingjava.model.AuditLogModel;
import com.codeusingjava.service.AuditLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addAuditLog(@RequestBody AuditLogModel auditLog) {
        return auditLogService.addAuditLog(auditLog);
    }

    @PutMapping("/update/{auditLogIdentifier}")
    public ResponseEntity<String> updateAuditLog(@PathVariable long auditLogIdentifier, @RequestBody AuditLogModel auditLog) {
        return auditLogService.updateAuditLog(auditLogIdentifier, auditLog);
    }

    @DeleteMapping("/delete/{auditLogIdentifier}")
    public ResponseEntity<String> deleteAuditLog(@PathVariable long auditLogIdentifier) {
        return auditLogService.deleteAuditLog(auditLogIdentifier);
    }

    @GetMapping("/getAll")
    public List<AuditLogModel> getAllAuditLogs() {
        return auditLogService.findAllAuditLogs();
    }

    @GetMapping("/get/{auditLogIdentifier}")
    public ResponseEntity<AuditLogModel> getAuditLogById(@PathVariable long auditLogIdentifier) {
        Optional<AuditLogModel> auditLog = auditLogService.findAuditLogById(auditLogIdentifier);
        return auditLog.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
