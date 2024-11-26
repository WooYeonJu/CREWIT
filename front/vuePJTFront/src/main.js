import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";

import { useKakao } from "vue3-kakao-maps/@utils";

// Kakao API 초기화
useKakao(import.meta.env.VITE_KAKAO_API_KEY, [
  "clusterer",
  "services",
  "drawing",
]);

const app = createApp(App);

app.use(createPinia());
app.use(router);
app.mount("#app");
