package com.example.chat.domain.plan;

import com.example.chat.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "plans")
public class PlanEntity extends BaseTimeEntity {

    // IDENTITY 수정 필요(고유 아이디)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // (enum을 사용하려다 관리자가 새로운 플랜을 생성하려면 접근이 안되서 불가)
    @Column(nullable = false, unique = true)
    private String name;

    // 월간 제공 토큰량
    @Column(nullable = false)
    private int limitTokens;

    // 접근 가능 모델
    @Column(nullable = false)
    private String availableModels;

    @Column(nullable = false)
    private int price;
}