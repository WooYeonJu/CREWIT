<template>
  <div class="container">
    <h4>수정</h4>
    <fieldset>
      <div class="crew-tag">
        <label for="crewName">크루 이름</label>
        <input type="text" id="crewName" v-model="store.crewboard.crewName" />
      </div>
      <div class="crew-tag">
        <label for="crewLeader">크루 리더</label>
        <input
          type="text"
          id="crewLeader"
          v-model="store.crewboard.crewLeader"
          value=""
          disabled
        />
      </div>

      <div class="crew-tag">
        <label for="crewMaxMember">모집 인원</label>
        <input
          type="number"
          id="crewMaxMember"
          v-model="store.crewboard.crewMaxMember"
        />
      </div>

      <div class="crew-tag">
        <label for="crewContent">크루 설명</label>
        <textarea
          id="crewContent"
          cols="30"
          rows="10"
          class="description"
          v-model="store.crewboard.crewContent"
        ></textarea>
      </div>

      <div class="crew-tag">
        <label>운동 종목</label>
        <select v-model="store.crewboard.crewExerciseCode">
          <option
            v-for="options in store.exerciseList"
            :key="options.id"
            :value="options.id"
          >
            {{ options.exercise }}
          </option>
        </select>
      </div>

      <div class="crew-tag">
        <label for="crewLocation">운동 장소</label>
        <input
          type="text"
          id="crewLocation"
          v-model="store.crewboard.crewLocation"
        />
      </div>

      <div class="crew-tag">
        <label for="crewExerciseDate">운동 날짜 : </label>
        <select v-model="store.crewboard.crewExerciseDate">
          <option value="" selected hidden>선택</option>
          <option value="월요일">월요일</option>
          <option value="화요일">화요일</option>
          <option value="수요일">수요일</option>
          <option value="목요일">목요일</option>
          <option value="금요일">금요일</option>
          <option value="토요일">토요일</option>
          <option value="일요일">일요일</option>
        </select>
      </div>
    </fieldset>
    <div class="crew-tag">
      <button @click="updatecrewBoard">수정</button>
    </div>
  </div>
</template>

<script setup>
import { useCrewboardStore } from "@/stores/crewboard";
import { onMounted } from "vue";

const store = useCrewboardStore();

const updatecrewBoard = function () {
  store.updateCrewBoard();
};

onMounted(() => {
  store.getExerciseList();
});
</script>

<style scoped>
.container {
  max-width: 800px;
  margin: 50px auto;
  margin-top: 20px;
  padding: 20px;
}

h4 {
  text-align: center;
  font-size: 2rem;
  margin: 50px 0;
}

div {
  display: flex;
  flex-direction: column;
  margin: 20px 20%;
}

.crew-tag {
  margin-bottom: 20px;
  margin-top: 20px;
}

label {
  margin: 10px 0px;
  font-size: large;
}

input[type="text"],
input[type="number"],
select,
textarea {
  border: none;
  border-bottom: solid #aaaaaa 1px;
  font-size: large;
  padding: 5px;
  margin-bottom: 20px;
}

textarea.description {
  border: solid #aaaaaa 1px;
  font-size: large;
  padding: 5px;
  margin-bottom: 20px;
}

textarea {
  width: 100%;
  resize: none;
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
