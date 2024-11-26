import { defineStore } from "pinia";
import { ref, computed } from "vue";
import router from "@/router";
import axios from "axios";

const REST_COMMENT_API = `http://localhost:8080/commentapi`;

export const useCommentStore = defineStore("comment", () => {
  const comments = ref([]);

  const commentCnt = computed(() => {
    return comments.value.length;
  });

  const createComment = (newComment) => {
    axios({
      url: REST_COMMENT_API + "/comment",
      method: "POST",
      data: newComment,
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
    })
      .then((response) => {
        alert("댓글이 등록되었습니다.");
        location.reload(); // 페이지 새로고침
        router.push({ name: "videoDetail" });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // const getComments = () => {
  //   axios
  //     .get(REST_COMMENT_API + "/movie", {
  //       headers: {
  //         "access-token": sessionStorage.getItem("access-token"),
  //       },
  //     })
  //     .then((response) => {
  //       comments.value = response.data;
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //       alert("권한이 없습니다.");
  //     });
  // };

  const getCommentsByBoardId = (boardId) => {
    axios
      .get(`${REST_COMMENT_API}/comment/${boardId}`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((response) => {
        console.log(response.data);
        comments.value = response.data;
      });
  };

  const commentUpdate = (comment) => {
    console.log(comment);
    axios
      .put(`${REST_COMMENT_API}/comment/update`, comment, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then(() => {
        return;
        // location.reload();
        // router.push({ name: "" });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return {
    comments,
    commentCnt,
    createComment,
    getCommentsByBoardId,
    commentUpdate,
  };
});
