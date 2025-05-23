package com.ploggingbuddy.presentation.gathering.controller;

import com.ploggingbuddy.application.gathering.*;
import com.ploggingbuddy.domain.member.entity.Member;
import com.ploggingbuddy.presentation.gathering.dto.request.*;
import com.ploggingbuddy.presentation.gathering.dto.response.GetGatheringDetailResponse;
import com.ploggingbuddy.presentation.gathering.dto.response.GetGatheringsNearSpotResponse;
import com.ploggingbuddy.security.aop.CurrentMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gathering")
@RequiredArgsConstructor
@Tag(name = "Gathering Api", description = "게시글 API")
public class GatheringController {
    private final CreateGatheringUseCase createGatheringUseCase;
    private final UpdatePostStatusAsDeletedUseCase updatePostStatusAsDeletedUseCase;
    private final UpdateGatheringAmountUseCase updateGatheringAmountUseCase;
    private final FinishGatheringUseCase finishGatheringUseCase;
    private final DecidePendingPostStatusUseCase decidePendingPostStatusUseCase;
    private final GetGatheringDataUseCase getGatheringDataUseCase;
    private final GetGatheringsNearSpotUseCase getGatheringsNearSpotUseCase;

    @PostMapping("/new")
    @Operation(summary = "모집 게시글 작성", description = "새 모집 게시글을 작성하는 api입니다.")
    public ResponseEntity<Void> postNewGathering(
            @CurrentMember Member member,
            @RequestBody PostGatheringPostDto requestBody
    ) {
        createGatheringUseCase.execute(requestBody, member.getId());
        return ResponseEntity.ok().build();
    }

    //1개 조회
    @GetMapping("/{postId}")
    @Operation(summary = "모집게시글 조회", description = "모임글 데이터를 조회하는 api입니다.")
    public ResponseEntity<GetGatheringDetailResponse> getGatheringData(@CurrentMember Member member,
            @PathVariable Long postId
    ){
        return ResponseEntity.ok(getGatheringDataUseCase.execute(member, postId));
    }

    //인근 5km 이내 모임글 리스트 조회
    @GetMapping("/spot/{latitude}/{longitude}")
    @Operation(summary = "반경 내 모집게시글 조회", description = "특정 위경도 위치의 반경 5km 내 모임글리스트를 조회 api입니다.")
    public ResponseEntity<GetGatheringsNearSpotResponse> getGatheringsNearSpot(
            @PathVariable Double latitude,
            @PathVariable Double longitude){
        return ResponseEntity.ok(getGatheringsNearSpotUseCase.execute(latitude, longitude));
    }

    @PostMapping("/delete")
    @Operation(summary = "모집 게시글 삭제", description = "본인이 작성한 게시글을 삭제하는 api입니다.")
    public ResponseEntity<Void> updateGatheringAsDeleted(
            @CurrentMember Member member,
            @RequestBody UpdatePostStatusAsDeletedDto requestBody
    ) {
        updatePostStatusAsDeletedUseCase.execute(requestBody, member.getId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    @Operation(summary = "모집 게시글 인원수, 이미지 수정", description = "본인이 작성한 게시글의 모집 인원과 이미지를 수정하는 api입니다.")
    public ResponseEntity<Void> updateGatheringAmount(
            @CurrentMember Member member,
            @RequestBody UpdateGatheringAmountDto requestBody
    ) {
        updateGatheringAmountUseCase.execute(requestBody, member.getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/gathering-finish/{postId}")
    @Operation(summary = "모집 조기 마감", description = "모집 중인 게시글을 조기 마감하는 api입니다.")
    public ResponseEntity<Void> finishGathering(
            @CurrentMember Member member,
            @PathVariable Long postId
    ) {
        finishGatheringUseCase.execute(postId, member.getId());
        return ResponseEntity.ok().build();
    }

    //모임 강행할지 결정 api
    @PutMapping("/status-decision")
    @Operation(summary = "모임 강행 여부 선택", description = "조기마감시킨 모임을 강행할지 결정하는 api입니다.")
    public ResponseEntity<Void> decideProceedOrNot(
            @CurrentMember Member member,
            @RequestBody UpdatePendingPostStatusDto requestBody
    ) {
        decidePendingPostStatusUseCase.execute(member.getId(), requestBody);
        return ResponseEntity.ok().build();
    }
}
