<template>
  <div>
    <CrewBoardSearchInput class="search-input" />
    <div class="container">
      <h4 class="page-title">크루 목록</h4>
      <p class="page-titletitle">원하시는 크루를 선택하세요.</p>

      <div class="boardlist-container">
        <div
          v-for="crewboard in sortedCrewboardList"
          :key="crewboard.id"
          class="boardlist"
        >
          <div v-if="crewboard.crewCurrMember !== crewboard.crewMaxMember">
            <div class="recruit">모집중</div>
          </div>
          <div v-else>
            <div class="recruitend">마감</div>
          </div>

          <div class="crew-container">
            <h3>
              <RouterLink
                :to="`/crewboard/${crewboard.crewId}`"
                class="crew-name"
              >
                {{ crewboard.crewName }}
              </RouterLink>
            </h3>
            <p class="member-count">
              {{ crewboard.crewCurrMember }} / {{ crewboard.crewMaxMember }}
            </p>
          </div>
          <div>
            <p class="crew-content">
              종목 |
              {{ store.exerciseList[crewboard.crewExerciseCode - 1]?.exercise }}
            </p>
          </div>
          <div>
            <p class="crew-content">장소 | {{ crewboard.crewLocation }}</p>
          </div>
          <div class="crew-content">
            <p class="introduce">소개</p>
            <p class="crew-content-introduce">{{ crewboard.crewContent }}</p>
          </div>
        </div>
      </div>
      <div class="create-button-container">
        <button @click="createBoard" class="create-button">
          크루 생성하기
        </button>
      </div>
    </div>

    <!-- 버튼 위치를 오른쪽 하단으로 고정 -->
  </div>
</template>

<script setup>
import { useCrewboardStore } from "@/stores/crewboard";
import { onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import CrewBoardSearchInput from "./CrewBoardSearchInput.vue";

const router = useRouter();
const store = useCrewboardStore();

onMounted(() => {
  store.getCrewBoardList();
  store.getExerciseList();
});

// 모집 중인 항목이 위로 오도록 정렬된 리스트 생성
const sortedCrewboardList = computed(() => {
  return store.crewboardList.slice().sort((a, b) => {
    // 마감 여부를 기준으로 정렬 (모집중 우선)
    const aStatus = a.crewCurrMember === a.crewMaxMember ? 1 : 0;
    const bStatus = b.crewCurrMember === b.crewMaxMember ? 1 : 0;
    return aStatus - bStatus; // 모집중(0)이 마감(1)보다 위로 오도록 설정
  });
});

const createBoard = function () {
  router.push({ name: "crewBoardCreate" });
};
</script>

<style scoped>
/* 전체 컨테이너 스타일 */
.container {
  background-color: white;
  width: 90%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 30px;
  position: relative;
  /* height: 81vh; */
}

.recruit {
  text-align: start;
  width: 70px;
  display: flex;
  justify-content: center;
  background-color: #777;
  color: white;
  margin: 5px 0 20px 0;
}
.recruitend {
  /* text-align: ; */
  width: 70px;
  display: flex;
  justify-content: center;
  background-color: rgb(167, 18, 18);
  color: white;
  margin: 5px 0 20px 0;
}
/* 페이지 제목 스타일 */
.page-title {
  font-size: 1.5rem;
  font-weight: 700;
  color: rgb(102, 102, 102);
  margin-bottom: 20px;
  text-align: center;
}

.page-titletitle {
  font-size: 1rem;
  font-weight: 700;
  color: #333;
  margin-bottom: 20px;
  text-align: center;
}

/* 게시글 목록 컨테이너 (가로 배치) */
.boardlist-container {
  display: flex;
  flex-wrap: wrap; /* 내용이 넘칠 경우 다음 줄로 넘어가도록 설정 */
  /* justify-content: space-between; */
  padding: 0 0 0 10%;
  gap: 50px; /* 항목 간 간격 */
}

/* 게시글 항목 스타일 */
.boardlist {
  width: 30%; /* 각 항목의 너비를 30%로 설정 */
  border: 1px solid #e0e0e0;
  padding: 20px;
  /* border-radius: 10px; */
  background-color: #ffffff;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  text-align: center;
  position: relative;
}

.boardlist:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 10px rgb(119, 119, 115);
}

/* 게시글 제목(크루 이름) 스타일 */
.crew-name {
  font-size: 1.3rem;
  font-weight: 600;
  color: rgb(102, 102, 102);
  text-decoration: none;
  transition: color 0.3s ease;
}

.crew-name:hover {
  color: #003366;
}

.crew-container {
  display: flex;
  justify-content: space-between;
  margin: 5px 5px 20px 0px;
}
.crew-content {
  font-size: 1rem;
  color: #777;
  margin: 10px 0;
  text-align: start;
}
.crew-content-introduce {
  font-size: 1rem;
  color: #777;
  margin: 10px 0;
  text-align: start;

  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.introduce {
  width: 35px;
  /* border-bottom: 1.5px solid #777; */
}
/* 크루 현재 멤버 수 및 최대 멤버 수 스타일 */
.member-count {
  font-size: 1.1rem;
  font-weight: bold;
  color: rgb(85, 85, 85);
}

.create-button-container {
  max-width: 1200px;
  justify-content: flex-end;
  margin: 0 auto;
  padding: 20px 0;
  display: flex;
}
/* '등록' 버튼 스타일 */
.create-button {
  /* position: fixed;

  bottom: 27%;
  right: 27%; */

  padding: 15px 30px;
  /* border-radius: 50px; */
  cursor: pointer;
  font-size: 1rem;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s ease, transform 0.3s ease;
  z-index: 1000; /* 버튼이 다른 요소 위에 표시되도록 설정 */
  background-color: white; /* 버튼 호버 시 색상 변화 */
  color: rgb(119, 119, 115);
  border: 1px solid rgb(119, 119, 115);
}

.create-button:hover {
  background-color: rgb(119, 119, 115); /* 초록색 버튼 */
  color: white;
  border: none;
  transform: scale(1.1); /* 버튼 크기 확대 */
}

/* 검색 입력 부분 스타일 */
.search-input {
  margin-top: 30px;
}

/* 반응형 스타일 */
@media (max-width: 768px) {
  .container {
    padding: 20px;
  }

  .boardlist {
    width: 100%; /* 작은 화면에서는 100% 너비로 설정 */
  }

  .create-button {
    width: 100%;
    bottom: 20px; /* 모바일에서도 버튼을 아래에 고정 */
  }
}
</style>
