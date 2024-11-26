<template>
  <div class="mypage">
    <h4 class="mypage-title">마이페이지</h4>
    <aside class="mypage-aside">
      <img :src="getImageURL(userStore.myPageInfo.img)" class="mypage-img" />
      <p class="mypage-greeting">
        {{ userStore.myPageInfo.name }}님 환영합니다
      </p>
    </aside>
    <div class="mypage-content">
      <table class="mypage-table">
        <tr>
          <td class="mypage-label">아이디</td>
          <td>
            <input
              type="text"
              v-model="userStore.myPageInfo.id"
              class="mypage-input"
              disabled
            />
          </td>
          <td></td>
        </tr>
        <tr>
          <td class="mypage-label">비밀번호</td>
          <td>
            <input
              type="password"
              v-model="userStore.myPageInfo.password"
              class="mypage-input"
              disabled
            />
          </td>
          <td><button class="mypage-button" @click="changePW">수정</button></td>
        </tr>
        <tr>
          <td class="mypage-label">이메일</td>
          <td>
            <input
              type="email"
              v-model="userStore.myPageInfo.email"
              class="mypage-input"
            />
          </td>
          <td>
            <button class="mypage-button" @click="changeEmail">수정</button>
          </td>
        </tr>
        <tr>
          <td class="mypage-label">핸드폰</td>
          <td>
            <input
              type="text"
              v-model="userStore.myPageInfo.phone"
              class="mypage-input"
            />
          </td>
          <td>
            <button class="mypage-button" @click="changePhone">수정</button>
          </td>
        </tr>
        <tr>
          <td class="mypage-label">나이</td>
          <td>
            <input
              type="number"
              v-model="userStore.myPageInfo.age"
              class="mypage-input"
            />
          </td>
          <td>
            <button class="mypage-button" @click="changeAge">수정</button>
          </td>
        </tr>
        <tr>
          <td class="mypage-label">내가 생성한 크루</td>
          <td>
            <div v-if="crewMembersStore.searchLeaderByUserID.length > 0">
              <div class="mypage-crew-list">
                <span
                  v-for="crew in crewMembersStore.searchLeaderByUserID"
                  :key="crew.crewId"
                  class="mypage-crew-link"
                >
                  <RouterLink :to="'/crewboard/' + crew.crewId">{{
                    crew.crewName
                  }}</RouterLink>
                </span>
              </div>
            </div>
            <div v-else>
              <span>아직 생성한 크루가 없습니다</span>
            </div>
          </td>
          <td></td>
        </tr>
        <tr>
          <td class="mypage-label">내가 가입한 크루</td>
          <td>
            <div v-if="crewMembersStore.searchByUserID.length > 0">
              <div class="mypage-crew-list">
                <span
                  v-for="crew in crewMembersStore.searchByUserID"
                  :key="crew.crewId"
                  class="mypage-crew-link"
                >
                  <RouterLink :to="'/crewboard/' + crew.crewId">{{
                    crew.crewName
                  }}</RouterLink>
                </span>
              </div>
            </div>
            <div v-else>
              <span>가입한 크루가 없습니다</span>
            </div>
          </td>
          <td></td>
        </tr>
      </table>
      <button class="mypage-delete-button" @click="deleteUser">
        회원 탈퇴하기
      </button>
    </div>
  </div>
</template>

<script setup>
import { useCrewMembersStore } from "@/stores/crewMembersStore";
import { useUserStore } from "@/stores/userStore";
import axios from "axios";
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";

const router = useRouter();
const route = useRoute();
const userStore = useUserStore();
const crewMembersStore = useCrewMembersStore();
const id = userStore.loginUser.id;

onMounted(async () => {
  await userStore.mypage(id); // 사용자 정보를 먼저 불러옴
  crewMembersStore.getUsersCrewList(id); // 크루 리스트 불러오기
  crewMembersStore.getLeadersCrewList(id); // 리더 크루 리스트 불러오기

  // console.log("Image URL:", userStore.myPageInfo.img); // 이미지 URL을 콘솔로 확인
});

