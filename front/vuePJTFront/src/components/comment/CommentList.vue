<template>
  <div class="container">
    <h2 class="title">댓글: {{ commentStore.commentCnt }}</h2>
    <CommentCreate />

    <div v-if="commentStore.commentCnt">
      <div
        v-for="comment in commentStore.comments"
        :key="comment.id"
        class="comment-item"
      >
        <p class="comment-writer">{{ comment.writer }}</p>

        <div class="comment-content" v-if="!comment.doupdate">
          <p>{{ comment.content }}</p>
        </div>

        <!-- 수정/삭제 버튼들 -->
        <div
          v-if="
            userStore.loginUser != null &&
            userStore.loginUser.id === comment.writer
          "
        >
          <div
            class="comment-btn"
            v-if="
              userStore.loginUser.id === comment.writer && !comment.doupdate
            "
          >
            <button @click="commentUpdate(comment)" class="delete-btn">
              댓글 수정
            </button>
            <button @click="commentRemove(comment.id)" class="delete-btn">
              댓글 삭제
            </button>
          </div>

          <div v-else>
            <input
              class="update-content"
              type="text"
              v-model="comment.content"
            />
            <button class="update-button" @click="doUpdate(comment)">
              수정하기
            </button>
          </div>
        </div>
      </div>
    </div>

    <div v-else class="no-comments">등록된 댓글이 없습니다.</div>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { useCommentStore } from "@/stores/commentStore";
import { onMounted, ref } from "vue";

import axios from "axios";
import { useUserStore } from "@/stores/userStore";
import CommentCreate from "./CommentCreate.vue";

const router = useRouter();
const route = useRoute();
const commentStore = useCommentStore();
const userStore = useUserStore();

let doupdate = ref(false);

onMounted(() => {
  const boardId = route.params.boardId;
  commentStore.getCommentsByBoardId(boardId);
});

const commentUpdate = function (comment) {
  comment.doupdate = !comment.doupdate;
  // location.reload();
  // commentStore.updateComment(commentid);
};

const doUpdate = function (comment) {
  console.log(comment);
  comment.crewId = route.params.crewId;
  comment.boardId = route.params.boardId;
  commentStore.commentUpdate(comment);
  alert("댓글이 수정되었습니다.");
  comment.doupdate = !comment.doupdate;
};

const commentRemove = function (id) {
  const url = `http://localhost:8080/commentapi`;

  axios
    .delete(`${url}/comment/${id}`, {
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
    })
    .then(() => {
      alert("댓글이 삭제되었습니다.");
      location.reload(); // 페이지 새로고침
    })
    .catch((err) => {
      console.log(err);
    });
};
</script>

<style scoped>
/* 전체 컨테이너 스타일 */
.container {
  width: 100%;
  /* padding: 40px; */
  background-color: #fff;
  border-radius: 10px;
  margin: 20px 0px;
  /* margin-top: 100px; */
}

/* 제목 스타일 */
.title {
  text-align: left;
  font-size: 1.2rem;
  font-weight: bold;
  color: #333;
  margin: 20px 0px;
}

/* 리뷰 수 표시 스타일 */
.comment-count {
  text-align: center;
  font-size: 1.2rem;
  margin-bottom: 20px;
  color: #666;
}

.comment-content {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.comment-writer {
  margin-top: 50px;
  color: #333;
  margin-bottom: 20px;
}

.comment-item {
  background-color: #fff;
  padding: 0px;
  padding-bottom: 20px;
  margin-bottom: 30px;
  border-radius: 0px;
  justify-items: bottom;
  border-bottom: 1px solid #ccc;
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
.delete-btn:hover,
.update-btn:hover {
  background-color: #686868;
}

.comment-btn {
  justify-items: end;
}

.comment-item p {
  margin: 0;
}

.no-comments {
  text-align: left;
  font-size: 1rem;
  color: #888;
  margin: 30px 0px;
  margin-bottom: 50px;
}

.update-content {
  width: 100%;
  padding: 5px;
  border: none;
  border-bottom: 1px solid #ddd;
}

.update-button {
  background-color: #ccc;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.3s ease;
  margin-right: 10px;
  padding: 5px 7px;
  margin-top: 10px;
}
</style>
