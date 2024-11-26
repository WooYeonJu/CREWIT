<template>
  <div class="login-container">
    <h4 class="login-title">아이디 찾기</h4>
    <form @submit.prevent="searchId" class="login-form">
      <div class="dust-class">
        <label for="id"><span> * </span>이름</label>
        <input
          type="text"
          placeholder="이름"
          v-model="name"
          required
          class="input-field"
        />
      </div>
      <div class="dust-class">
        <label for="id"><span> * </span>이메일</label>

        <input
          type="email"
          placeholder="이메일"
          v-model.trim="email"
          required
          class="input-field"
        />
      </div>
      <button type="submit" class="submit-btn">아이디 찾기</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/userStore";

const store = useUserStore();

const name = ref("");
const email = ref("");

const searchId = function () {
  store.usersearchId(name.value, email.value);
};
</script>

<style scoped>
/* 전체 로그인 컨테이너 스타일 */
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

.submit-btn {
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

.submit-btn:hover {
  background-color: white;
  color: #ccc;
  border: 1px solid #ccc;
}

.submit-btn:disabled {
  background-color: #ccc;
  cursor: not-allowed;
  opacity: 0.6;
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
