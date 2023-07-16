package com.codeusingjava.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "AUDIT_LOG")
public class AuditLogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUDIT_LOG_IDENTIFIER")
    private int auditLogIdentifier;

    @Column(name = "USER_IDENTIFIER")
    private int userIdentifier;

    @Column(name = "RECORD_UPDATE_TIMESTAMP", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp recordUpdateTimestamp;

    @Column(name = "CHANGE_TYPE")
    private String changeType;

    public int getAuditLogIdentifier() {
        return auditLogIdentifier;
    }

    public void setAuditLogIdentifier(int auditLogIdentifier) {
        this.auditLogIdentifier = auditLogIdentifier;
    }

    public int getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(int userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public Timestamp getRecordUpdateTimestamp() {
        return recordUpdateTimestamp;
    }

    public void setRecordUpdateTimestamp(Timestamp recordUpdateTimestamp) {
        this.recordUpdateTimestamp = recordUpdateTimestamp;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public AuditLogModel() {
    }
}
