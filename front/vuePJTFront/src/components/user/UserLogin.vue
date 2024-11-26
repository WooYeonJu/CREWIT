<template>
  <div class="login-container">
    <h4 class="login-title">로그인</h4>
    <form @submit.prevent="login" class="login-form">
      <div class="dust-class">
        <label for="id"><span> * </span>ID</label>
        <input
          type="text"
          placeholder="ID입력"
          v-model.trim="id"
          class="login-input"
          required
        />
      </div>
      <div class="dust-class">
        <label for="id"><span> * </span>PW</label>
        <input
          type="password"
          placeholder="PW입력"
          v-model.trim="pw"
          class="login-input"
          required
        />
      </div>
      <button type="submit" class="login-button">로그인</button>
      <div class="links-container">
        <RouterLink to="/searchId" class="link">아이디 찾기</RouterLink>
        <RouterLink to="/searchPW" class="link">비밀번호 찾기</RouterLink>
        <RouterLink to="/signup" class="link"
          >아직 회원이 아니신가요? 회원가입</RouterLink
        >
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/userStore";

const store = useUserStore();

const id = ref("");
const pw = ref("");

const login = function () {
  store.userLogin(id.value, pw.value);
};
</script>

<style scoped>
/* 전체 로그인 페이지 스타일 */
.login-container {
  display: flex;
  flex-direction: column;
  justify-content: center; /* 수직 중앙 정렬 */
  align-items: center; /* 수평 중앙 정렬 */
}

.login-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #333; /* 제목 색상 */
}

.login-form {
  border: 1px solid #ccc;
  display: flex;
  flex-direction: column; /* 입력 필드와 버튼을 세로로 배치 */
  align-items: center;
  width: 100%;
  max-width: 400px; /* 최대 너비를 400px로 제한 */
  padding: 20px;
  background-color: white; /* 배경색 */
  /* box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);  */
}

.login-button {
  display: block;
  width: 90%;
  background-color: #ccc;
  color: white;
  padding: 14px;
  margin-top: 20px;
  border: none;
  cursor: pointer;
  font-size: 1.1rem;
  transition: background-color 0.3s ease;
}

.login-button:hover {
  background-color: white;
  color: #ccc;
  border: 1px solid #ccc;
}

.login-button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
  opacity: 0.6;
}

/* 링크 영역 스타일 */
.links-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 15px;
}

.link {
  font-size: 14px;
  color: #555555;
  text-decoration: none;
  margin: 5px 0;
}

.link:hover {
  text-decoration: underline;
  color: #0056b3; /* 호버 시 색상 변경 */
}

/* dust-class styling */
.dust-class {
  width: 90%;
  box-sizing: border-box;
  margin: 10px auto;
  position: relative;
}

.dust-class label {
  display: inline-block;
  position: absolute;
  top: -2px;
  left: 14px;
  padding: 7px;
  background: white;
  font-size: 12px;
  color: #888;
  font-weight: bold;
}

.dust-class label span {
  color: #da4841;
  vertical-align: -1px;
}

.dust-class input {
  width: 100%;
  border: 1px solid #ddd !important;
  font-size: 1rem;
  line-height: 1.45;
  letter-spacing: -0.04rem;
  border-radius: 8px;
  padding: 16px;
  margin-top: 12px;
}

/* 반응형 디자인: 모바일 화면에서 입력창을 작게 설정 */
@media (max-width: 480px) {
  .login-title {
    font-size: 20px;
  }

  .login-input {
    padding: 10px;
    font-size: 14px;
  }

  .login-button {
    font-size: 14px;
    padding: 10px;
  }

  .link {
    font-size: 12px;
  }
}
</style>
