import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";
///import { useCrewboardStore } from "./crewboard";

const REST_API_URL = `http://localhost:8080/crewMembers/members`;
//const boardStore = useCrewboardStore();

export const useCrewMembersStore = defineStore("crewMembers", () => {
  const crewMemberList = ref([]);

  // 크루에 가입한 전체 유저 조회
  const getMemberList = function () {
    axios
      .get(REST_API_URL, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((response) => {
        console.log(response.data);
        crewMemberList.value = response.data;
      });
  };

  const searchByCrewId = ref([]);

  const getCrewMember = function (crewId) {
    axios.get(`${REST_API_URL}/searchByCrew/${crewId}`, {        headers: {
      "access-token": sessionStorage.getItem("access-token"),
    }}).then((response) => {
      searchByCrewId.value = response.data;
      console.log(searchByCrewId.value);
    });
  };

  const searchByUserID = ref([]);
  const getUsersCrewList = function (userID) {
    // console.log("store:", userID);
    axios
      .get(`${REST_API_URL}/searchByUser/${userID}`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        }
      })
      .then((response) => {
        // console.log(response.data);
        searchByUserID.value = response.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };
  
  const searchLeaderByUserID = ref([]);
  const getLeadersCrewList = function (userID) {
    // console.log("store:", userID);
    axios
      .get(`${REST_API_URL}/searchByUserLeader/${userID}`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((response) => {
        console.log(response.data);
        searchLeaderByUserID.value = response.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const insertCrewMember = function (
    board,
    crewId,
    crewName,
    crewLeader,
    crewMember
  ) {
    // console.log(searchByCrewId.length);
    // console.log(board.crewMaxMember);
    // if (searchByCrewId.length >= board.crewMaxMember.value) {
    //   alert("더 이상 참가할 수 없습니다. 최대 인원에 도달했습니다.");
    //   return;
    // }

    const member = {
      crewId,
      crewName,
      crewLeader,
      crewMember,
    };

    console.log("store: ", crewMember);

    axios({
      url: `${REST_API_URL}/insertMember`,
      method: "POST",
      data: member,
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
    }).then((response) => {
      alert("크루 가입이 완료되었습니다.");
      board.crewCurrMember += 1;
      // router.push({ name: "crewBoardDetail" });
      location.reload();
    });
  };

  const deleteCrewMember = function (crewId, crewMember) {
    axios
      .delete(`${REST_API_URL}/deleteMember`, {
        params: {
          crewId: crewId,
          crewMember: crewMember,
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((response) => {
        alert("크루 탈퇴가 완료되었습니다.");
        location.reload();
      });
  };

  return {
    getMemberList,
    searchByCrewId,
    getCrewMember,
    searchByUserID,
    getUsersCrewList,
    insertCrewMember,
    deleteCrewMember,
    searchLeaderByUserID,
    getLeadersCrewList,
  };
});