function getImageURL(name) {
  return new URL(`/src/assests/${name}`, import.meta.url).href;
}

const changePW = function () {
  router.push({ name: "userSearchPW" });
};

const changeEmail = function () {
  const newEmail = userStore.myPageInfo.email;
  userStore.changeEmail(id, newEmail);
};

const changePhone = function () {
  const newPhone = userStore.myPageInfo.phone;
  userStore.changePhone(id, newPhone);
};

const changeAge = function () {
  const newAge = userStore.myPageInfo.age;
  userStore.changeAge(id, newAge);
};

const deleteUser = function () {
  if (
    confirm(
      "탈퇴하시면 생성한 크루 및 게시글까지 전부 삭제됩니다.\n정말 탈퇴하시겠습니까?"
    )
  ) {
    userStore.deleteUser(id);
  }
};
</script>

<style scoped>
.mypage {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 1200px;
  margin: 20px auto;
  padding: 20px;
}

.mypage-title {
  font-size: 2rem;
  margin: 70px 0px;
}

.mypage-aside {
  text-align: center;
  margin-bottom: 20px;
}

.mypage-img {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  object-fit: cover;
  margin-bottom: 50px;
}

.mypage-greeting {
  font-size: 1.2rem;
  font-weight: bold;
  margin-bottom: 20px;
}

.mypage-content {
  width: 100%;
  max-width: 800px;
}

.mypage-table {
  width: 100%;
  margin-bottom: 20px;
  border-collapse: collapse;
}

.mypage-label {
  padding: 15px; /* 라벨 간격을 늘림 */
  text-align: left;
  vertical-align: middle;
  font-size: 1rem;
}

.mypage-input {
  width: 100%;
  padding: 12px; /* 입력 필드의 패딩을 넓힘 */
  border: 1px solid #ccc;
  font-size: 1rem;
  margin-top: 8px; /* 위쪽 여백을 약간 늘림 */
  margin-bottom: 12px; /* 아래쪽 여백을 넓힘 */
}

.mypage-button {
  padding: 10px 18px; /* 버튼의 패딩을 늘림 */
  cursor: pointer;
  font-size: 1rem;
  background-color: rgb(119, 119, 115); /* 수정 버튼 색상을 #ccc로 변경 */
  color: white;
  border: none;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.mypage-button:hover {
  background-color: #bbb; /* 호버시 색상 변경 */
  transform: scale(1.05);
}

.mypage-button:disabled {
  background-color: #ddd;
  cursor: not-allowed;
}

.mypage-delete-button {
  padding: 12px 22px; /* 삭제 버튼의 패딩을 늘림 */
  cursor: pointer;
  font-size: 1rem;
  background-color: #f44336;
  color: white;
  border: none;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-top: 20px;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.mypage-delete-button:hover {
  background-color: #e53935;
  transform: scale(1.05);
}

/* 테이블의 border-bottom만 연한 회색으로 변경 */
.mypage-table td {
  border-bottom: 1px solid #f1f1f1; /* 연한 회색으로 border-bottom 설정 */
  padding: 15px; /* 각 셀 간 간격을 늘림 */
}

.mypage-table td:last-child {
  text-align: center;
}

.mypage-table td span {
  display: block;
  margin-bottom: 5px;
}

/* 내가 가입한 크루와 내가 생성한 크루 리스트를 가로로 표시 */
.mypage-crew-list {
  display: flex;
  flex-wrap: wrap; /* 여러 줄로 표시될 수 있게 함 */
  gap: 20px; /* 아이템 간 간격을 넓힘 */
}

.mypage-crew-link {
  display: inline-block;
  margin-bottom: 5px;
  font-size: 1rem;
  color: #007bff;
  text-decoration: none;
}

.mypage-crew-link:hover {
  text-decoration: underline;
}

@media screen and (max-width: 768px) {
  .mypage-content {
    margin: 0 10%;
  }

  .mypage-table td {
    font-size: 0.9rem;
  }
}
</style>
