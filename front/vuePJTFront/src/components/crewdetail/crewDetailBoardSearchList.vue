<template>
  <div class="board-container">
    <h2 class="board-title">검색된 게시글 목록</h2>
    <div class="board-info">
      <h4 class="board-count">
        "{{ $route.query.title }}"로 검색된 게시글 수 :
        {{ crewDetailStore.serachBoardCnt }}
      </h4>
      <button @click="addBoard" class="add-board-btn">게시물 등록</button>
    </div>
    <hr />
    <CrewDetailBoardSearchInput />
    <div v-if="crewDetailStore.serachBoardCnt">
      <table class="board-table">
        <thead>
          <tr>
            <th class="board-title-column">제목</th>
            <th class="board-writer-column">작성자</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="board in crewDetailStore.searchedCrewDetail"
            :key="board.boardId"
          >
            <td class="board-title-column">
              <RouterLink
                :to="`/crewboard/${board.crewId}/${board.boardId}`"
                class="board-link"
                >{{ board.boardTitle }}</RouterLink
              >
            </td>
            <td class="board-writer-column">{{ board.boardWriter }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-else class="no-boards">검색된 게시물이 없습니다.</div>
  </div>
</template>

<script setup>
import { useCrewDetailStore } from "@/stores/crewDetailStore.js";
import { onMounted } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

import CrewDetailBoardSearchInput from "./CrewDetailBoardSearchInput.vue";

const crewDetailStore = useCrewDetailStore();

const addBoard = function () {
  router.push({
    name: "crewDetailBoardCreate",
    params: crewDetailStore.searchedCrewDetail.crewId,
  });
};
</script>

<style scoped>
.add-board-btn {
  padding: 10px 20px;
  background-color: #ccc;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s;
  margin-bottom: 10px;
}

.add-board-btn:hover {
  background-color: #ffffff;
  border: 1px solid #ccc;
  color: #ccc;
}

.board-container {
  font-family: Arial, sans-serif;
  border-radius: 8px;
  max-width: 1000px;
  padding: 20px;
  margin: 0 auto;
  margin-top: 70px;
}

.board-info {
  display: flex;
  align-content: center;
  justify-content: space-between;
}

.board-title {
  font-size: 24px;
  color: #333;
  margin: 0 auto;
  margin-bottom: 10px;
  justify-content: center;
  display: flex;
}

.board-count {
  font-size: 18px;
  color: #666;
  margin-bottom: 20px;
  margin-top: 5px;
}

.board-table {
  width: 80%;
  border-collapse: collapse;
  margin: 10px auto;
  margin-top: 20px;
}

.board-table thead {
  background-color: #dadada;
}

.board-table th,
.board-table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.board-title-column {
  width: 70%; /* 제목란을 넓게 */
  border-right: 1px solid white;
}

.board-writer-column {
  width: 30%;
}

.board-link {
  text-decoration: none;
  color: #333;
  font-weight: bold;
}

.board-link:hover {
  color: #0099ff;
}

.no-boards {
  font-size: 16px;
  color: #888;
  margin-top: 20px;
}
</style>
