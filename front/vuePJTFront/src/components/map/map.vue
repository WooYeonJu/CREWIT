<template>
  <div>
    <div class="headerImg">
      <img src="../../assests/headerImg1.jpg" alt="" />
      <h3>시설 정보</h3>
    </div>
    <div class="container">
      <div class="title">
        <h3 class="title-text">체육 시설 정보</h3>
        <span class="title-span">|</span>
      </div>

      <!-- 카카오맵 출력 div -->
      <div id="map"></div>

      <div class="selectbox-parent">
        <!-- <span>운동 종목 선택</span> -->
        <select v-model="searchInfo.word" @click="searchByExerciseName">
          <option value="" selected hidden>운동 종목 선택</option>
          <option
            v-for="options in crewBoardStore.exerciseList"
            :key="options.id"
            :value="options.exercise"
          >
            {{ options.exercise }}
          </option>
        </select>
      </div>

      <div>
        <table>
          <thead>
            <tr>
              <td class="place-name-td">시설</td>
              <td>주소</td>
              <td></td>
            </tr>
          </thead>
          <tbody v-if="mapStore.searchedLocations">
            <tr
              v-for="exercisePlace in mapStore.searchedLocations"
              :key="exercisePlace.id"
            >
              <td>
                {{ exercisePlace.name }}
              </td>
              <!-- <td>{{ exercisePlace.name }}</td> -->
              <td>{{ exercisePlace.location }}</td>
              <td>
                <a
                  :href="`http://localhost:5173/kakao/${exercisePlace.id}`"
                  class="table-link"
                  >바로가기</a
                >
                <!-- <RouterLink
                  :to="`/kakao/${exercisePlace.id}`"
                  class="table-link"
                  >바로가기</RouterLink
                > -->
              </td>
            </tr>
          </tbody>
          <tbody v-else>
            <tr
              v-for="exercisePlace in mapStore.locations"
              :key="exercisePlace.id"
            >
              <td>{{ exercisePlace.name }}</td>
              <td>{{ exercisePlace.location }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useCrewboardStore } from "@/stores/crewboard";
import { useMapStore } from "@/stores/mapStore.js";
import { ref, onMounted } from "vue";

// stores
const crewBoardStore = useCrewboardStore();
const mapStore = useMapStore();
const coordinate = ref([]);

// 검색 정보
const searchInfo = ref({
  key: "exercise",
  word: "",
});

// 카카오맵을 로드하는 함수
const loadKakaoMapAPI = () => {
  return new Promise((resolve, reject) => {
    if (window.kakao) {
      resolve(window.kakao);
    } else {
      const script = document.createElement("script");
      script.src = `https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
        import.meta.env.VITE_KAKAO_API_KEY
      }&libraries=clusterer,drawing,services`;
      script.onload = () => resolve(window.kakao);
      script.onerror = (error) => reject(error);
      document.head.appendChild(script);
    }
  });
};

// 카카오맵 초기화
const initKakaoMap = (kakao) => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(36.350992, 127.384776), // 기본 좌표 설정
    level: 8,
  };

  const map = new kakao.maps.Map(container, options);

  // console.log(coordinate.value);

  // 지도에 마커 및 기타 작업을 추가하는 로직
  coordinate.value.forEach((coor) => {
    // console.log(coor);
    const markerPosition = new kakao.maps.LatLng(coor.lat, coor.lng);
    const marker = new kakao.maps.Marker({
      position: markerPosition,
      clickable: true,
    });

    // 인포윈도우 설정
    const infowindow = new kakao.maps.InfoWindow({
      content: coor.content, // infoWindow 내용
      removable: true, // 닫기 버튼 추가
    });

    marker.setMap(map);

    kakao.maps.event.addListener(marker, "click", function () {
      infowindow.open(map, marker);
    });
  });
};

// 컴포넌트 로드 시 카카오맵 로드
onMounted(async () => {
  crewBoardStore.getExerciseList();
  try {
    await loadKakaoMapAPI(); // 카카오 맵 API 로드
    initKakaoMap(window.kakao); // 카카오 맵 초기화
  } catch (error) {
    console.error("Kakao Map API 로드 실패:", error);
  }
});

// 검색 버튼 클릭 시 실행되는 함수
const searchByExerciseName = async function () {
  await mapStore.searchByCondition(searchInfo.value);
  // console.log(searchInfo.value);
  // console.log(mapStore.searchedLocations);
  await getCoordinates(mapStore.searchedLocations); // 주소를 위도/경도로 변환
  initKakaoMap(window.kakao); // getCoordinates 이후에 카카오맵 초기화
};

