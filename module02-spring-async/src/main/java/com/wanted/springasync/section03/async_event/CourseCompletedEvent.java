package com.wanted.springasync.section03.async_event;

public record CourseCompletedEvent(
        Long enrollmentId,
        Long userId,
        String courseTitle
) {
}
