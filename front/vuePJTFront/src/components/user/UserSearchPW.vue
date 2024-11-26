<template>
  <div class="change-pw-container">
    <h4 class="change-pw-title">비밀번호 변경</h4>
    <form @submit.prevent="changePW" class="change-pw-form">

      <div class="dust-class" v-if="store.loginUser == null">

        <label for="id"><span> * </span>아이디</label>
      <input
        type="text"
        v-model.trim="id"
        required
        class="input-field"
        id="id"
      />
    </div>
    <div v-else>
      <div class="dust-class">
      <label for="currPw"><span> * </span>현재 비밀번호</label>
      <input
        type="password"
        v-model.trim="currPw"
        required
        class="input-field"
        id="currPw"
      />
    </div>
    </div>
    <div class="dust-class">
      <label for="pw"><span> * </span>비밀번호</label>
      <input
        type="password"
        v-model.trim="pw"
        required
        class="input-field"
        id="pw"
      />
</div>
<div class="dust-class">
  <label for="pw2"><span> * </span>비밀번호 확인</label>
      <input
        type="password"
        v-model.trim="pw2"
        required
        class="input-field"
        id="pw2"
      />
</div>
      <button type="submit" class="submit-btn">변경하기</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/userStore";

const store = useUserStore();

const id = ref("");
const pw = ref("");
const pw2 = ref("");
const currPw = ref("");

const changePW = function () {

  if(store.loginUser == null){
    store.checkId(id.value, pw.value, pw2.value);
  }
  else {
    const id = store.loginUser.id;

    store.checkPW(id, currPw.value, pw.value, pw2.value);
  }
    

};
</script>

<style scoped>
/* 전체 비밀번호 변경 페이지 스타일 */.change-pw-container {
  display: flex;
  flex-direction: column;
  justify-content: center; /* 수직 중앙 정렬 */
  align-items: center; /* 수평 중앙 정렬 */
}

.change-pw-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #333; /* 제목 색상 */
}

.change-pw-form {
  border: 1px solid #ccc;
  display: flex;
  flex-direction: column; /* 입력 필드와 버튼을 세로로 배치 */
  align-items: center;
  width: 100%;
  max-width: 400px; /* 최대 너비를 400px로 제한 */
  padding: 20px;
  background-color: white; /* 배경색 */
  border-radius: 8px; /* 둥근 모서리 */
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
  border-radius: 4px;
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
