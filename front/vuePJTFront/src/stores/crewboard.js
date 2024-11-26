import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

const REST_API_URL = `http://localhost:8080/api-crew/crew`;

export const useCrewboardStore = defineStore("crewboard", () => {
  const crewboardList = ref([]);

  const getCrewBoardList = function () {
    axios
      .get(`${REST_API_URL}/list`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((response) => {
        console.log(response.data);
        crewboardList.value = response.data;
      })
      .catch((err) => {
        console.log(err);
        alert("로그인 후 이용해주세요");
        router.push({ name: "login" });
      });
  };

  const crewboard = ref([]);

  const getCrewBoard = function (id) {
    axios
      .get(`${REST_API_URL}/${id}`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((response) => {
        crewboard.value = response.data;
        console.log(crewboard);
      })
      .catch(() => {
        alert("로그인 후 이용해주세요");
        router.push({ name: "login" });
      });
  };

  const createCrewBoard = function (crewboard) {
    console.log(crewboard);

    axios({
      url: REST_API_URL,
      method: "POST",
      data: crewboard,
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
    })
      .then(() => {
        console.log("완료");
        alert("크루가 정상적으로 생성되었습니다.");
        router.push({ name: "crewboardList" });
      })
      .catch(() => {
        console.log("실패");
        alert("로그인 후 이용해주세요");
        router.push({ name: "login" });
      });
  };

  const updateCrewBoard = function () {
    // console.log("store: ", crewboard.value.crewMembers);
    axios
      .put(REST_API_URL, crewboard.value, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then(() => {
        alert("크루 정보가 정상적으로 수정되었습니다.");
        router.push({ name: "crewboardList" });
      })
      .catch((error) => {
        console.error("Error updating crew board:", error);
        alert("로그인 후 이용해주세요");
        router.push({ name: "login" });
      });
  };

  const searchCrewBoardList = function (searchCondition) {
    console.log(searchCondition);
    axios
      .get(`${REST_API_URL}/list`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
        params: searchCondition,
      })
      .then((res) => {
        console.log(res.data);
        crewboardList.value = res.data;
      })
      .catch(() => {
        alert("로그인 후 이용해주세요");
        router.push({ name: "login" });
      });
  };

  const exerciseList = ref([]);

  const getExerciseList = function () {
    const url = `http://localhost:8080/exerciseapi/exercise`;

    axios
      .get(url, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((res) => {
        exerciseList.value = res.data;
        // console.log(exerciseList.value);
      })
      .catch((err) => {
        console.log(err);
        alert("로그인 후 이용해주세요");
        router.push({ name: "login" });
      });
  };

  return {
    crewboardList,
    getCrewBoardList,
    crewboard,
    getCrewBoard,
    createCrewBoard,
    updateCrewBoard,
    searchCrewBoardList,
    getExerciseList,
    exerciseList,
  };
});
