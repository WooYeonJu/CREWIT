<template>
  <div>
    <div class="search-container">
      <form @submit.prevent="search" class="search-form">
        <!-- form submit 시 페이지 새로고침 방지 -->
        <input
          type="text"
          v-model="title"
          placeholder="검색어를 입력하세요"
          class="search-input"
        />
        <button type="submit" class="search-button">검색</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { useCrewDetailStore } from "@/stores/crewDetailStore";
import { ref } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();

const store = useCrewDetailStore();

const title = ref("");

// 검색 함수
const search = function () {
  console.log("search");
  console.log(title.value);

  const crewId = route.params.crewId;
  store.searchBoard(title.value, crewId);
};
</script>

<style scoped>
.container {
  max-width: 1200px;
  display: flex;
  flex-direction: column; /* 세로로 배치 */
  justify-content: center;
  margin: 0 auto;
  /* align-items: center; */
}

/* 검색창을 화면의 중앙에 배치 */
.search-container {
  display: flex;
  justify-content: center; /* 가로 중앙 정렬 */
  align-items: center; /* 세로 중앙 정렬 */
  width: 100%;
  padding: 20px;
  z-index: 10; /* 검색창이 가장 위에 위치하도록 설정 */
}

.search-form {
  display: flex;
  justify-content: center; /* 검색창과 버튼을 수평으로 중앙 정렬 */
  align-items: center; /* 검색창과 버튼을 세로로 중앙 정렬 */
  max-width: 600px; /* 최대 너비 */
  width: 100%; /* 부모 컨테이너에 맞추어 크기 확장 */
}

.search-input {
  flex: 1; /* 남는 공간을 채우도록 */
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  /* border-radius: 5px; */
  margin-right: 10px; /* 버튼과의 간격 */
  outline: none;
  transition: border-color 0.3s ease;
}

.search-input:focus {
  border-color: rgb(119, 119, 115); /* 입력 필드 포커스 시 테두리 색상 변경 */
}

.search-button {
  padding: 10px 20px;
  color: white;
  font-size: 16px;
  border: none;
  /* border-radius: 5px; */
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.search-button:hover {
  background-color: rgb(119, 119, 115); /* 버튼 호버 시 색상 변경 */
}

.search-button:focus {
  outline: none; /* 버튼에 포커스가 있을 때 테두리 제거 */
}

/* RouterView가 검색창 아래에 위치하도록 설정 */
.content {
  flex: 1; /* 남은 공간을 차지하도록 */
  padding: 20px;
  overflow-y: auto; /* 내용이 많을 경우 스크롤 가능 */
}

/* 반응형 디자인 */
@media (max-width: 768px) {
  .search-input {
    font-size: 14px;
    padding: 8px;
  }

  .search-button {
    font-size: 14px;
    padding: 8px 15px;
  }
}
</style>
