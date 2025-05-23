package com.ploggingbuddy.domain.enrollment.service;

import com.ploggingbuddy.domain.enrollment.entity.Enrollment;
import com.ploggingbuddy.domain.enrollment.repository.EnrollmentRepository;
import com.ploggingbuddy.domain.gathering.entity.Gathering;
import com.ploggingbuddy.domain.gathering.entity.GatheringStatus;
import com.ploggingbuddy.domain.gathering.repository.GatheringRepository;
import com.ploggingbuddy.domain.member.entity.Member;
import com.ploggingbuddy.domain.member.repository.MemberRepository;
import com.ploggingbuddy.global.exception.base.BadRequestException;
import com.ploggingbuddy.global.exception.base.InternalServerErrorException;
import com.ploggingbuddy.global.exception.code.ErrorCode;
import com.ploggingbuddy.presentation.enrollment.dto.EnrollmentData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final MemberRepository memberRepository;
    private final GatheringRepository gatheringRepository;

    public void saveEnrollment(Long postId, Long userId) {
        // 모집글을 락 걸어서 조회
        Gathering gathering = gatheringRepository.findWithLockById(postId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.INVALID_POST_ID));

        // 현재 신청 인원 수 확인
        long currentCount = getEnrolledCount(postId);

        // 중복 신청 경우
        boolean alreadyApplied = enrollmentRepository.existsByPostIdAndMemberId(postId, userId);
        if (alreadyApplied) {
            throw new BadRequestException(ErrorCode.DUPLICATED_ENROLLMENT);
        }

        // 최대 신청 인원 체크
        if (currentCount >= gathering.getParticipantMaxNumber()) {
            throw new BadRequestException(ErrorCode.EXCEED_PARTICIPANT_LIMIT);
        }

        log.error("enrolling to postId " + postId);
        Enrollment enrollment = new Enrollment(userId, postId);
        enrollmentRepository.save(enrollment);

        currentCount = getEnrolledCount(postId);
        if (currentCount == gathering.getParticipantMaxNumber()) {
            gathering.updatePostStatus(GatheringStatus.GATHERING_CONFIRMED);
        }
    }

    public List<EnrollmentData> getEnrollmentList(Long postId) {
        return enrollmentRepository.findAllByPostId(postId)
                .stream()
                .map(enrollment -> {
                    Member member = memberRepository.findById(enrollment.getMemberId())
                            .orElseThrow(() -> new InternalServerErrorException(ErrorCode.INTERNAL_SERVER_ERROR));
                    return new EnrollmentData(member.getId(), member.getNickname(), member.getProfileImageUrl());
                })
                .toList();
    }

    public Long getEnrolledCount(Long postId) {
        return enrollmentRepository.countByPostId(postId);
    }

}
