package com.wanted.springevnet.certificate.service;

import com.wanted.springevnet.certificate.entity.Certificate;
import com.wanted.springevnet.certificate.repository.CertificateRepository;
import com.wanted.springevnet.enrollment.entity.Enrollment;
import com.wanted.springevnet.enrollment.repository.EnrollmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
public class CertificateService {

     private final EnrollmentRepository enrollmentRepository;
     private final CertificateRepository certificateRepository;

    public CertificateService(EnrollmentRepository enrollmentRepository, CertificateRepository certificateRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.certificateRepository = certificateRepository;
    }

    /*comment
    *  Transactional 은 주요 속성들이 있다
    *  - propagation : 전파의 의미를 가지고 있고 default value 는
    *    * REQUIRED(상위 트랜잭션이 있다면 같이)
    *    * REQUIRES_NEW (상위 트랜잭션과 분리된 독립된 트랜잭션 결계를 만든다)
    *  - rollbackFor : 예외는 크게 두 가지가 있다 > 언체크 예외 , 체크 예외
    *    체크 에외 시에도 rollback을 하기 위해서 작성해야 한다
    *    예) rollbackFor = Exception.class
    *  - timeout = 3 제한 시간 초과 시 롤백 (단위: 초)
    *    너무 오래 걸리는 작업을 제한할 때 사용한다
    *  */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void issueAfterCourseCompletion(Long enrollmentId, String courseTitle, String codePrefix) {
        if (certificateRepository.existsByEnrollment_EnrollmentId(enrollmentId)) {
            log.info("[certificateService] 이미 수료증이 존재하므로 발급을 건너뜁니다. enrollmentId={}", enrollmentId);
            return;
        }

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalArgumentException("수강 정보를 찾을 수 없습니다. enrollmentId=" + enrollmentId));

        Certificate certificate = certificateRepository.save(
                Certificate.issue(enrollment, codePrefix + "-" + UUID.randomUUID())
        );

        log.info(
                "[certificateService] 새 트랜잭션에서 수료증 발급 완료. courseTitle={}, certificateId={}",
                courseTitle,
                certificate.getCertificateId()
        );
    }


}
