<template>
  <div class="main">
    <HeaderNav />
    <RouterView class="routerview" />
    <footerNav />
  </div>
</template>

<script setup>
import HeaderNav from "@/components/common/HeaderNav.vue";

import footerNav from "./components/common/footerNav.vue";
</script>

<style>
/* 초기화 작업 */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  list-style: none;
  /* background-color: white; */
}

.routerview {
  height: auto;
  min-height: 84%;
  /* padding-bottom: 10%; */
  /* height: 100vh - 120px; */
}
.main {
  height: 100vh;
}

</style>
