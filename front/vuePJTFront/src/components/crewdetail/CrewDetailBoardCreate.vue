<template>
  <div>
    <h4>게시글 작성</h4>

    <div>
      <label for="title">제목</label>
      <input type="text" id="title" v-model="crewdetailboard.boardTitle" />
    </div>
    <div>
      <label for="writer">작성자</label>
      <input
        type="text"
        id="writer"
        v-model="userStore.loginUser.id"
        disabled
      />
    </div>

    <div>
      <label for="content">내용</label>
      <textarea
        id="content"
        cols="30"
        rows="10"
        class="description"
        v-model="crewdetailboard.content"
      ></textarea>
    </div>

    <div>
      <button @click="createBoard">등록</button>
    </div>
  </div>
</template>

<script setup>
import { useCrewDetailStore } from "@/stores/crewDetailStore.js";
import { useUserStore } from "@/stores/userStore";
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";

const store = useCrewDetailStore();
const userStore = useUserStore();

const route = useRoute();

const crewdetailboard = ref({
  crewId: "",
  boardTitle: "",
  boardWriter: userStore.loginUser.id,
  content: "",
});

const createBoard = function () {
  const id = route.params.crewId;
  crewdetailboard.value.crewId = id;
  console.log(crewdetailboard.value);
  store.createCrewDetailBoard(crewdetailboard.value);
  alert("게시글 등록이 완료되었습니다.");
};
</script>

<style scoped>
h4 {
  text-align: center;
  font-size: 2rem;
  margin: 0 0 50px 0;
}

div {
  display: flex;
  flex-direction: column;
  margin: 20px 20%;
}

label {
  margin: 5px 0px;
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
