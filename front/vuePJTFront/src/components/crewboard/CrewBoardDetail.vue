<template>
  <div>
    <div class="board-detail-container">
      <img src="../../assests/headerImg2.jpg" alt="" />
    </div>
    <!-- <h4 class="board-detail-title">크루 상세정보</h4> -->
    <div class="board-info">
      <div class="board-detail-title-div">
        <h4 class="board-detail-title">{{ store.crewboard.crewName }}</h4>
        <div class="board-detail-creatdate-div">
          <p class="board-detail-item-title-createtime">등록일</p>
          <p class="board-detail-item-createtime">
            {{ store.crewboard.crewCreateDate }}
          </p>
        </div>
      </div>

      <div class="board-detail-section">
        <!-- <p class="board-detail-item-title">크루 이름</p> -->

        <div class="board-detail-item-container">
          <p class="board-detail-item-title">리더</p>
          <p class="board-detail-item">{{ store.crewboard.crewLeader }}</p>
        </div>

        <div class="board-detail-item-container">
          <p class="board-detail-item-title">모집 인원</p>
          <p class="board-detail-item">
            {{ crewMembersStore.searchByCrewId.length }} /
            {{ store.crewboard.crewMaxMember }}
          </p>
        </div>

        <div class="board-detail-item-container">
          <p class="board-detail-item-title">상세 설명</p>
          <p class="board-detail-item">{{ store.crewboard.crewContent }}</p>
        </div>

        <div class="board-detail-item-container">
          <p class="board-detail-item-title">운동 종목</p>
          <p class="board-detail-item">
            {{ store.crewboard.crewExerciseName }}
          </p>
        </div>

        <div class="board-detail-item-container">
          <p class="board-detail-item-title">운동 장소</p>
          <p class="board-detail-item">{{ store.crewboard.crewLocation }}</p>
        </div>

        <div class="board-detail-item-container">
          <p class="board-detail-item-title">운동 날짜</p>
          <p class="board-detail-item">
            {{ store.crewboard.crewExerciseDate }}
          </p>
        </div>
      </div>
    </div>
    <!-- 리더일 경우 '삭제'와 '수정' 버튼을 표시 -->
    <div
      v-if="
        userStore.loginUser &&
        userStore.loginUser.id === store.crewboard.crewLeader
      "
    >
      <div class="board-detail-leader-buttons">
        <button class="board-detail-button delete-button" @click="deleteBoard">
          삭제
        </button>
        <button class="board-detail-button update-button" @click="updateBoard">
          수정
        </button>
      </div>
      <div>
        <RouterView />
      </div>
    </div>

    <!-- 크루에 가입한 멤버일 경우 '탈퇴' 버튼을 표시 -->
    <div v-else>
      <div
        v-if="
          crewMembersStore.searchByCrewId &&
          Array.isArray(crewMembersStore.searchByCrewId) &&
          crewMembersStore.searchByCrewId.some(
            (crewMember) => crewMember.crewMember === userStore.loginUser.id
          )
        "
      >
        <div>
          <RouterView />
        </div>
        <div class="board-detail-actions">
          <button class="board-detail-button exit-button" @click="exitcrew">
            크루 탈퇴
          </button>
        </div>
      </div>

      <!-- 크루에 가입하지 않은 경우 '참가' 버튼을 표시 -->
      <div v-else class="board-detail-actions">
        <button
          class="board-detail-button join-button"
          @click="joincrew"
          v-if="
            store.crewboard.crewCurrMember !== store.crewboard.crewMaxMember
          "
        >
          크루 가입
        </button>
        <button
          class="board-detail-button join-button"
          @click="joincrew"
          disabled
          v-else
        >
          크루 모짐 마감
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useCrewboardStore } from "@/stores/crewboard";
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";
import { useCrewMembersStore } from "@/stores/crewMembersStore";
import { useUserStore } from "@/stores/userStore";
import CrewDetailBoardList from "../crewdetail/crewDetailBoardList.vue";
import CrewDetailBoardSearchList from "../crewdetail/crewDetailBoardSearchList.vue";

const store = useCrewboardStore();
const crewMembersStore = useCrewMembersStore();
const userStore = useUserStore();

const route = useRoute();
const router = useRouter();

onMounted(() => {
  if (!userStore.loginUser) {
    router.push({ name: "login" });
  }

  store.getCrewBoard(route.params.crewId);
  crewMembersStore.getCrewMember(route.params.crewId);
});

const deleteBoard = function () {
  axios
    .delete(`http://localhost:8080/api-crew/crew/${route.params.crewId}`, {
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
    })
    .then(() => {
      alert("크루가 정상적으로 삭제되었습니다.");
      router.push({ name: "crewboardList" });
    })
    .catch(() => {
      alert("오류가 발생했습니다.");
    });
};

