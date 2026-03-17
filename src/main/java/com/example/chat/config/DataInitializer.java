package com.example.chat.config;

import com.example.chat.domain.plan.PlanEntity;
import com.example.chat.repository.PlanRepository; // 본인의 PlanRepository 경로에 맞게 수정해주세요
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 애플리케이션 시작 시 DB에 기본 데이터가 없으면 자동으로 채워주는 클래스
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PlanRepository planRepository;

    @Override
    public void run(String... args) {
        // DB에 플랜 정보가 하나도 없을 때만 기본 플랜 3가지를 생성
        if (planRepository.count() == 0) {
            List<PlanEntity> defaultPlans = List.of(
                    PlanEntity.builder()
                            .name("BASIC")
                            .price(0)
                            .limitTokens(5000)
                            .availableModels("gpt-3.5-turbo,gpt-4o-mini")
                            .build(),

                    PlanEntity.builder()
                            .name("PRO")
                            .price(9900)
                            .limitTokens(50000)
                            .availableModels("gpt-3.5-turbo,gpt-4o-mini")
                            .build(),

                    PlanEntity.builder()
                            .name("PREMIUM")
                            .price(19900)
                            .limitTokens(200000)
                            .availableModels("gpt-3.5-turbo,gpt-4,gpt-4o-mini")
                            .build()
            );

            planRepository.saveAll(defaultPlans);
        }
    }
}