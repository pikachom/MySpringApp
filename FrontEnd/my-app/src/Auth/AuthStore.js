import axios from "axios";
import { observable, runInAction, toJS, reaction } from "mobx";
import jwt_decode from "jwt-decode";

let authMethods = observable({
  login: (loginData, history) => {
    axios
      .post("http://localhost:8080/api/signin", {
        withCredentials: true,
        username: loginData.username,
        password: loginData.password,
      })
      .then(function (response) {
        console.log("로그인 성공");
        authMethods.setDefaultAxiosRequestHeader(response.data.token);
        authMethods.setUserInfo(response.data.token);
        runInAction(() => {
          authorizationInfo.isAuthorized = true;
        });
        history.replace("/");
      })
      .catch(function (error) {
        console.log("로그인 실패");
      });
  },
  logout: () => {
    axios.defaults.headers.common["Authorization"] = null;
    runInAction(() => {
      authMethods.setAuthorizationInfo(false);
    });
  },
  setDefaultAxiosRequestHeader: (token) => {
    // accessToken 설정
    axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
  },
  setUserInfo: (token) => {
    userInfo = jwt_decode(token);
  },
  hello: (loginData) => {
    axios
      .get("http://localhost:8080/api/hello", {
        withCredentials: true,
        origin: "http://localhost:8080",
      })
      .then(function (response) {
        console.log("로그인 성공");
      })
      .catch(function (error) {
        console.log("로그인 실패");
      });
  },
  setAuthorizationInfo(isAuthorized) {
    runInAction(() => {
      authorizationInfo.isAuthorized = isAuthorized;
    });
  },
  getIsAuthorized() {
    return authorizationInfo.isAuthorized;
  },
  getUserInfo() {
    return userInfo;
  },
});
let userInfo = observable({
  sub: null,
  role: null,
});
let authorizationInfo = observable({
  isAuthorized: false,
});

export default authMethods;
export { userInfo, authorizationInfo };
