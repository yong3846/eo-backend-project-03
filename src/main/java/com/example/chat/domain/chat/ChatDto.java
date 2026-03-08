package com.example.chat.domain.chat;

import com.example.chat.domain.chat.entity.MessageEntity;
import com.example.chat.domain.chat.entity.SessionEntity;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ChatDto {

    // 1. 채팅방(세션) 생성 요청
    public record SessionCreateRequest(
            @NotBlank(message = "채팅방 제목을 입력해주세요.")
            String title
    ) {}

    // 2. 채팅방(세션) 목록/단일 응답
    public record SessionResponse(
            Long id,
            String title,
            LocalDateTime createdAt
    ) {
        public static SessionResponse fromEntity(SessionEntity session) {
            return new SessionResponse(
                    session.getId(),
                    session.getTitle(),
                    session.getCreatedAt()
            );
        }
    }

    // 3. AI에게 질문 보내기 요청 (메시지)
    public record MessageRequest(
            @NotBlank(message = "질문 내용을 입력해주세요.")
            String content,

            @NotBlank(message = "사용할 AI 모델을 선택해주세요.")
            String modelName // 예: GPT-4, Gemini-Pro
    ) {}

    // 4. 채팅 메시지 내역 응답 (질문 & 답변 모두 이걸로 리턴)
    public record MessageResponse(
            Long id,
            String role, // "user" or "assistant"
            String content,
            String modelName,
            int usedTokens,
            LocalDateTime createdAt
    ) {
        public static MessageResponse fromEntity(MessageEntity message) {
            return new MessageResponse(
                    message.getId(),
                    message.getRole(),
                    message.getContent(),
                    message.getModelName(),
                    message.getUsedTokens(),
                    message.getCreatedAt()
            );
        }
    }
}