package com.wanted.springasync.section03.async_event;

import com.wanted.springasync.common.support.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class CompleteSummaryService {

    /*comment
    *  비동기 메서드의 반환형 차이
    *  1. void : 비동기 메서드 호출하는 곳은 비동기 완료 결과를 받을 수 없다
    *  2. CompletableFuture : 비동기 메서드 호출하는 곳에서 thenAccept(), join(), get() 등올 비동기 메서드 완료 결과를 이어서 다룸 */
    @Async("classTaskExecutor")
    public CompletableFuture<String> createSummaryAsync(Long enrollmentId) {
        log.info("[section03] ✅비동기+CompletableFuture✅수강 완료 시 진행되는 이벤트 작업 시작 / 작업 중인 Thread = {}", Thread.currentThread().getName());

        SleepUtils.sleep(3000L);
        // 추후 실제 프로젝트에서는 db에 요약본이 저장되거나 조회하는 형태로 한다
        String summary = "enrollmentId = "+ enrollmentId + "수강 완료 요약본 생성됨";

        log.info("[section03] ✅비동기+CompletableFuture✅수강 완료 시 진행되는 이벤트 작업 종료 / 작업 중인 Thread = {}", Thread.currentThread().getName());

        // 미래에 비동기 처리가 되고서 설정해둔 결과
        return CompletableFuture.completedFuture(summary);
    }
}
