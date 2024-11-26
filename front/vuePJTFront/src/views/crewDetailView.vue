<template>
  <div class="container">
    <!-- RouterView가 검색창 바로 아래에 출력 -->
    <div class="content">
      <RouterView />
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
/* 전체 컨테이너 */
.container {
  max-width: 1200px;
  display: flex;
  flex-direction: column; /* 세로로 배치 */
  justify-content: center;
  margin: 0 auto;
  /* align-items: center; */
}
</style>
