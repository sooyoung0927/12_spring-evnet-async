package com.wanted.springasync.section04.async_exception;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/section04")
public class AsyncExceptionController {

    private final AsyncExceptionService asyncExceptionService;

    @GetMapping("/void-exception")
    public String voidException(){

        /*comment
        *  메인 요청 스레드에서는 비동기 요청 스레드에서 발생하는 예외 타입을 핸들링 하려고 해도 동작하지 않는다
        *  별도의 스레드에서 동작하고 있기 때문에 비동기 메서드 관련 예외처리는 별도의 비동기 전용 예외처리를 해주어야 한다 */

        asyncExceptionService.voidExceptionAsync();

        return "void 반환형의 @Async 예외 작업 동작함";
    }

    @PostMapping("/retry-jobs")
    public AsyncRetryJobResponse createRetryJob(
            @RequestParam(defaultValue = "1") Long userId,
            @RequestParam(defaultValue = "2") int failUntilAttempt,
            @RequestParam(defaultValue = "3") int maxRetryCount
    ) {
        AsyncRetryJobResponse response = asyncExceptionService.createRetryDemoJob(userId, failUntilAttempt, maxRetryCount);
        asyncExceptionService.processRetryJobAsync(response.jobId());
        return response;
    }
}
