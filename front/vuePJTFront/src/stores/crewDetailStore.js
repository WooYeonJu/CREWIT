import { defineStore } from "pinia";
import { ref, computed } from "vue";
import router from "@/router";
import axios from "axios";

const REST_BOARD_API = `http://localhost:8080/crewDetailapi`;

export const useCrewDetailStore = defineStore("crewDetail", () => {
  const crewDetailBoards = ref([]);
  const crewDetailBoard = ref([]);
  const crewDetailDetail = ref({});

  const searchedCrewDetail = ref([]);

  const boardCnt = computed(() => {
    return crewDetailBoard.value.length;
  });

  const serachBoardCnt = computed(() => {
    return searchedCrewDetail.value.length;
  });

  const getBoards = () => {
    axios
      .get(REST_BOARD_API + "/crewDetail", {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((response) => {
        crewDetailBoards.value = response.data;
      })
      .catch((err) => {
        console.log(err);
        alert("로그인 후 이용해주세요");
        router.push({ name: "login" });
      });
  };

  const getBoardByCrewId = (id) => {
    axios
      .get(`${REST_BOARD_API}/crewDetail/crewId/${id}`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((response) => {
        console.log(response.data);
        crewDetailBoard.value = response.data;
        console.log(crewDetailBoard.value);
        boardCnt.value = response.data.length;
      })
      .catch(() => {
        alert("로그인 후 이용해주세요");
        router.push({ name: "login" });
      });
  };

  const getBoardByBoardId = (id) => {
    console.log(id);
    axios
      .get(`${REST_BOARD_API}/crewDetail/boardId/${id}`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((response) => {
        console.log(response.data);
        crewDetailDetail.value = response.data;
        console.log(crewDetailDetail.value);
      })
      .catch(() => {
        alert("로그인 후 이용해주세요");
        router.push({ name: "login" });
      });
  };

  const searchBoard = function (title, crewId) {
    axios
      .get(`${REST_BOARD_API}/crewDetail/search`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
        params: {
          title: title,
          crewId: crewId,
        },
      })
      .then((response) => {
        console.log(response.data);
        searchedCrewDetail.value = response.data;
        console.log("searchedVideo", searchedCrewDetail.value);
        router.push({
          name: "crewDetailBoardSearchList",
          query: { title: title }, // 검색어를 쿼리 파라미터로 전달
        });
      })
      .catch((err) => {
        console.log(err);
        alert("로그인 후 이용해주세요");
        router.push({ name: "login" });
      });
  };

  const createCrewDetailBoard = function (crewdetailboard) {
    console.log(crewdetailboard);
    axios({
      url: `${REST_BOARD_API}/crewDetail`,
      method: "POST",
      data: crewdetailboard,
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
    })
      .then(() => {
        router.push({ name: "crewDetailBoardList" });
      })
      .catch((err) => {
        console.log(err);
        alert("로그인 후 이용해주세요");
        router.push({ name: "login" });
      });
  };

  const updateBoard = function (crewdetailboard) {
    console.log(crewdetailboard);
    axios
      .put(`${REST_BOARD_API}/crewDetail/update`, crewdetailboard, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then(() => {
        alert("정상적으로 수정되었습니다.");
        router.push({ name: "crewDetailBoardList" });
      })
      .catch((err) => {
        console.log(err);
        alert("로그인 후 이용해주세요");
        router.push({ name: "login" });
      });
  };

  const deleteBoard = function (boardId, crewId) {
    axios
      .delete(`${REST_BOARD_API}/crewDetail/delete/${boardId}`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then(() => {
        alert("게시물이 삭제되었습니다.");
        // location.reload(); // 페이지 새로고침
        router.push({
          name: "crewDetailBoardList",
          params: crewId,
        });
      })
      .catch((err) => {
        console.log(err);
        alert("로그인 후 이용해주세요");
        router.push({ name: "login" });
      });
  };

  return {
    crewDetailBoards,
    crewDetailBoard,
    searchedCrewDetail,
    boardCnt,
    serachBoardCnt,
    getBoards,
    getBoardByCrewId,
    searchBoard,
    crewDetailDetail,
    getBoardByBoardId,
    createCrewDetailBoard,
    updateBoard,
    deleteBoard,
  };
});
