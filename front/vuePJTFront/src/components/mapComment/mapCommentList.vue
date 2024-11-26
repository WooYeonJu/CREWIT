<template>
  <div class="container">
    <h2 class="title">댓글: {{ commentStore.commentCnt }}</h2>

    <MapCommentCreate />

    <div v-if="commentStore.commentCnt">
      <div
        v-for="comment in commentStore.comments"
        :key="comment.id"
        class="comment-item"
      >
        <p class="comment-writer">{{ comment.writer }}</p>

        <!-- 별점 표시 및 점수 -->
        <div class="rating-wrapper" v-if="!comment.doupdate">
          <div class="star-rating" >
            <input
              v-for="n in 5"
              :key="n"
              type="radio"
              class="star"
              :name="`rating-${comment.id}`"
              :value="n"
              v-model="comment.rate"
              :checked="comment.rate <= 6 - n"
              disabled
            />
          </div>
          <span class="rating-score">{{ comment.rate }}/5</span>
        </div>

        <div >
          <div class="comment-content" v-if="!comment.doupdate">
          <p>{{ comment.content }}</p>
        </div>
          <!-- 수정/삭제 버튼들 -->
          <div v-if="userStore.loginUser != null &&  userStore.loginUser.id === comment.writer">
          <div
            class="comment-btn"
            v-if="
              userStore.loginUser.id === comment.writer && !comment.doupdate
            "
          >
            <button @click="commentRemove(comment.id)" class="delete-btn">
              리뷰 삭제
            </button>
            <button @click="commentUpdate(comment)" class="delete-btn">
              리뷰 수정
            </button>
          </div>

          <div v-else>
            <div class="update-comment">
            <div class="star-rating">
              <input
                v-for="n in 5"
                :key="n"
                type="radio"
                class="star"
                :name="`rating-${comment.id}`"
                :value="6 - n"
                v-model="comment.rate"
              />
            </div>
            <input class="update-content" type="text" v-model="comment.content" />
          </div>
            <button class="update-button" @click="doUpdate(comment)">수정하기</button>
          </div>
        </div>
      </div>
    </div>
    </div>

    <div v-else class="no-comments">등록된 리뷰가 없습니다.</div>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { useMapCommentStore } from "@/stores/mapCommentStore";
import { onMounted, ref } from "vue";

import axios from "axios";
import { useUserStore } from "@/stores/userStore";
import MapCommentCreate from "./mapCommentCreate.vue";

const router = useRouter();
const route = useRoute();
const commentStore = useMapCommentStore();
const userStore = useUserStore();

onMounted(() => {
  const placeId = route.params.exercisePlaceId;
  commentStore.getCommentsByBoardId(placeId);
});

const commentUpdate = function (comment) {
  comment.doupdate = !comment.doupdate;
};

const doUpdate = function (comment) {
  comment.placeId = route.params.exercisePlaceId;
  commentStore.commentUpdate(comment);
  alert("댓글이 수정되었습니다.");
  location.reload();
  comment.doupdate = !comment.doupdate;
};

const commentRemove = function (id) {
  const url = `http://localhost:8080/mapcommentapi`;

  axios
    .delete(`${url}/${id}`, {
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
.star-rating {
  display: flex;
  direction: rtl; /* 별을 오른쪽에서 왼쪽으로 표시 */
  justify-content: left;
}

.star {
  appearance: none;
  padding: 0px 1px;
  margin: 0;
  cursor: pointer;
}

.star::after {
  content: "☆";
  color: #d1d1d1; /* 기본 색상 */
  font-size: 20px;
  transition: color 0.3s ease;
}

.star:checked::after,
.star:checked ~ .star::after {
  content: "★"; /* 선택된 별 */
  color: gold; /* 선택된 별 색상 */
}

.container {
  width: 100%;
  margin: 70px 0px;
  padding: 40px;
  background-color: #fff;
  border-radius: 10px;
  margin-top: 100px;
}

.title {
  text-align: left;
  font-size: 1.2rem;
  font-weight: bold;
  color: #333;
  margin: 20px 0px;
}

.comment-count {
  text-align: center;
  font-size: 1.2rem;
  color: #666;
  margin-bottom: 20px;
}

.rating-wrapper {
  display: flex;
  justify-content: left;
  gap: 10px;
  position: relative;
  align-items: center;
  align-content: center;
}

.rating-score {
  font-size: 1rem;
  color: #333;
  margin-bottom: 9px;
}
.comment-content {
  margin-bottom:10px;
  display: flex;
  justify-content: space-between;
}

.comment-writer {
  margin-top: 50px;
  color: #333;
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

.update-content{
  width:100%;
  padding:5px;
  border: none;
  border-bottom: 1px solid #ddd;
}

.update-button{
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

.comment-btn {
  /* width: 100%; */
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

.update-comment{
  width:100%;
  justify-content: start;
}
</style>
