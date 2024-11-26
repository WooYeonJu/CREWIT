<template>
  <div class="container">
    <h4>게시글 수정</h4>

    <div class="crew-tag">
      <label for="title">제목</label>
      <input type="text" id="title" v-model="crewdetailboard.boardTitle" />
    </div>

    <div class="crew-tag">
      <label for="writer">작성자</label>
      <input
        type="text"
        id="writer"
        v-model="userStore.loginUser.id"
        disabled
      />
    </div>

    <div class="crew-tag">
      <label for="content">내용</label>
      <textarea
        id="content"
        cols="30"
        rows="10"
        class="description"
        v-model="crewdetailboard.content"
      ></textarea>
    </div>

    <div class="crew-tag">
      <button @click="updateBoard">수정</button>
    </div>
  </div>
</template>

<script setup>
import { useCrewDetailStore } from "@/stores/crewDetailStore.js";
import { useUserStore } from "@/stores/userStore";
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";

const store = useCrewDetailStore();
const userStore = useUserStore();

const route = useRoute();
const router = useRouter();

const crewDetail = JSON.parse(route.query.crewDetail);

onMounted(() => {
  console.log(crewDetail);
});

const crewdetailboard = ref({
  crewId: crewDetail.crewId,
  boardId: crewDetail.boardId,
  boardTitle: crewDetail.boardTitle,
  boardWriter: userStore.loginUser.id,
  content: crewDetail.content,
});

const updateBoard = function () {
  const id = route.params.crewId;
  crewdetailboard.value.crewId = id;
  console.log(crewdetailboard.value);
  store.updateBoard(crewdetailboard.value);
};
</script>

<style scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
  margin-top: 20px;
  padding: 20px;
}

h4 {
  text-align: center;
  font-size: 2rem;
  margin: 10px 0;
}

div {
  display: flex;
  flex-direction: column;
  margin: 20px 20%;
}

.crew-tag {
  margin-bottom: 10px;
  margin-top: 10px;
}

label {
  margin: 10px 0px;
  font-size: large;
}

input[type="text"],
textarea {
  border: none;
  border-bottom: solid #aaaaaa 1px;
  font-size: large;
  padding: 5px;
  margin-bottom: 20px;
}

textarea {
  width: 100%;
  resize: none;
}
textarea.description {
  border: solid #aaaaaa 1px;
  font-size: large;
  padding: 5px;
  margin-bottom: 20px;
}
button {
  padding: 10px 20px;
  cursor: pointer;
  font-size: 1rem;
  background-color: white;
  color: rgb(119, 119, 115);
  border: 1px solid rgb(119, 119, 115);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s ease, transform 0.3s ease;
}

button:hover {
  background-color: rgb(119, 119, 115);
  color: white;
  border: none;
  transform: scale(1.1);
}
</style>
