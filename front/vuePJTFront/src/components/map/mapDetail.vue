<template>
  <div>
    <div class="headerImg">
      <img src="../../assests/headerImg1.jpg" alt="" />
      <h3>시설 상세 정보</h3>
    </div>
    <div class="container">
      <div class="title">
        <h3 class="title-text">{{ mapStore.searchedLocationById.name }}</h3>
        <span class="title-span">|</span>
      </div>
      <div>
        <div id="map" style="width: 100%; height: 400px"></div>
      </div>
      <div>
        <div class="map-info-container">
          <div class="first-line">
            <div class="map-info">
              <h4>주소</h4>
              <p>{{ mapStore.searchedLocationById.location }}</p>
            </div>
            <div class="map-info">
              <h4>영업시간</h4>
              <p>{{ mapStore.searchedLocationById.info }}</p>
            </div>
          </div>

          <div class="second-line">
            <div class="map-info">
              <hr />
              <h4>전화번호</h4>
              <p>{{ mapStore.searchedLocationById.call }}</p>
            </div>
            <div class="map-info">
              <hr />
              <h4>홈페이지</h4>
              <a :href="mapStore.searchedLocationById.homepage" target="_blank"
                >바로가기</a
              >
            </div>
          </div>

          <!-- <table>
          <thead>
            <tr>
              <th>항목</th>
              <th>내용</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>주소</td>
              <td>{{ mapStore.searchedLocationById.location }}</td>
            </tr>
            <tr>
              <td>영업시간</td>
              <td>{{ mapStore.searchedLocationById.info }}</td>
            </tr>
            <tr>
              <td>전화번호</td>
              <td>{{ mapStore.searchedLocationById.call }}</td>
            </tr>
            <tr>
              <td>링크</td>
              <td>
                <a
                  :href="mapStore.searchedLocationById.homepage"
                  target="_blank"
                  >홈페이지</a
                >
              </td>
            </tr>
          </tbody>
        </table> -->
        </div>

        <div>
          <!-- <CommentCreate /> -->
          <CommentList />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import CommentList from "../mapComment/mapCommentList.vue";
import CommentCreate from "../mapComment/mapCommentCreate.vue";

import { useMapStore } from "@/stores/mapStore";
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";

const mapStore = useMapStore();

const route = useRoute();
const id = route.params.exercisePlaceId;

onMounted(async () => {
  mapStore.searchById(id);
  try {
    await loadKakaoMapAPI(); // 카카오 맵 API 로드
    initKakaoMap(window.kakao); // 카카오 맵 초기화
  } catch (error) {
    console.error("Kakao Map API 로드 실패:", error);
  }
});

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

const initKakaoMap = (kakao) => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 기본 좌표 설정
    level: 3,
  };

  const map = new kakao.maps.Map(container, options);

  if (window.kakao && window.kakao.maps) {
    const geocoder = new window.kakao.maps.services.Geocoder();
    geocoder.addressSearch(
      mapStore.searchedLocationById.location,
      function (result, status) {
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
          map: map,
          position: coords,
        });

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
      }
    );
  }
};
</script>

<style scoped>
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
  background-color: white;
  max-width: 1200px;
  margin: 10px auto;
  justify-content: center;
  padding: 0px;
}

.map-info-container {
  margin: 50px 0px;
}

.first-line {
  display: flex;
  max-width: 1200px;
}

.second-line {
  display: flex;
}

hr {
  margin-bottom: 50px;
}

.map-info {
  padding: 10px;
  width: 50%;
}

.map-info h4 {
  margin: 10px 0px;
  font-size: 1.2rem;
  color: #585858;
}

.map-info p {
  margin: 10px 0px;
  color: #585858;
}

.map-info a {
  text-decoration: none;
  margin: 10px 0px;
  /* color: #333; */
  color: #585858;
}

.map-info a:hover {
  font-weight: bold;
}

/* 테이블 스타일 */
/* table {
  padding: 20px;
} */

/* table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 50px;
}

th,
td {
  padding: 12px 15px;
  text-align: left;
  border: 1px solid #ddd;
}

th {
  background-color: #f4f4f4;
  font-weight: bold;
}

td a {
  color: #4c77af;
  text-decoration: none;
}

td a:hover {
  text-decoration: underline;
} */

/* 카카오맵 스타일 */
#map {
  margin: 50px 0;
  border-radius: 8px;
  /* box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); */
}

/* 기본 레이아웃 */
/* div {
  margin: 20px;
} */

/* 제목 스타일 */

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

/* 반응형 디자인 */
@media (max-width: 768px) {
  h3 {
    font-size: 1.5rem;
  }

  table {
    font-size: 0.9rem;
  }

  td,
  th {
    padding: 10px 12px;
  }

  #map {
    height: 300px; /* 모바일에서 맵의 높이를 줄임 */
  }
}
</style>
