# CREWIT

| "크루(CREW)"와 "하자(DO IT)"을 합쳐, 함께 운동할 사람을 모집하여 활동할 수 있는 커뮤니티

![logo-removebg-preview](https://github.com/user-attachments/assets/0f4c5e7c-2900-42dd-9409-e72b434ca601)

## 프로젝트 개요

- 운동 크루 커뮤니티 형성 및 공공 체육 시설 정보 제공 서비스
- 프로젝트 제목: CREWIT
- 프로젝트 기간: 2024.11.11 ~ 2024.11.26
- 참여자: 우연주, 백승민

## 사용 기술 스택

- Spring Boot
- Spring
- Java
- HTML5
- CSS3
- JavaScript
- Vue.js
- MySQL

## 개발 도구

- Spring Tool Suite 3
- VSCode

## 시연 영상

시연 영상: ![바로가기](https://drive.google.com/file/d/1HochLzugwRBBXeGZ66m4WTkfwRYEGF6J/view?usp=sharing)

## 요구사항 정의서

### 요구사항 정의서(유저)

| **요구사항 ID** | **요구사항명** | **기능ID**      | **기능명**                    | **상세설명**                                                                                                | **필수 데이터**                                    | **선택 데이터**                  |
| --------------- | -------------- | --------------- | ----------------------------- | ----------------------------------------------------------------------------------------------------------- | -------------------------------------------------- | -------------------------------- |
| MEM01           | 로그인         | MEM01_LOGIN_N01 | 자체 로그인 기능              | 웹 페이지 기능을 사용하기 위해 로그인 필요. 로그인 시 아이디, 비밀번호 입력 후 인증 절차 진행               | 아이디, 비밀번호                                   |                                  |
|                 |                | MEM01_LOGIN_N02 | 아이디 찾기                   | 이름과 핸드폰 번호를 이용하여 아이디 찾기                                                                   | 이름, 핸드폰 번호                                  |                                  |
|                 |                | MEM01_LOGIN_N03 | 비밀번호 찾기                 | 아이디, 이름, 핸드폰 번호 확인 후 비밀번호 변경 화면으로 이동                                               | 아이디, 이름, 핸드폰 번호, 이메일                  |                                  |
|                 |                | MEM01_LOGIN_N04 | 비밀번호 변경(잊어버린 경우)  | 비밀번호를 잊어버렸거나, 변경 시 비밀번호 재설정 위한 화면                                                  | 변경할 비밀번호, 아이디, 이름, 핸드폰 번호, 이메일 |                                  |
| MEM02           | 회원가입       | MEM02_SIGNUP01  | 회원가입                      | 사용자 아이디, 비밀번호, 이메일 등 기본 정보를 입력하여 회원가입 진행. 이메일 및 휴대폰 번호 중복 체크 진행 | 아이디, 비밀번호, 이름, 이메일, 휴대폰 번호        | 프로필 사진                      |
| MEM03           | 회원 정보 수정 | MEM03_MODIFY01  | 회원 정보 수정                | 비밀번호 입력 후 사용자인증을 통해 회원 정보 수정 가능. 이메일, 전화번호 변경 시 확인 절차 추가             | 비밀번호                                           | 프로필 사진, 이메일, 휴대폰 번호 |
|                 |                | MEM03_MODIFY02  | 비밀번호 변경(단순 정보 수정) | 비밀번호 수정 시 현재 비밀번호 입력하여 본인 확인 바꿀 비밀번호(재확인까지) 입력받기                        | 현재 비밀번호, 바꿀 비밀번호                       |                                  |
| MEM04           | 회원 탈퇴      | MEM04_DROP01    | 회원 탈퇴                     | 회원 탈퇴 시 비밀번호 입력 후 사용자인증, 탈퇴 여부를 최종 확인하는 팝업 띄우기                             | 비밀번호, 탈퇴 확인 팝업                           |                                  |
| MEM05           | 로그아웃       | MEM05_LOGOUT01  | 로그아웃                      | 현재 로그인된 사용자 로그아웃 처리                                                                          |                                                    |                                  |

### 요구사항 정의서(커뮤니티)

| **요구사항 ID** | **요구사항명** | **기능ID**                 | **기능명**  | **상세설명**                                                                            | **필수 데이터**                         | **선택 데이터**       |
| --------------- | -------------- | -------------------------- | ----------- | --------------------------------------------------------------------------------------- | --------------------------------------- | --------------------- |
| COM01           | 전체 크루      | COM01_CREATE_CREW01        | 크루 생성   | 유저가 함께 운동할 사람을 모집할 크루를 생성. 운동 종목, 지역, 성별 등을 선택할 수 있음 | 크루 이름, 크루 리더, 인원수            | 운동 종목, 위치, 성별 |
|                 |                | COM01_JOIN_CREW01          | 크루 신청   | 사용자가 원하는 크루를 검색하여 신청. 인원수가 마감되지 않은 크루에만 신청 가능         | 유저 아이디, 이름                       |                       |
|                 |                | COM01_DROP_CREW01          | 크루 삭제   | 크루 리더가 본인의 크루를 삭제할 수 있음. 삭제 시 인원도 자동으로 제외됨                | 유저 아이디, 크루 아이디                |                       |
|                 |                | COM01_INFO_CREW01          | 크루 정보   | 크루의 상세 정보를 확인할 수 있음                                                       | 유저 아이디, 크루 아이디                |                       |
| COM02           | 크루 내 게시판 | COM02_CREATE_CREWBOARD01   | 게시글 생성 | 크루에 가입한 유저들이 소통할 수 있는 게시글 생성                                       | 크루 아이디, 게시글 아이디              |                       |
|                 |                | COM02_UPDATE_CREWBOARD02   | 게시글 수정 | 게시글을 작성한 유저가 게시글을 수정할 수 있음                                          | 게시글 아이디, 유저 아이디              |                       |
|                 |                | COM02_DROP_CREWBOARD03     | 게시글 삭제 | 게시글을 작성한 유저가 해당 게시글을 삭제할 수 있음                                     | 유저 아이디, 게시글 아이디              |                       |
| COM03           | 게시판 댓글    | COM03_CREATE_BOARDREVIEW01 | 댓글 작성   | 크루에 가입한 유저는 크루 내 게시판에 댓글을 작성할 수 있음                             | 유저 아이디, 게시글 아이디, 댓글 아이디 |                       |
|                 |                | COM03_UPDATE_BOARDREVIEW02 | 댓글 수정   | 댓글을 작성한 유저가 해당 댓글을 수정할 수 있음                                         | 유저 아이디, 게시글 아이디, 댓글 아이디 |                       |
|                 |                | COM03_UPDATE_BOARDREVIEW03 | 댓글 삭제   | 댓글을 작성한 유저가 해당 댓글을 삭제할 수 있음                                         | 유저 아이디 게시글 아이디, 댓글 아이디  |                       |

### 요구사항 정의서(시설 예약)

| **요구사항 ID** | **요구사항명** | **기능ID**            | **기능명**                    | **상세설명**                                                                              | **필수 데이터**                             | **선택 데이터** |
| --------------- | -------------- | --------------------- | ----------------------------- | ----------------------------------------------------------------------------------------- | ------------------------------------------- | --------------- |
| REG01           | 지도           | REG01_PRINTMAP01      | 지도 출력                     | 공공 체육 시설의 위치를 마커로 표시한 지도 출력                                           | 공공 체육 시설 이름, 위치                   |                 |
|                 |                | REG01_MARKER01        | 마커 출력                     | 마커 클릭 시 해당 시설의 상세 정보 페이지로 이동                                          |                                             |                 |
| REG02           | 시설 정보      | REG02_INFO01          | 공공 체육 시설 상세 정보 출력 | 공공 체육 시설의 이름, 위치 등의 정보를 출력하고 공식 홈페이지로 넘어갈 수 있는 링크 제공 | 공공 체육 시설 이름, 위치, 예약 사이트 링크 |                 |
| REG03           | 시설 정보 리뷰 | REG03_CREATE_REVIEW01 | 리뷰 작성                     | 해당 공공 체육 시설에 대한 리뷰를 별점과 함께 작성할 수 있음                              | 유저 아이디, 리뷰 아이디                    |                 |
|                 |                | REG03_UPDATE_REVIEW02 | 리뷰 수정                     | 해당 리뷰를 작성한 유저가 해당 리뷰를 수정할 수 있음                                      | 유저 아이디, 리뷰 아이디                    |                 |
|                 |                | REG03_DROP_REVIEW03   | 리뷰 삭제                     | 해당 리뷰를 작성한 유저가 해당 리뷰를 삭제할 수 있음                                      | 유저 아이디, 리뷰 아이디                    |                 |

## 유즈케이스 다이어그램

![usecase_diagram](https://github.com/user-attachments/assets/21a27b35-1eff-4828-874a-242a2a635aa8)

## 테이블 구조도(ERD)

![image](https://github.com/user-attachments/assets/02553c74-1eb2-4164-9afe-2bf414f9cc5a)

## 클래스 다이어그램

![class_diagram](https://github.com/user-attachments/assets/f2a21607-e163-4546-8c86-ea133ada8458)

## 화면 설계서

![mockup1](https://github.com/user-attachments/assets/03fe0cb9-3cd0-4439-b17f-c90fc6c6c4e3)

![mockup2](https://github.com/user-attachments/assets/d14307d8-bcac-4280-9ae8-82e1c4f4989c)

