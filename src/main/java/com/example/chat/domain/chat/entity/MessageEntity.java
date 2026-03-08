package com.example.chat.domain.chat.entity;

import com.example.chat.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "chat_messages")
public class MessageEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 어떤 세션에 속한 메시지인지 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity session;

    @Column(nullable = false)
    private String role;

    // 대화 내용
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // 모델명
    @Column(nullable = false)
    private String modelName;

    // 소모된 토큰
    @Column(nullable = false)
    private int usedTokens;
}