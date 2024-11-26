<template>
  <div class="container">
    <div class="createContainer">
      <h4 class="crew-create-title">크루 만들기</h4>

      <div class="crew-tag">
        <label for="crewName">크루 이름 </label>
        <input
          type="text"
          id="crewName"
          class="input-tag"
          v-model="crewboard.crewName"
          required
        />
      </div>
      <div class="crew-tag">
        <label for="crewLeader">크루 리더 </label>
        <input
          type="text"
          id="crewLeader"
          v-model="userStore.loginUser.id"
          disabled
          required
        />
      </div>
      <!-- <div>
        <label for="crewCreateDate">크루 생성 날짜 : </label>
        <input type="date" id="crewCreateDate" v-model="crewboard.crewCreateDate" disabled>
      </div> -->
      <!-- <div class="crew-tag">
        <label for="crewDueDate">크루 마감 날짜  </label>
        <input type="date" id="crewDueDate" v-model="crewboard.crewDueDate" />
      </div> -->
      <div class="crew-tag">
        <label for="crewMaxMember"
          >모집 인원
          <br />
          (본인 제외)
        </label>
        <button class="numberbutton" @click="minus">-</button>
        <input
          type="number"
          id="crewMaxMember"
          v-model="crewboard.crewMaxMember"
          required
        />
        <button class="numberbutton" @click="plus">+</button>
      </div>

      <div class="crew-tag" id="description">
        <label for="crewContent" class="description">크루 설명 </label>
        <textarea
          id="crewContent"
          cols="50"
          rows="10"
          v-model="crewboard.crewContent"
        ></textarea>
      </div>

      <div class="crew-tag">
        <label>운동 종목 </label>

        <select v-model="crewboard.crewExerciseCode">
          <option value="" selected hidden="">선택하라고</option>
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
        <label for="crewLocation">운동 장소 </label>
        <input
          type="text"
          id="crewLocation"
          class="input-tag"
          v-model="crewboard.crewLocation"
        />
      </div>
      <div class="crew-tag">
        <label for="crewExerciseDate">운동 날짜 </label>
        <!-- <input
      type="datetime-local"
      id="crewExerciseDate"
      v-model="crewboard.crewExerciseDate"
      /> -->
        <select v-model="crewboard.crewExerciseDate">
          <option value="" selected hidden="">선택</option>
          <option value="월요일">월요일</option>
          <option value="화요일">화요일</option>
          <option value="수요일">수요일</option>
          <option value="목요일">목요일</option>
          <option value="금요일">금요일</option>
          <option value="토요일">토요일</option>
          <option value="일요일">일요일</option>
        </select>
      </div>
      <div class="crew-tag" id="crewcreatesubmit">
        <button
          @click="createBoard"
          class="crewcreatesubmit"
          :disabled="!isFormValid"
        >
          등록
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useCrewboardStore } from "@/stores/crewboard";
import { useUserStore } from "@/stores/userStore";
import { ref, onMounted, computed } from "vue";

const store = useCrewboardStore();
const userStore = useUserStore();

onMounted(() => {
  store.getExerciseList();
  console.log(store.exerciseList);
});

const crewboard = ref({
  crewName: "",
  crewLeader: userStore.loginUser.id,
  crewMaxMember: 0,
  crewContent: "",
  crewExerciseCode: "",
  crewLocation: "",
  crewExerciseDate: "",
});

// 모든 필드가 기입되었는지 검증하는 computed 속성
const isFormValid = computed(() => {
  return (
    crewboard.value.crewName &&
    crewboard.value.crewMaxMember > 0 &&
    crewboard.value.crewContent &&
    crewboard.value.crewExerciseCode &&
    crewboard.value.crewLocation &&
    crewboard.value.crewExerciseDate
  );
});

const createBoard = function () {
  if (isFormValid.value) {
    store.createCrewBoard(crewboard.value);
  } else {
    alert("모든 필드를 기입해주세요.");
  }
};

const minus = function () {
  if (crewboard.value.crewMaxMember > 0) {
    crewboard.value.crewMaxMember -= 1;
  }
};

const plus = function () {
  if (crewboard.value.crewMaxMember < 100) {
    crewboard.value.crewMaxMember += 1;
  }
};
</script>

<style scoped>
h4.crew-create-title {
  text-align: center;
  font-size: 2rem;
  margin: 0 0 50px 0;
  /* position:absolute */
}

.container {
  background-color: white;
  max-width: 1200px;
  margin: 20px auto;
  display: flex;
  justify-content: center;
}

.createContainer {
  /* border: solid 1px; */
  /* border-radius: 15%; */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 70%;
  padding: 0;
}

.crew-tag {
  margin: 20px 20%;
  font-size: large;
  display: flex;
  align-items: center;
}

label {
  margin: 0px 10px;
}
/* 크루 이름, 운동장소 input tag */
.input-tag {
  border: none;
  border-bottom: solid #aaaaaa 1px;
}
/* input[type="date"]::webkit-calendar-picker-indicator{
}
input[type="date"]::webkit-clear-button{
  
} */

#description {
  display: flex;
  align-items: top;
}

select {
  width: 50%;
  border: none;
  border-bottom: solid #aaaaaa 1px;
}

input[type="number"] {
  -webkit-appearance: textfield;
  -moz-appearance: textfield;
  appearance: textfield;
}

input[type="number"]::-webkit-inner-spin-button,
input[type="number"]::-webkit-outer-spin-button {
  -webkit-appearance: none;
}

input[type="number"] {
  width: 30px;
  height: 30px;
  border: none;
  text-align: center;
}

.numberbutton {
  width: 30px;
  height: 30px;
  border-radius: 40px;
  border: none;
}

#crewcreatesubmit {
  display: flex;
  justify-content: flex-end;
}

.crewcreatesubmit {
  /* position: fixed;

  bottom: 27%;
  right: 27%; */

  padding: 10px 20px;
  /* border-radius: 50px; */
  cursor: pointer;
  font-size: 0.8rem;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s ease, transform 0.3s ease;
  z-index: 1000; /* 버튼이 다른 요소 위에 표시되도록 설정 */
  background-color: white; /* 버튼 호버 시 색상 변화 */
  color: rgb(119, 119, 115);
  border: 1px solid rgb(119, 119, 115);
}

.crewcreatesubmit:hover {
  background-color: rgb(119, 119, 115); /* 초록색 버튼 */
  color: white;
  border: none;
  transform: scale(1.1); /* 버튼 크기 확대 */
}
</style>
