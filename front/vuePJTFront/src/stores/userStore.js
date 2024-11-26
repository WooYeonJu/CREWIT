import { ref, onMounted } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_USER_API = `http://localhost:8080/userapi`;

export const useUserStore = defineStore("user", () => {
  const loginUser = ref(null);

  onMounted(() => {
    loginUser.value = JSON.parse(sessionStorage.getItem("user"));
  });

  const userLogin = function (id, password) {
    axios
      .post(
        `${REST_USER_API}/login`,
        { id, password },
        {
          headers: {
            "access-token": sessionStorage.getItem("access-token"),
          },
        }
      )
      .then((response) => {
        sessionStorage.setItem("access-token", response.data["accessToken"]);
        loginUser.value = response.data;
        console.log(loginUser.value);
        sessionStorage.setItem("user", JSON.stringify(response.data));
        alert("로그인 성공!");
        router.push({ name: "Home" });
      })
      .catch(() => {
        alert("아이디/비밀번호가 틀렸습니다.");
      });
  };

  const userSignup = function (formData) {
    console.log([...formData]);

    // axios 요청 시 Content-Type을 자동으로 설정하도록 수정
    axios({
      url: `${REST_USER_API}/user`,
      method: "POST",
      data: formData, // FormData로 전송
      // Content-Type을 지정하지 않음, axios가 자동으로 설정합니다.
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
    })
      .then(() => {
        alert("회원가입 완료되었습니다.");
        router.push({ name: "login" }); // 로그인 페이지로 리다이렉트
      })
      .catch((err) => {
        console.error("회원가입 실패:", err);
      });
  };

  const userLogout = function () {
    const token = sessionStorage.getItem("access-token");

    if (token) {
      // 서버에 로그아웃 요청을 보낼 수 있습니다.
      axios
        .post(`${REST_USER_API}/logout`, {
          headers: {
            "access-token": sessionStorage.getItem("access-token"),
          },
        })
        .then(() => {
          sessionStorage.removeItem("access-token"); // 토큰 삭제
          sessionStorage.removeItem("user"); // 토큰 삭제
          loginUser.value = null; // 로그인 상태 초기화
          alert("로그아웃 성공");
          router.push({ name: "Home" });
        })
        .catch((err) => {
          console.log(err);
          alert("로그아웃 실패");
        });
    } else {
      loginUser.value = null;
      router.push({ name: "Home" });
    }
  };

  const serachedId = ref("");

  const usersearchId = function (name, email) {
    axios
      .get(`${REST_USER_API}/searchId`, {
        params: { name: name, email: email },
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((response) => {
        serachedId.value = response.data;
        router.push({ name: "userResultSearchId" });
      })
      .catch((error) => {
        alert("해당 정보의 사용자가 없습니다.");
        if (confirm("회원가입 페이지로 이동하시겠습니까?")) {
          router.push({ name: "signup" });
        }
      });
  };

  const checkedUser = ref({});

  const checkId = function (id, pw, pw2) {
    axios
      .get(`${REST_USER_API}/checkId?id=${id}`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((response) => {
        checkedUser.value = response.data;

        if (pw !== pw2) {
          alert("비밀번호가 일치하지 않습니다.");
        } else {
          if (checkedUser.value.password === pw) {
            alert("전에 사용한 비밀번호는 사용할 수 없습니다.");
          } else {
            console.log(id, pw);
            userChangePW(id, pw);
          }
        }
      })
      .catch((error) => {
        console.log(error);
        alert("해당 id의 사용자가 없습니다.");
        if (confirm("회원가입 페이지로 이동하시겠습니까?")) {
          router.push({ name: "signup" });
        }
      });
  };

  const checkPW = function (id, inputPw, pw, pw2) {
    console.log(inputPw);
    axios
      .post(
        `${REST_USER_API}/checkpw`,
        {
          id: id,
          password: inputPw,
        },
        {
          headers: {
            "access-token": sessionStorage.getItem("access-token"),
          },
        }
      )
      .then((response) => {
        console.log(response);
        if (response.status === 200 && response.data === true) {
          console.log("비밀번호가 일치합니다");
          if (pw !== pw2) {
            alert("비밀번호가 일치하지 않습니다.");
          } else {
            userChangePW(id, pw);
          }
        } else {
          console.log("비밀번호가 일치하지 않습니다.");
          console.log(response.data);
        }
      })
      .catch((err) => {
        console.log(err);
        alert("현재 비밀번호가 일치하지 않습니다.");
      });
  };

  const userChangePW = function (id, newPw) {
    axios
      .put(
        `${REST_USER_API}/changepw`,
        {
          id: id,
          newPw: newPw,
        },
        {
          headers: {
            "access-token": sessionStorage.getItem("access-token"),
          },
        }
      )
      .then(() => {
        alert("비밀번호 변경 성공!");

        if (loginUser.value === null) {
          router.push({ name: "login" });
        } else {
          console.log(loginUser);
          router.push({ name: "mypage" });
        }
      })
      .catch((err) => {
        // console.log(checkedUser.value);
        console.log(err);
      });
  };

  const myPageInfo = ref({});

  const mypage = function (id) {
    axios
      .get(`${REST_USER_API}/mypage?id=${id}`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((response) => {
        console.log(response.data);

        myPageInfo.value = response.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const changeEmail = function (id, email) {
    axios
      .put(
        `${REST_USER_API}/changeEmail`,
        {
          id: id,
          email: email,
        },
        {
          headers: {
            "access-token": sessionStorage.getItem("access-token"),
          },
        }
      )
      .then((res) => {
        alert("정상적으로 수정되었습니다.");
        router.push({ name: "mypage" });
      })
      .catch((err) => {
        console.log(err);
        alert("문제가 발생했습니다.");
      });
  };

  const changePhone = function (id, phone) {
    axios
      .put(
        `${REST_USER_API}/changePhone`,
        {
          id: id,
          phone: phone,
        },
        {
          headers: {
            "access-token": sessionStorage.getItem("access-token"),
          },
        }
      )
      .then((res) => {
        alert("정상적으로 수정되었습니다.");
        router.push({ name: "mypage" });
      })
      .catch((err) => {
        console.log(err);
        alert("문제가 발생했습니다.");
      });
  };

  const changeAge = function (id, age) {
    axios
      .put(
        `${REST_USER_API}/changeAge`,
        {
          id: id,
          age: age,
        },
        {
          headers: {
            "access-token": sessionStorage.getItem("access-token"),
          },
        }
      )
      .then((res) => {
        alert("정상적으로 수정되었습니다.");
        router.push({ name: "mypage" });
      })
      .catch((err) => {
        console.log(err);
        alert("문제가 발생했습니다.");
      });
  };

  const deleteUser = function (id) {
    console.log(id);

    axios
      .delete(`${REST_USER_API}/deleteUser`, {
        params: { id: id },
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((res) => {
        console.log(res.data);
        alert("정상적으로 탈퇴되었습니다.");
        sessionStorage.removeItem("access-token"); // 토큰 삭제
        sessionStorage.removeItem("user"); // 토큰 삭제
        loginUser.value = null; // 로그인 상태 초기화
        router.push({ name: "Home" });
      })
      .catch((err) => {
        console.log(err);
        alert("문제가 발생했습니다.");
      });
  };

  return {
    userLogin,
    loginUser,
    userSignup,
    userLogout,
    usersearchId,
    serachedId,
    userChangePW,
    checkId,
    checkedUser,
    myPageInfo,
    mypage,
    checkPW,
    changeEmail,
    changeAge,
    deleteUser,
    changePhone,
  };
});
