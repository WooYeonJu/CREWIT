<template>
  <div class="comment-create-container">
    <fieldset class="comment-form">
      <div class="comment-inputandsubmit">
        <input
          type="text"
          id="content"
          v-model="content"
          class="comment-input-field"
          placeholder="댓글 내용을 작성하세요"
        />
        <button @click="createComment" class="submit-button">작성</button>
      </div>
    </fieldset>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { useCommentStore } from "@/stores/commentStore";
import { useUserStore } from "@/stores/userStore";
import { ref } from "vue";

const store = useUserStore();
const commentStore = useCommentStore();
const route = useRoute();

const boardId = ref("");
const writer = ref("");
const content = ref("");

const createComment = () => {
  let comment = {
    crewId: route.params.crewId,
    boardId: route.params.boardId,
    writer: store.loginUser.id,
    content: content.value,
  };

  commentStore.createComment(comment);
};
</script>

<style>
/* 전체 컨테이너 스타일 */
.comment-create-container {
  width: 100%;
  margin: 0px auto; /* 화면 중앙 정렬 */
  padding: 0px;
  background-color: #fff;
  border-radius: 10px;
  margin-bottom: 30px;
}

/* 제목 스타일 */
.comment-title {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 20px;
  color: #333;
}

/* 필드셋 스타일 */
.comment-form {
  border: 1px solid #fff;
  border-radius: 8px;
  padding: 0px;
  background-color: #fff;
}

/* 레이블 스타일 */
.label {
  font-size: 1.1rem;
  font-weight: bold;
  margin-bottom: 10px;
  display: block;
  color: #555;
}

.comment-inputandsubmit {
  display: flex;
}

/* 입력 필드 스타일 */
.comment-input-field {
  width: 100%;
  padding: 0px;
  margin-bottom: 10px;
  border: 1px solid #fff;
  border-bottom: 1px solid #ccc;
  border-radius: 0px;
  font-size: 1rem;
  color: #333;
  /* background-color: #f9f9f9; */
  transition: border-color 0.3s ease;
}

.submit-button {
  display: inline-block;
  padding: 10px;
  font-size: 1rem;
  background-color: #505050;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin: 10px;
  margin-left: 20px;
  margin-right: 0px;
  min-width: 100px;
}

.submit-button:hover {
  background-color: #45a049;
}
</style>
