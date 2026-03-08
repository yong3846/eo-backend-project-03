package com.example.chat.domain.user;


import com.example.chat.domain.BaseTimeEntity;
import com.example.chat.domain.plan.PlanEntity;
import com.example.chat.domain.user.user_enum.UserRole;
import com.example.chat.domain.user.user_enum.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity extends BaseTimeEntity {

    // IDENTITY 수정 필요(고유 아이디)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    // AI 포털 핵심: 플랜 및 토큰 관리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private PlanEntity plan;

    // 잔여 토큰량
    @Column(nullable = false)
    private int remainingTokens;

    // 비밀번호 재설정 토큰 로직
    private String resetToken;
    private LocalDateTime tokenExpiry;

    // 비즈니스 로직: 토큰 차감 메서드
    public void decreaseTokens(int usedTokens) {
        if (this.remainingTokens < usedTokens) {
            throw new IllegalArgumentException("잔여 토큰이 부족합니다.");
        }
        this.remainingTokens -= usedTokens;
    }
}