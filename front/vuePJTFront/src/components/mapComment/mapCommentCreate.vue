<template>
  <div class="comment-create-container">
    <fieldset class="comment-form">
      <!-- <label for="rate">별점</label> -->
      <div class="star-rating">
        <input
          v-for="n in 5"
          :key="n"
          type="radio"
          class="star"
          :value="n"
          v-model="rate"
        />
      </div>
      <!-- <label for="content" class="label">댓글</label> -->
      <div class="comment-inputandsubmit">
        <input
          type="text"
          id="content"
          v-model="content"
          class="comment-input-field"
          placeholder="리뷰 내용을 작성하세요"
        />
        <button @click="createComment" class="submit-button">등록</button>
      </div>
    </fieldset>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { useMapCommentStore } from "@/stores/mapCommentStore";
import { useUserStore } from "@/stores/userStore";
import { ref } from "vue";

const store = useUserStore();
const commentStore = useMapCommentStore();
const route = useRoute();

const boardId = ref("");
const writer = ref("");
const content = ref("");
const rate = ref(6);

const createComment = () => {
  if (store.loginUser !== null) {
    let comment = {
      placeId: route.params.exercisePlaceId,
      writer: store.loginUser.id,
      content: content.value,
      rate: 6 - rate.value,
    };

    console.log(comment.rate);
    commentStore.createComment(comment);
  } else {
    alert("로그인 후 이용해주세요");
    location.reload();
  }
};
</script>

<style>
/* 전체 리뷰 작성 페이지 컨테이너 스타일 */
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
  text-align: left;
  font-size: 2rem;
  font-weight: bold;
  color: #333;
  margin-bottom: 20px;
}

/* 별점 컨테이너 */
.star-rating {
  display: flex;
  justify-content: left;
  direction: rtl; /* 별을 오른쪽에서 왼쪽으로 표시하여 직관성 제공 */
  margin-bottom: 15px;
  /* margin-left: 3px; */
}

/* 개별 별 스타일 */
.star {
  appearance: none;
  margin: 0 4px; /* 별 사이에 간격 추가 */
  cursor: pointer;
  font-size: 20px; /* 별 크기 */
  color: #d1d1d1; /* 기본 색상: 회색 */
  transition: color 0.3s ease;
}

/* 클릭된 별은 금색으로 변경 */
.star:checked::after {
  content: "★"; /* 선택된 별은 ★로 표시 */
  color: gold;
}

/* 선택된 별 이전까지의 별도 금색으로 설정 */
.star:checked ~ .star::after {
  content: "★";
  color: gold;
}

/* 기본 별을 회색으로 설정 */
.star::after {
  content: "☆"; /* 기본 상태에서는 ☆ */
}

/* 리뷰 폼 컨테이너 스타일 */
.comment-form {
  border: 1px solid #fff;
  padding: 0px;
  background-color: #fff;
}

/* 레이블 스타일 */
.label {
  font-size: 1.1rem;
  font-weight: bold;
  margin-bottom: 10px;
  color: #555;
  display: block;
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

/* 작성 버튼 스타일 */
.submit-button {
  display: inline-block;
  padding: 10px;
  font-size: 1rem;
  color: white;
  border: none;
  cursor: pointer;
  margin: 10px;
  margin-left: 20px;
  max-width: 100px;
  background-color: rgb(119, 119, 115);
  border-radius: 0px;
}

.submit-button:hover {
  background-color: #292929; /* 버튼에 마우스를 올리면 색상 변경 */
}

.submit-button:focus {
  outline: none;
  box-shadow: 0 0 5px rgba(76, 175, 80, 0.3); /* 포커스 시 그림자 효과 */
}
</style>
