// mapStore.js
import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";

const REST_MAP_API = `http://localhost:8080/exerciseplaceapi`;

export const useMapStore = defineStore("map", () => {
  const locations = ref([]);
  const searchedLocations = ref([]);

  // 비동기 함수로 수정
  const selectAll = async function () {
    try {
      const res = await axios.get(`${REST_MAP_API}/api/read`);
      console.log(res.data);
      locations.value = res.data;
    } catch (err) {
      console.log(err);
      // alert('로그인 후 이용해주세요')
    }
  };

  // 비동기 함수로 수정
  const searchByCondition = async function (searchCondition) {
    try {
      const res = await axios.get(`${REST_MAP_API}/api/search`, {
        params: searchCondition, // searchCondition을 params로 전달

      });
      // console.log(res.data);
      searchedLocations.value = res.data;
    } catch (err) {
      console.log(err);
    }
  };
  

  const searchedLocationById = ref({});
  const searchById = function (id) {
    axios
      .get(`${REST_MAP_API}/api/search/${id}`,   )
      .then((res) => {
        searchedLocationById.value = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return {
    locations,
    selectAll,
    searchedLocations,
    searchByCondition,
    searchedLocationById,
    searchById,
  };
});
