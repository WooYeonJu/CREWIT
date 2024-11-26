<template>
  <div class="container">
    <h2>사용자 등록</h2>
    <fieldset class="form-fieldset">
      <div class="dust-class">
        <label for="id"><span> * </span>ID</label>
        <input
          type="text"
          id="id"
          v-model="id"
          class="form-input"
          placeholder="아이디를 입력해주세요"
          required
        />
      </div>

      <div class="dust-class">
        <label for="password"><span> * </span>PW</label>
        <input
          type="password"
          id="password"
          v-model="password"
          class="form-input"
          placeholder="비밀번호를 입력해주세요"
          required
        />
      </div>

      <div class="dust-class">
        <label for="name"><span> * </span>이름</label>
        <input
          type="text"
          id="name"
          v-model="name"
          class="form-input"
          placeholder="이름을 입력해주세요"
          required
        />
      </div>

      <div class="dust-class">
        <label for="email"><span> * </span>이메일</label>
        <input
          type="email"
          id="email"
          v-model="email"
          class="form-input"
          placeholder="이메일을 입력해주세요"
          required
        />
      </div>

      <div class="dust-class">
        <label for="phone"><span> * </span>핸드폰 번호</label>
        <input
          type="text"
          id="phone"
          v-model="phone"
          class="form-input"
          placeholder="핸드폰 번호를 입력해주세요"
          required
        />
      </div>

      <div class="dust-class">
        <label for="age"><span> * </span>나이</label>
        <input
          type="number"
          id="age"
          v-model="age"
          class="form-input"
          placeholder="나이를 입력해주세요"
          required
        />
      </div>

      <div class="dust-class">
        <label for="profileImg">프로필 이미지</label>
        <input
          type="file"
          id="profileImg"
          @change="uploadImg($event.target.files)"
          class="form-input"
          accept="image/*"
        />
      </div>

      <!-- 프로필 이미지 미리보기 -->
      <img
        id="preview"
        v-if="profileImgURL"
        :src="profileImgURL"
        alt="프로필 미리보기"
        class="profile-preview"
      />
      <br />

      <button class="btn" :disabled="isFormInvalid" @click="userSignup">
        등록
      </button>
    </fieldset>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useUserStore } from "@/stores/userStore";

const userStore = useUserStore();

const id = ref("");
const password = ref("");
const name = ref("");
const email = ref("");
const phone = ref("");
const age = ref("");
const profileImg = ref(null); // 프로필 이미지 파일 객체
const profileImgURL = ref(""); // 프로필 이미지 미리보기 URL

// 파일 업로드 처리 및 미리보기 URL 생성
const uploadImg = (files) => {
  if (files && files.length > 0) {
    profileImg.value = files[0]; // 선택된 파일
    profileImgURL.value = URL.createObjectURL(files[0]); // 미리보기 URL 생성
  }
};

// 모든 입력이 올바르게 입력되었는지 확인하는 computed 속성
const isFormInvalid = computed(() => {
  return (
    !id.value ||
    !password.value ||
    !name.value ||
    !email.value ||
    !phone.value ||
    !age.value
  );
});

// 사용자 등록 처리
const userSignup = async () => {
  const formData = new FormData();
  formData.append("id", id.value);
  formData.append("password", password.value);
  formData.append("name", name.value);
  formData.append("email", email.value);
  formData.append("phone", phone.value);
  formData.append("age", age.value);

  // 프로필 이미지가 있으면 파일을 FormData에 추가
  if (profileImg.value) {
    formData.append("profileImg", profileImg.value); // 파일 자체를 전송
  }

  try {
    await userStore.userSignup(formData); // 사용자 등록 API 호출
  } catch (error) {
    console.error("회원가입 실패:", error);
  }
};
</script>

<style scoped>
/* 기본적인 레이아웃 설정 */
.container {
  max-width: 600px;
  margin: 40px auto;
  padding: 20px;
  background-color: white;
  border-radius: 8px;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
  font-size: 1.8rem;
  color: #333;
}

.form-fieldset {
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 8px;
}

.form-input:focus {
  outline: none;
  border-color: #4caf50;
}

.form-input[type="file"] {
  padding: 16px;
  font-size: 1rem;
  border: 1px solid #ddd;
  border-radius: 8px;
  width: 100%;
  margin-top: 12px;
}

.profile-preview {
  margin-top: 10px;
  border-radius: 8px;
  border: 1px solid #ddd;
  width: 100%;
  height: auto;
  max-height: 200px;
}

.btn {
  display: block;
  width: 100%;
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

.btn:hover {
  background-color: white;
  color: #ccc;
  border: 1px solid #ccc;
}

.btn:disabled {
  background-color: #ccc;
  cursor: not-allowed;
  opacity: 0.6;
}

.dust-class {
  width: 70%;
  box-sizing: border-box;
  margin: 20px auto;
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

@media (max-width: 768px) {
  .container {
    padding: 15px;
  }

  .form-input {
    font-size: 0.9rem;
  }

  .btn {
    font-size: 1rem;
    padding: 12px;
  }

  h2 {
    font-size: 1.6rem;
  }
}
</style>
