<template>
  <div class="board-container">
    <div class="board-title-div">
      <h2 class="board-title">크루 게시글 목록</h2>
    </div>
    <div class="board-info">
      <h4 class="board-count">
        등록된 게시글 수 : {{ crewDetailStore.boardCnt }}
      </h4>
      <button @click="addBoard" class="add-board-btn">게시물 등록</button>
    </div>
    <hr />
    <CrewDetailBoardSearchInput />
    <div v-if="crewDetailStore.boardCnt">
      <table class="board-table">
        <thead>
          <tr>
            <th class="board-title-column">제목</th>
            <th class="board-writer-column">작성자</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="board in crewDetailStore.crewDetailBoard"
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
    <div v-else class="no-boards">등록된 게시물이 없습니다.</div>
  </div>
</template>

<script setup>
import { useCrewDetailStore } from "@/stores/crewDetailStore.js";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import CrewDetailBoardSearchInput from "./CrewDetailBoardSearchInput.vue";

const route = useRoute();
const router = useRouter();

const crewId = route.params.crewId;

const crewDetailStore = useCrewDetailStore();

onMounted(() => {
  console.log(crewId);
  crewDetailStore.getBoardByCrewId(crewId);
});

const addBoard = function () {
  router.push({ name: "crewDetailBoardCreate", params: crewId });
};
</script>

<style scoped>
.board-container {
  font-family: Arial, sans-serif;
  border-radius: 8px;
  max-width: 1000px;
  padding: 20px;
  margin: 0 auto;
}

.board-info {
  display: flex;
  align-content: center;
  justify-content: space-between;
}

.board-title {
  font-size: 24px;
  color: #333;
  margin: 0;
  margin-bottom: 10px;
  text-align: center; /* 텍스트를 가로 중앙 정렬 */
}

.board-title-div {
  display: flex;
  justify-content: center; /* 가로 중앙 정렬 */
  align-items: center; /* 세로 중앙 정렬 */
  height: 30px; /* 적당한 높이 설정 */
  width: 100%; /* 전체 너비를 차지하게 설정 */
  margin-top: 50px;
}

.board-count {
  font-size: 18px;
  color: #666;
  margin-bottom: 20px;
  margin-top: 5px;
}

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
