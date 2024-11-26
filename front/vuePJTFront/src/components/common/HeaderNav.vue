<template>
  <header>
    <nav class="header-nav">
      <!-- 로고 -->
      <div class="logo-container">
        <RouterLink to="/" class="logo"
          ><img src="../../../public/logo_ImgNText.png" alt="" width="200px" />
        </RouterLink>
      </div>

      <div class="nabMenu">
        <RouterLink :to="{ name: 'crewboardList' }" class="nabMenu"
          >크루 보러 가기</RouterLink
        >
        <span> | </span>
        <RouterLink :to="{ name: 'map' }" class="nabMenu">시설 정보</RouterLink>
      </div>

      <!-- <RouterLink to="/Video" class="nabMenu">동영상</RouterLink> -->

      <!-- 로그인/회원가입 또는 사용자 정보 -->
      <div class="nav-links">
        <span v-if="store.loginUser == null">
          <RouterLink to="/login" class="nabMenu">Login</RouterLink>
          <RouterLink to="/signup" class="nabMenu">Signup</RouterLink>
        </span>
        <span v-else class="hello">
          <RouterLink to="/mypage"> {{ store.loginUser.name }}</RouterLink>
          님 반갑습니다.
          <button @click="logout" class="logout-btn">로그아웃</button>
        </span>
      </div>
    </nav>
  </header>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore";
import { ref } from "vue";

const store = useUserStore();

const logout = function () {
  store.userLogout();
};
</script>

<style>
/* 전체 헤더 스타일 */
header {
  background-color: (255, 163, 185); /* 상단 네비게이션 배경색 */
  padding: 15px 30px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* 네비게이션 바 스타일 */
.header-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1200px;
  margin: 0 auto;
  flex-wrap: wrap; /* 작은 화면에서 메뉴 항목들이 잘 안 겹치도록 합니다 */
}

.logo-con

/* 로고 스타일 */
.logo {
  font-size: 2rem;
  color: gray;
  text-decoration: none;
  font-weight: bold;
}

/* 네비게이션 링크 스타일 */
.nav-links {
  display: flex;
  align-items: center;
}

/* 각 메뉴 항목 스타일 */
.nabMenu {
  color: gray;
  text-decoration: none;
  font-size: 1.25rem;
  margin-left: 20px;
  margin-right: 20px;
  transition: color 0.3s ease;
}

.nav-links:hover {
  color: #333;
}

/* 로그인/회원가입 영역 */
.hello {
  color: gray;
  font-size: 1rem;
  display: flex;
  align-items: center;
}

.hello button {
  margin-left: 10px;
  background-color: transparent;
  border: none;
  color: gray;
  font-size: 1rem;
  cursor: pointer;
  transition: color 0.3s ease;
}

.hello button:hover {
  color: #f44336;
}

/* 로그아웃 버튼 스타일 */
.logout-btn {
  margin-left: 10px;
  background-color: transparent;
  border: none;
  color: gray;
  font-size: 1rem;
  cursor: pointer;
  transition: color 0.3s ease;
}

.logout-btn:hover {
  color: #f44336;
}

/* 반응형 디자인 - 작은 화면에서 메뉴 레이아웃 조정 */
@media (max-width: 768px) {
  /* 네비게이션 바에서 메뉴를 수직 정렬로 변경 */
  .header-nav {
    flex-direction: column;
    align-items: flex-start;
  }

  /* 메뉴 항목들 간의 간격을 줄여서 더 많이 보여줌 */
  .nabMenu {
    font-size: 1rem;
    margin: 10px 0;
  }

  /* 로고 크기 줄이기 */
  .logo {
    font-size: 1.5rem;
  }

  /* 로그인/회원가입 영역 스타일 변경 */
  .nav-links {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }

  /* 로그인/회원가입 버튼과 텍스트 사이의 여백을 줄여줌 */
  .hello {
    font-size: 0.9rem;
  }

  /* 버튼 크기 줄이기 */
  .hello button,
  .logout-btn {
    font-size: 0.9rem;
  }
}

/* 480px 이하에서 메뉴 항목 항상 보이도록 설정 */
@media (max-width: 480px) {
  .header-nav {
    padding: 10px 15px;
  }

  /* 메뉴 항목들이 사라지지 않도록 하고 수평으로 유지 */
  .nabMenu {
    display: inline-block;
    margin: 5px 0px;
  }

  .logo {
    font-size: 1.2rem;
  }

  /* 로그인/회원가입 텍스트 크기 축소 */
  .hello {
    font-size: 0.8rem;
  }

  /* 버튼 크기 조정 */
  .hello button,
  .logout-btn {
    font-size: 0.8rem;
  }
}
</style>
