<template>
  <div class="container">
    <h4 class="title">아이디 찾기</h4>
    <div class="content">
      <span
        v-if="store.serachedId == null || store.serachedId === ''"
        class="no-info"
      >
        가입된 정보가 없습니다.
        <RouterLink to="/signup" class="signup-link">회원가입</RouterLink>
      </span>
      <span v-else class="found-id"
        >회원님의 아이디는 <strong>{{ store.serachedId }}</strong> 입니다.
        <div class="move-to-login">
          <RouterLink :to="{ name: 'login' }">로그인 페이지로 이동</RouterLink>
        </div>
      </span>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/userStore";

const store = useUserStore();
</script>

<style scoped>
/* 기본 컨테이너 스타일 */
.container {
  margin: 20px;
  font-family: Arial, sans-serif;
}

/* 제목 스타일 */
.title {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

/* 콘텐츠 영역 */
.content {
  text-align: center;
  font-size: 16px;
  color: #555;
}

/* 아이디를 찾을 수 없는 경우 메시지 스타일 */
.no-info {
  color: #f44336; /* 빨간색 */
  font-size: 18px;
}

/* 회원가입 링크 스타일 */
.signup-link {
  color: #2196f3; /* 파란색 */
  text-decoration: none;
  font-weight: bold;
}

.signup-link:hover {
  text-decoration: underline;
  color: #1976d2; /* 링크 hover 색상 */
}

/* 아이디를 찾은 경우 메시지 스타일 */
.found-id {
  color: #565656; /* 초록색 */
  font-size: 18px;
}

.found-id strong {
  font-weight: bold;
}

.move-to-login {
}
</style>
