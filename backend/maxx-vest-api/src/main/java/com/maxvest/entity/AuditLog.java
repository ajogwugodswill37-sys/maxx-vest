package com.maxvest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "audit_logs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String action;

    @Column
    private String entityType;

    @Column
    private String entityId;

    @Column(columnDefinition = "jsonb")
    private String oldValues;

    @Column(columnDefinition = "jsonb")
    private String newValues;

    @Column
    private String ipAddress;

    @Column
    private String userAgent;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuditStatus status = AuditStatus.SUCCESS;

    @Column
    private String errorMessage;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum AuditStatus {
        SUCCESS, FAILURE
    }
}