const updateBoard = function () {
  router.push({ name: "crewboardUpdate" });
};

const joincrew = function () {
  if (crewMembersStore.searchByCrewId.length >= store.crewboard.crewMaxMember) {
    alert("더 이상 참가할 수 없습니다. 최대 인원에 도달했습니다.");
    return;
  }
  crewMembersStore.insertCrewMember(
    store.crewboard,
    route.params.crewId,
    store.crewboard.crewName,
    store.crewboard.crewLeader,
    userStore.loginUser.id
  );
};

const exitcrew = function () {
  crewMembersStore.deleteCrewMember(
    route.params.crewId,
    userStore.loginUser.id
  );
};
</script>

<style scoped>
/* 전체 컨테이너 스타일 */
.board-detail-container {
  width: 100%;
  position: relative;
  height: 200px;
}

.board-detail-container img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.board-detail-title-div {
  max-width: 1200px;
  margin: 40px auto;
  display: flex;
  justify-content: space-between;
  border-bottom: 0.5px solid;
  /* margin-top:20px; */
}

.board-detail-item-container {
  display: flex;
  justify-content: center;
}
.board-detail-creatdate-div {
  display: flex;
  vertical-align: bottom;
}

.board-detail-container::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.35); /* 검은색 배경에 투명도 30% */
  z-index: 1; /* 이미지보다 위에 표시되도록 설정 */
}

/* 제목 스타일 */
.board-detail-title {
  font-size: 1.6rem;
  font-weight: bold;
  color: #333;
  margin: 0 0 15px 15px;
}

/* 항목별 텍스트 스타일 */
.board-detail-item {
  font-size: 1rem;
  color: #555;
  /* margin: 5px 0; */
  margin: 20px;
  font-size: large;
  width: 400px;
  text-align: start;
}

.board-detail-item-createtime {
  font-size: 1rem;
  color: #555;
  margin: 10px 5px 0 5px;
  padding: 10% 5px 0 5px;
}

.board-detail-item-title-createtime {
  font-size: 1rem;
  color: #555;
  margin: 5px 0;
  padding: 10% 5px 0 5px;
}

/* 항목별 텍스트 스타일 */
.board-detail-item-createtime {
  font-size: 1rem;
  color: #555;
  margin: 5px 0;
}

/* 항목 제목 스타일 */
.board-detail-item-title {
  /* border-right: 1px solid; */
  width: 100px;
  font-weight: bold;
  color: #333;
  margin: 20px 0px;
  font-size: large;
  text-align: end;
}

button.disabled {
  pointer-events: none; /* 클릭 이벤트 차단 */
  /* opacity: 0.5;  흐릿하게 표시 */
  background-color: #c5b3b3; /* 배경 색상 변경 */
  cursor: not-allowed; /* 마우스 커서 변경 */
  transform: none; /* hover 확대 효과 제거 */
  box-shadow: none; /* hover 시 그림자 효과 제거 */
}

/* 정보 항목 구분을 위한 간격 */
.board-detail-section {
  /* width: 40%; */
  /* margin: 0 auto; */
  margin: 20px 30%;
  font-size: large;
  margin-bottom: 20px;
  background-color: #ccc;
  /* padding : 0 13% 0 13% */
  background-color: #f9f9f9;
  /* border-radius: 8px; */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

/* '삭제', '수정', '크루 탈퇴', '크루 참가' 버튼 스타일 */
.board-detail-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  margin: 10px 5px;
  transition: background-color 0.3s;
}

.board-detail-button:hover {
  background-color: #0056b3;
}

/* '삭제' 버튼 스타일 */
.delete-button {
  background-color: #dc3545;
}

.delete-button:hover {
  background-color: #c82333;
}

/* '수정' 버튼 스타일 */
.update-button {
  background-color: #ffc107;
}

.update-button:hover {
  background-color: #e0a800;
}

/* '크루 탈퇴' 버튼 스타일 */
.exit-button {
  background-color: #dc3545;
}

.exit-button:hover {
  background-color: #c82333;
}

/* '크루 참가' 버튼 스타일 */
.join-button {
  background-color: rgb(119, 119, 115);
}

.join-button:hover {
  background-color: rgb(119, 119, 115);
}

/* 버튼 그룹 구분 */
.board-detail-actions {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: flex-end;
  /* margin : 20px 100px 20px 0; */
}

/* 리더에게만 보이는 삭제/수정 버튼 구분 */
.board-detail-leader-buttons {
  display: flex;
  justify-content: flex-end;
  max-width: 1200px;
  margin: 0 auto;
  gap: 10px;
}

/* 반응형 디자인 */
@media (max-width: 768px) {
  .board-detail-container {
    padding: 10px;
  }

  .board-detail-item {
    font-size: 0.9rem;
  }

  .board-detail-button {
    width: 100%;
    margin: 10px 0;
  }
}
</style>
