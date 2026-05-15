package com.wanted.springevnet.certificate.repository;

import com.wanted.springevnet.certificate.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate,Long> {

    // 이벤트에서 전달받은 enrollmentId가 존재하는지 검증
    boolean existsByEnrollment_EnrollmentId(Long aLong);
}
