<template>
  <div class="search-container">
    <div class="parent-search-bar">
      <!-- 검색 기준 -->
      <div class="search-bar1">
        <!-- <div class="search-item"> -->
        <select
          v-model="searchInfo.key"
          class="select-criteria"
          @click="initcriteria"
        >
          <option value="none">검색 기준</option>
          <option value="crew_name">크루 이름</option>
          <option value="crew_exercise_code">운동 종목</option>
          <option value="crew_location">운동 장소</option>
          <option value="crew_exercise_date">운동 날짜</option>
        </select>
        <!-- </div> -->

        <!-- 검색 내용 -->
        <!-- <div class="search-item">
          <div v-if="searchInfo.key === 'crew_exercise_code'"> -->
        <select
          v-model="searchInfo.word"
          class="input-box"
          v-if="searchInfo.key === 'crew_exercise_code'"
        >
          <option value="" selected hidden="">선택하셈</option>
          <option
            class="input-option"
            v-for="options in store.exerciseList"
            :key="options.id"
            :value="options.id"
          >
            {{ options.exercise }}
          </option>
        </select>
        <!-- </div> -->

        <div v-else class="search-input-container">
          <input
            type="text"
            v-model="searchInfo.word"
            class="input-box2"
            placeholder="검색어를 입력하세요"
            @keyup.enter="searchCrewBoardList"
          />
        </div>
        <button @click="searchCrewBoardList" class="search-button">
          <!-- <i class="bi bi-search"></i> -->
          <span class="search-icon"
            ><img
              src="../../assests/search.svg"
              alt=""
              style="
                width: 30px;
                justify-content: center;
                align-items: center;
                margin-top: 7px;
              "
          /></span>
          <!-- <span class="search-icon">&#128269;</span> -->
          <!-- 돋보기 아이콘 -->
        </button>
        <!-- </div> -->
      </div>
      <div class="search-bar">
        <!-- 정렬 기준 -->
        <div class="search-item">
          <select
            v-model="searchInfo.orderBy"
            class="select-box"
            @click="searchCrewBoardList"
          >
            <option value="none">정렬 기준</option>
            <option value="crew_name">이름순</option>
            <option value="crew_location">지역순</option>
          </select>
        </div>

        <!-- 정렬 방향 -->
        <div class="search-item">
          <select
            v-model="searchInfo.orderByDir"
            class="select-box"
            @click="searchCrewBoardList"
          >
            <option value="asc">가나다순</option>
            <option value="desc">역순</option>
          </select>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useCrewboardStore } from "@/stores/crewboard";
import { onMounted, ref } from "vue";

const searchInfo = ref({
  key: "none",
  word: "",
  orderBy: "none",
  orderByDir: "asc",
});

const store = useCrewboardStore();

const searchCrewBoardList = function () {
  store.searchCrewBoardList(searchInfo.value);
};

onMounted(() => {
  store.getExerciseList();
});

const initcriteria = function () {
  // console.log(searchInfo.value.word)
  searchInfo.value.word = "";
};
</script>

<style scoped>
/* 검색 및 정렬 컨테이너 */

.parent-search-bar {
  display: flex;
  width: 100%;
  max-width: 1130px;
  margin: 0 auto;
  justify-content: flex-end;
}
/* 검색바 컨테이너 */
.search-bar1 {
  display: flex;
  max-width: 500px;
  border-radius: 8px;
  border: 1px solid;
  width: 40%;
  position: relative;
  margin-right: 100px;
  border: 1px solid #757575;
}

/* select 스타일 */
.select-criteria {
  border-radius: 8px 0px 0px 8px;
  width: auto;
  padding: 12px 16px;
  font-size: 1rem;
  border: none;
}
/* select 스타일 */
.select-box {
  width: 100%;
  padding: 12px 16px;
  font-size: 1rem;
  border-radius: 8px;
  background-color: #f9f9f9;
  transition: border 0.3s ease, box-shadow 0.3s ease;
  box-sizing: border-box;
  border: 1px solid #ccc;
}

/* select 필드에 포커스 시 스타일 */
.select-box:focus {
  /* border-color: #007bff;
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
  outline: none; */
}

/* 검색창 스타일 */
.search-input-container {
  position: relative;
  width: 150%;
}

.input-box {
  width: 100%;
  padding: 12px 16px;
  font-size: 1rem;
  border: none;
  appearance: none;
}
.input-option {
  width: 100%;
}
.input-box2 {
  border-radius: 0px 8px 8px 0px;
  width: 100%;
  padding: 12px 16px;
  font-size: 1rem;
  border: none;
}

/* 돋보기 아이콘 스타일 */
.search-button {
  position: absolute;
  right: 10px;
  top: 50%;
  transform: translateY(-50%);
  background-color: transparent;
  border: none;
  border-radius: 50%;
  color: #007bff;
  font-size: 1.5rem;
  cursor: pointer;
  transition: transform 0.3s ease, color 0.3s ease;
}

.search-button:hover {
  color: #0056b3;
  transform: translateY(-50%) scale(1.2);
}

.search-bar {
  display: flex;
  /* position: absolute; */
  /* right: 50px; */
  /* width: 90%; */
  /* max-width: 1200px; */
  /* margin: 0 auto; */
  /* padding: 30px; */
  justify-content: flex-end;
  /* margin-left: auto; */
}
/* 작은 화면에서 검색바 레이아웃 개선 */
@media (max-width: 768px) {
  .search-bar {
    flex-direction: column;
  }

  /* .search-item {
    flex: 1 1 100%;
  } */

  .input-box {
    padding-right: 30px; /* 아이콘을 위한 여백 조정 */
  }

  .search-button {
    font-size: 1.2rem;
  }
}
</style>