// 주소를 위도/경도로 변환하는 함수
const getCoordinates = async (addressList) => {
  // console.log(addressList);
  // console.log(addressList);
  coordinate.value = [];

  if (window.kakao && window.kakao.maps) {
    const geocoder = new window.kakao.maps.services.Geocoder();

    // Promise.all을 사용하여 비동기 처리를 기다린 후, 완료된 후에 initKakaoMap 호출
    await Promise.all(
      addressList.map((address) => {
        return new Promise((resolve) => {
          geocoder.addressSearch(address.location, (result, status) => {
            if (status === window.kakao.maps.services.Status.OK) {
              const lat = result[0].y;
              const lng = result[0].x;

              // console.log(address);

              // coordinate.value에 infoWindow 설정
              coordinate.value.push({
                lat: lat,
                lng: lng,
                content: `<div style="padding: 10px; background-color: white; border: 1px solid #ccc; border-radius: 5px;">
                                <div style="font-weight: bold; margin-bottom: 5px">${address.name}</div>
                                <div style="white-space: nowrap;">${address.location}</div>
                                <div style="display:flex;"><a href="http://localhost:5173/kakao/${address.id}" }">상세보기</a> <span style="margin: 0px 5px"> | </span>
                                <div v-if="${address.homepage} !== 'null'"><a href=${address.homepage} target="_blank" style="color: blue">홈페이지</a></div></div>
                              </div>`,
              });
              // console.log(`위도: ${lat}, 경도: ${lng}`);
            } else {
              console.log(
                `${address.location} : 카카오 맵에서 제공되지 않는 주소입니다.`
              );
            }
            resolve(); // Promise 해결
          });
        });
      })
    );

    // 모든 좌표가 처리된 후에 카카오맵 초기화
    initKakaoMap(window.kakao);
  } else {
    console.error("Kakao Maps API가 로드되지 않았습니다.");
  }
};
</script>

<style scoped>
/* 전체 페이지의 기본 스타일 */
.headerImg {
  position: relative;
  width: 100%;
  height: 200px;
}

.headerImg img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.headerImg::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.35); /* 검은색 배경에 투명도 30% */
  z-index: 1; /* 이미지보다 위에 표시되도록 설정 */
}

.headerImg h3 {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white; /* 텍스트 색을 흰색으로 지정 */
  font-size: 24px; /* 필요에 따라 폰트 크기 조정 */
  font-weight: bold; /* 필요에 따라 폰트 두께 조정 */
  z-index: 2;
}

.container {
  max-width: 1200px;
  margin: 20px auto;
  justify-content: center;
  background-color: white;
}

.title {
  margin-top: 70px;
  text-align: center;
}

.title-text {
  font-size: 1.5rem;
  font-weight: bold;
  margin: 30px auto;
  color: #333;
}

.title-span {
  font-size: 1.7rem;
  font-weight: lighter;
  color: #acacac;
}

/* body {
  font-family: Arial, sans-serif;
  background-color: #f4f4f4;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
} */

/* select 박스 스타일 */
/* 상단 선택박스 및 버튼 배치 */
.selectbox-parent {
  display: flex;
  align-items: center; /* 수직 중앙 정렬 */
  justify-content: flex-end; /* 오른쪽 끝 정렬 */
  padding: 20px 0px;
  width: 90%;
  margin: 0 auto;
}

.selectbox-parent span {
  font-size: 16px;
  margin-right: 10px; /* select 박스와 간격을 추가 */
}

select {
  padding: 10px;
  font-size: 16px;
  border-radius: 5px;
  border: 1px solid #ccc;
  width: 200px; /* 원하는 너비로 조정 */
}

/* 검색 버튼 스타일 */
button {
  padding: 10px 15px;
  background-color: rgb(119, 119, 115);
  color: white;
  font-size: 16px;
  border: none;
  cursor: pointer;
  margin-right: 10px;
  transition: background-color 0.3s ease;
}

/* 버튼 호버 상태 스타일 */
button:hover {
  background-color: #ccc;
}

/* 테이블 스타일 */
table {
  width: 90%;
  border-collapse: collapse;
  margin: 20px auto;
  background-color: white;
  overflow: hidden;
  margin-bottom: 70px;
}

/* 테이블 헤더 스타일 */
th,
td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

tr {
  color: #333333;
}

thead {
  background-color: rgb(119, 119, 115);
}

thead td {
  color: white;
}

.place-name-td {
  border-right: 1px solid white;
}

/* 빈 테이블의 스타일 */
tbody.v-else tr {
  text-align: center;
  font-style: italic;
  color: #888;
}

/* 선택한 검색결과가 없는 경우 */
tbody.v-else tr td {
  padding: 15px;
}

.table-link {
  text-decoration: none;
  padding: 7px 10px;
  color: #888;
  background-color: white;
  border: 1px solid #ccc;
}

.table-link:hover {
  color: #ffffff;
  background-color: rgb(119, 119, 115);
  border: 1px solid rgb(119, 119, 115);
  /* font-weight: bold; */
}

#map {
  width: 90%;
  height: 400px;
  margin: 10px auto;
  border-radius: 8px;
  margin-top: 50px;
}
</style>
