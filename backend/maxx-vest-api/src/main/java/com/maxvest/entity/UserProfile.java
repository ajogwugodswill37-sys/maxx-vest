package com.maxvest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column
    private String profileImageUrl;

    @Column
    private String bio;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String address;

    @Column
    @Enumerated(EnumType.STRING)
    private AccountType accountType = AccountType.STANDARD;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private KycStatus kyc_status = KycStatus.NOT_STARTED;

    @Column
    private LocalDateTime kycVerifiedAt;

    @Column
    @Enumerated(EnumType.STRING)
    private TradingExperience tradingExperience = TradingExperience.BEGINNER;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum AccountType {
        STANDARD, PROFESSIONAL, VIP
    }

    public enum KycStatus {
        NOT_STARTED, PENDING, VERIFIED, REJECTED
    }

    public enum TradingExperience {
        BEGINNER, INTERMEDIATE, ADVANCED, EXPERT
    }
}
