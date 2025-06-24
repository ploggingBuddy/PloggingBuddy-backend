# PloggingBuddy-SERVER

## 📝 Introduce
Plogging Buddy는 사용자의 위치 기반으로 플로깅 모임을 조회하고, 참여하며, 새로운 모임을 개설할 수 있는 서비스입니다.

## ⚙️ Develop Environment

<div align="center">
	<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/></a>
	<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/></a>
	<img src="https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white"/></a>
	<img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white"/></a>
</div>
<br>




## **Contributor**  

<div align="center">
  <table>
    <tr>
      <td align="center" style="padding: 20px;" width="320">
        <a href="https://github.com/codingmy" target="_blank">
          <img src="https://github.com/2024-MobileProgramming/MP-Server/assets/97686638/f09cd579-9677-45c8-90f0-5a1b8c5dd8c3" width="300" style="border-radius: 50%;" alt="최민영"/><br />
          <h3 style="margin: 10px 0 5px; color: #007acc;">최민영</h3>
          <p style="margin: 0; font-weight: bold; color: #555;"></p>
          <p style="margin: 5px 0; font-size: 14px; color: #888;">커뮤니티 CRUD,<br> 게시글 상태 스케줄링,<br> 거리 기반 기능 구축,<br> 모임 신청 기능 구현</p>
        </a>
      </td>
      <td align="center" style="padding: 20px;" width="340">
        <a href="https://github.com/hamlsy" target="_blank">
          <img src="https://github.com/user-attachments/assets/f2e5c5d2-ab27-4d01-b901-ffff424b5c0f" width="300" style="border-radius: 50%;" alt="이승영"/><br />
          <h3 style="margin: 10px 0 5px; color: #007acc;">이승영</h3>
          <p style="margin: 0; font-weight: bold; color: #555;"></p>
          <p style="margin: 5px 0; font-size: 14px; color: #888;">프로젝트 초기 세팅, <br> 소셜 로그인, <br> 회원 정보 api 구현, <br> 서버·DB 배포<br> </p>
        </a>
      </td>
    </tr>
  </table>
</div>


## **System architecture**
![Image](https://github.com/user-attachments/assets/01a01bfd-7d59-4cb0-becc-31fbda5e0085)

## **ER Diagram**
![Image](https://github.com/user-attachments/assets/59529040-88dd-4a0e-8462-1f9bfa29babf)

## **구현 API 리스트**

### 🔐 Kakao 인증 API
* 카카오 계정 연동 해제

### 👤 회원 API
* 로그인 회원 정보 조회
* 내 정보 조회
* 회원 주소 검증
* 회원 닉네임 수정
* 회원 주소 수정


### 📢 알림 API
* 참가 신청 알림 발송

### 📄 모임(게시글) API
* 모집 게시글 작성
* 모집 게시글 상세 조회
* 반경 5km 내 모집 게시글 목록 조회
* 모집 게시글 삭제
* 모집 게시글 인원수/이미지 수정
* 모집 게시글 조기 마감
* 마감된 모임 강행 여부 결정

### ✅ 참가 신청 API
* 모집 게시글 참가 신청
* 모집 게시글 참가자 목록 조회


## **개발 사항**
### 최민영
모집 게시글 CRUD
* 모집 게시글 작성, 수정, 삭제, 상세 조회 기능 구현
* 모집 마감일자, 진행 일자 및 시간 기준 게시글 관리

위치 기반 모집글 조회
* 반경 5km 이내 위치 기반 모집글 조회 기능 구현

모집 상태 자동 갱신 스케줄링
* Spring TaskScheduler 기반 모집 상태 및 모임 상태 자동 갱신 기능 구현

모임 신청 기능 
* 동시성 이슈 처리
* 모임 최대 인원과 참가 신청 기능 간 동시성 처리

클린 아키텍처 기반 코드 스타일 적용
* 유지보수성과 확장성을 고려한 DDD 아키텍쳐 기반 모듈 설계 및 클린 아키텍쳐 적용


### 이승영
팀원 정보 api 구현
- 회원 관련 모임 조회 구현
- 회원 주소 정보 검증 api 구현

소셜 로그인/회원가입
- Spring Security OAuth2 카카오 로그인 및 회원가입 구현
- Jwt 기반 Spring Security Filter chain 구현

서버 및 DB 배포
- Koyeb -> Spring 서버 배포
- Railway -> DB(MYSQL) 배포 및 연결

프로젝트 구조 설계 및 초기 세팅
- DDD(Domain-Driven Design) 아키텍처 기반 모듈 설계
- domain, application, infrastructure, presentation 계층 구분 및 역할 정의
- 계층간 의존성 분리
- Swagger Api 세팅

## **Directory**

<pre>
```
├─🗂️ main
│  ├─🗂️ java
│  │  └─🗂️ com
│  │          └─🗂️ PloggingBuddy
│  │              ├─🗂️ application
│  │              │  └─🗂️ enrollment      
│  │              │  └─🗂️ gathering
│  │              │  └─🗂️ member
│  │              │  └─🗂️ validator
│  │              ├─🗂️ domain
│  │              │  └─🗂️ auditing      
│  │              │  └─🗂️ enrollment
│  │              │  └─🗂️ gathering
│  │              │  └─🗂️ member
│  │              │  └─🗂️ notification
│  │              │  └─🗂️ postImage
│  │              │  └─🗂️ scheduler
│  │              ├─🗂️ global
│  │              │  └─🗂️ annotation      
│  │              │  └─🗂️ auth
│  │              │  └─🗂️ config
│  │              │  └─🗂️ exception
│  │              │  └─🗂️ vo
│  │              ├─🗂️ presentation
│  │              ├─🗂️ security
│  └─🗂️ resources


```
</pre>





## **Branch Convention**
- Git-Flow 방식으로 진행
- main 브랜치 :  배포 브랜치

**브랜치명 예시** : feat/이슈번호-개발사항


## **Git 커밋 컨벤션**

*# feat : 기능 추가*

*# fix : 버그 수정*

*# docs : 문서 수정*

*# add : 파일, 의존성 추가*

*# refactor : 코드 리팩토링*

*# style : 코드 의미에 영향을 주지 않는 변경사항*

*# chore : 빌드 부분 혹은 패키지 매니저 수정사항*

**커밋 예시** : [feat/#38] 유저 스케줄링 수정

