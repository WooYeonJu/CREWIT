<template>
  <div class="crew-detail-container">
    <!-- <h1 class="crew-detail-title">게시글 상세 정보</h1> -->
    <fieldset class="crew-detail-content">
      <!-- <label for="title" class="detail-label">제목</label> -->
      <div class="detail-value-title">
        {{ crewDetailStore.crewDetailDetail.boardTitle }}
      </div>

      <div class="detail-value-container">
        <div class="detail-value-writer">
          <label for="channel" class="detail-label">작성자 :</label>
          {{ crewDetailStore.crewDetailDetail.boardWriter }}
        </div>

        <div
          v-if="
            crewDetailStore.crewDetailDetail.boardWriter ===
            userStore.loginUser.id
          "
        >
          <button @click="updateBoard" class="update-btn">수정</button>
          <button
            @click="
              deleteBoard(
                crewDetailStore.crewDetailDetail.boardId,
                crewDetailStore.crewDetailDetail.crewId
              )
            "
            class="delete-btn"
          >
            삭제
          </button>
        </div>
      </div>
      <hr />

      <!-- <label for="url" class="detail-label">내용</label> -->
      <div class="detail-value-content">
        <label for="channel" class="detail-label">내용</label>

        {{ crewDetailStore.crewDetailDetail.content }}
      </div>
    </fieldset>
    <div class="comment-section">
      <CommentList />
    </div>
    <div class="backToList">
      <button class="backToListButton" @click="backToList">게시물 목록</button>
    </div>
  </div>
</template>

<script setup>
import CommentList from "../comment/CommentList.vue";
import CommentCreate from "../comment/CommentCreate.vue";

import { useCrewDetailStore } from "@/stores/crewDetailStore.js";
import { onMounted, ref } from "vue";

import { useRoute, useRouter } from "vue-router";
import axios from "axios";
import { useUserStore } from "@/stores/userStore";

const userStore = useUserStore();
const crewDetailStore = useCrewDetailStore();
const router = useRouter();
const route = useRoute();

onMounted(() => {
  const id = route.params.boardId;
  crewDetailStore.getBoardByBoardId(id);
  // console.log(crewDetailStore.crewDetailDetail);
});

const updateBoard = function () {
  // crewDetailStore.updateBoard();
  router.push({
    name: "crewDetailBoardUpdate",
    query: {
      crewDetail: JSON.stringify(crewDetailStore.crewDetailDetail),
    },
  });
};

const deleteBoard = function (boardId, crewId) {
  crewDetailStore.deleteBoard(boardId, crewId);
};

const backToList = function () {
  router.push({ name: "crewDetailBoardList" });
};
</script>

<style>
/* 전체 컨테이너 스타일 */
.crew-detail-container {
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
  /* padding: 40px; */
  background-color: #fff;
  margin-top: 70px;
}

/* 제목 스타일 */
.crew-detail-title {
  text-align: center;
  font-size: 2.5rem;
  color: #333;
  margin-bottom: 20px;
}

/* 필드셋 스타일 */
.crew-detail-content {
  border: 1px solid #fff;
  border-radius: 8px;
  /* padding: 20px; */
  background-color: #fff;
  /* box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); */
}

.detail-value-container {
  display: flex;
  justify-content: space-between;
}

.detail-value-content {
  margin-top: 20px;
  margin-bottom: 50px;
}

.delete-btn,
.update-btn {
  background-color: #ffffff;
  color: #ccc;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.3s ease;
  margin-right: 10px;
}

/* 레이블 스타일 */
.detail-label {
  font-size: 1.1rem;
  font-weight: bold;
  margin-bottom: 10px;
  margin-right: 10px;
  display: block;
  color: #555;
}

/* 값 스타일 */
.detail-value {
  font-size: 1.1rem;
  margin-bottom: 20px;
  color: #333;
}

.detail-value-title {
  justify-content: center;
  font-size: 2rem;
  margin: 10ox auto;
  margin-bottom: 20px;
}

.detail-value-writer {
  display: flex;
}

/* 삭제 버튼 스타일 */
.delete-button {
  display: inline-block;
  padding: 10px 20px;
  font-size: 1rem;
  background-color: #ff4d4d;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.delete-button:hover {
  background-color: #ff1a1a;
}

/* 댓글 섹션 스타일 */
.comment-section {
  margin-top: 40px;
}

.backToList {
  display: flex;
  justify-content: center;
  margin: 70px;
}

.backToListButton {
  width: 120px;
  height: 50px;
  font-size: 1rem;
  padding: 10px 5px;
  background-color: rgb(119, 119, 115);
  border: none;
  color: white;
  cursor: pointer;
}
</style>
