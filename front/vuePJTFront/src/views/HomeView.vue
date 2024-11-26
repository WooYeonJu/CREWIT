<template>
  <div class="container">
    <div class="content">
      <h1>
        Crewit에 오신 것을 <br />
        환영합니다.
      </h1>
      <!-- 로그인 후 서비스 이용하기 버튼 -->
      <RouterLink to="/crewboard" class="login-btn">크루 보러 가기</RouterLink>
    </div>
  </div>
</template>

<script setup>
const message = "SSAFIT에 오신것을 환영합니다.";
</script>

<style scoped>
/* 기본 스타일 초기화 */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body,
html {
  height: 100%;
}

.container {
  position: relative;
  height: 100vh; /* 화면 전체 높이를 차지 */
  background-image: url("../assests/homeview_backgroundImg.jpg");
  background-size: cover; /* 배경 이미지가 전체 컨테이너를 덮도록 */
  background-position: center; /* 배경 이미지가 중앙에 위치하도록 */
  background-repeat: no-repeat; /* 이미지 반복 방지 */
  display: flex;
  justify-content: flex-start; /* 콘텐츠를 왼쪽 정렬 */
  align-items: center; /* 콘텐츠를 세로로 중앙 정렬 */
  color: white; /* 텍스트 색상 */
  text-align: left; /* 텍스트 왼쪽 정렬 */
  padding: 20px; /* 화면 가장자리에 여백 추가 */
}

.container::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.7); /* 검은색 배경 오버레이, 투명도 30% */
  z-index: 1; /* 이미지 위에 오버레이 */
}

.content {
  position: relative;
  max-width: 600px; /* 콘텐츠의 최대 너비 제한 */
  width: 100%; /* 최대 너비에 맞추어 콘텐츠 너비 확장 */
  z-index: 2; /* 텍스트와 버튼을 오버레이 위로 표시 */
}

h1 {
  font-size: 32px;
  font-weight: bold;
  margin-bottom: 20px; /* 타이틀과 버튼 간 여백 */
  margin-left: 20%;
}

.login-btn {
  display: inline-block;
  padding: 15px 30px;
  background-color: #4caf50; /* 버튼 색상 */
  color: white; /* 버튼 텍스트 색상 */
  font-size: 18px;
  text-decoration: none;
  border-radius: 5px; /* 둥근 모서리 */
  transition: background-color 0.3s ease; /* 호버 효과 전환 */
  margin-left: 20%;
}

.login-btn:hover {
  background-color: #45a049; /* 호버 시 버튼 색상 변경 */
}

@media (max-width: 480px) {
  h1 {
    font-size: 7vw; /* 더 작은 화면에서는 제목 폰트를 더 작게 조정 */
    margin-left: 7px; /* 버튼 왼쪽 여백 없앰 */
  }

  .login-btn {
    font-size: 3vw; /* 더 작은 화면에서는 버튼 폰트를 더 작게 조정 */
    margin-left: 7px; /* 버튼 왼쪽 여백 없앰 */
    min-width: 100px; /* 더 작은 화면에서 최소 너비 더 줄임 */
    padding: 10px 15px;
  }
}
</style>
