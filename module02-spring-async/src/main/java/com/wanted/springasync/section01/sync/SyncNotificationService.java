package com.wanted.springasync.section01.sync;

import com.wanted.springasync.common.support.SleepUtils;
import com.wanted.springasync.domain.course.Enrollment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SyncNotificationService {
    // 메일 발생 기능 수행
    public void sendCompletionEmail(Enrollment enrollment) {

        log.info("[section01] 수료 메일 발송 시작 ! enrollmentId={}", enrollment.getId());

        // 실제 서비스에서는 메일을 보내는 작업 혹은 알림을 저장하는 작업이 일어나지만
        // 지금은 학습을 위해서 오래 걸리는 것처럼 세팅하고 진행한다
        SleepUtils.sleep(3000L);

        log.info("[section01] 수료 메일 발송 종료 ! user={}", enrollment.getUser());

        /*comment
        *  실행하면 총 실행 시간이 3198ms가 찍힘
        *  이 때 3000은 우리가 설정해둔 지체 시간임
        *  이것 때문에 비동기가 필요함 !!*/


    }
}
