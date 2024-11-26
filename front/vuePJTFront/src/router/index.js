import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/HomeView.vue";
import UserLogin from "@/components/user/UserLogin.vue";
import UserSignup from "@/components/user/UserSignup.vue";
import UserMypage from "@/components/user/UserMypage.vue";
import BoardView from "@/views/BoardView.vue";
import KakaoView from "@/views/KakaoView.vue";
import UserSearchId from "@/components/user/UserSearchId.vue";
import UserSearchPW from "@/components/user/UserSearchPW.vue";
import UserResultSearchId from "@/components/user/UserResultSearchId.vue";

import CrewBoardCreate from "@/components/crewboard/CrewBoardCreate.vue";
import CrewBoardDetail from "@/components/crewboard/CrewBoardDetail.vue";
import CrewBoardList from "@/components/crewboard/CrewBoardList.vue";
import CrewBoardUpdate from "@/components/crewboard/CrewBoardUpdate.vue";
import CrewDetailBoardList from "@/components/crewdetail/crewDetailBoardList.vue";
import CrewDetailBoardSearchList from "@/components/crewdetail/crewDetailBoardSearchList.vue";
import CrewDetailView from "@/views/crewDetailView.vue";
import CrewDetailBoardDetail from "@/components/crewdetail/crewDetailBoardDetail.vue";
import CrewDetailBoardCreate from "@/components/crewdetail/CrewDetailBoardCreate.vue";
import CrewDetailBoardUpdate from "@/components/crewdetail/CrewDetailBoardUpdate.vue";
import mapDetail from "@/components/map/mapDetail.vue";
import map from "@/components/map/map.vue";

const routes = [
  {
    path: "",
    name: "Home",
    component: Home,
  },

  {
    path: "/login",
    name: "login",
    component: UserLogin,
  },
  {
    path: "/searchId",
    name: "userSearchId",
    component: UserSearchId,
  },
  {
    path: "/resultSearchId",
    name: "userResultSearchId",
    component: UserResultSearchId,
  },
  {
    path: "/searchPW",
    name: "userSearchPW",
    component: UserSearchPW,
  },

  {
    path: "/signup",
    name: "signup",
    component: UserSignup,
  },
  {
    path: "/mypage",
    name: "mypage",
    component: UserMypage,
  },
  {
    path: "/crewboard",
    name: "crewboard",
    component: BoardView,
    children: [
      {
        path: "",
        name: "crewboardList",
        component: CrewBoardList,
      },
      {
        path: "create",
        name: "crewBoardCreate",
        component: CrewBoardCreate,
      },
      {
        path: ":crewId",
        name: "crewBoardDetail",
        component: CrewBoardDetail,
        children: [
          {
            path: "",
            name: "crewDetailView",
            component: CrewDetailView,
            children: [
              {
                path: "",
                name: "crewDetailBoardList",
                component: CrewDetailBoardList,
              },
              {
                path: "search",
                name: "crewDetailBoardSearchList",
                component: CrewDetailBoardSearchList,
              },
              {
                path: ":boardId",
                name: "crewDetailBoardDetail",
                component: CrewDetailBoardDetail,
              },
              {
                path: "create",
                name: "crewDetailBoardCreate",
                component: CrewDetailBoardCreate,
              },
              {
                path: "update",
                name: "crewDetailBoardUpdate",
                component: CrewDetailBoardUpdate,
              },
            ],
          },
        ],
      },
      {
        path: "update",
        name: "crewboardUpdate",
        component: CrewBoardUpdate,
      },
    ],
  },
  {
    path: "/kakao",
    name: "kakao",
    component: KakaoView,
    children: [
      {
        path: "",
        name: "map",
        component: map,
      },
      {
        path: ":exercisePlaceId",
        name: mapDetail,
        component: mapDetail,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
